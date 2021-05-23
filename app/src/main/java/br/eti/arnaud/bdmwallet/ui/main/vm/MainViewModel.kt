package br.eti.arnaud.bdmwallet.ui.main.vm

import androidx.lifecycle.Observer
import androidx.lifecycle.viewModelScope
import br.eti.arnaud.bdmwallet.app.*
import br.eti.arnaud.bdmwallet.app.local.model.ExchangeValues
import br.eti.arnaud.bdmwallet.app.local.model.Wallet
import br.eti.arnaud.bdmwallet.app.local.model.WalletTransaction
import br.eti.arnaud.bdmwallet.app.remote.model.response.BalanceResponse
import br.eti.arnaud.bdmwallet.app.remote.model.response.ExchangeValuesResponse
import br.eti.arnaud.bdmwallet.app.remote.model.response.TransactionsResponse
import br.eti.arnaud.bdmwallet.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel: BaseViewModel() {

    private val balanceServicePullingJob = BalanceServicePullingJob(viewModelScope){ w, r ->
        saveWallet(w, r)
    }

    private val exchangeServicePullingJob = ExchangeServicePullingJob(viewModelScope){
        saveExchangeValues(it)
    }

    private val transactionsServicePullingJob = TransactionServicePullingJob(viewModelScope){ w, r ->
        saveTransactions(w, r)
    }

    private val walletObservable get() = db.walletDao().select()

    private val walletObserver = Observer<Wallet?>{
        it?.let {
            transactionsServicePullingJob.wallet = it
            balanceServicePullingJob.wallet = it
            if (!transactionsServicePullingJob.isActive()){
                transactionsServicePullingJob.start()
            }
            return@Observer
        }
        throwCatchableError(CatchableErrorEvent(CatchableErrorCode.ADDRESS_NOT_DEFINED))
    }

    init {
        exchangeServicePullingJob.start()
        balanceServicePullingJob.start()
        transactionsServicePullingJob.start()
        walletObservable.observeForever(walletObserver)
    }

    private fun saveExchangeValues(it: ExchangeValuesResponse) {
        db.exchangeValuesDao().insert(
            ExchangeValues(
                uid = 0,
                buy = it.real.compra,
                sell = it.real.venda
            )
        )
    }

    private fun saveTransactions(wallet: Wallet, response: List<TransactionsResponse>) {
        db.walletTransactionDao().insert(
            response.mapTo(arrayListOf()){ tr ->
                WalletTransaction(
                    id = tr.id,
                    address = if (tr.imSending(wallet.address)) tr.recipient else tr.sender,
                    amount = tr.amount,
                    direction = if (tr.imSending(wallet.address))
                        WalletTransaction.DIRECTION_OUTFLOW else WalletTransaction.DIRECTION_INFLOW,
                    timestamp = tr.timestamp,
                    status = 0
                )
            }
        )
    }

    private fun saveWallet(wallet: Wallet, response: BalanceResponse) {
        db.walletDao().insert(
            Wallet(
                uid = wallet.uid,
                address = wallet.address,
                balance = response.balance
            )
        )
    }

    fun saveAddress(address: CharSequence) {
        viewModelScope.launch(Dispatchers.IO){
            App.instance?.db?.walletDao()?.insert(
                Wallet(
                    address = address.toString()
                )
            )
        }
    }

    override fun onCleared() {
        super.onCleared()

        walletObservable.removeObserver(walletObserver)
        exchangeServicePullingJob.finish()
        balanceServicePullingJob.finish()
    }



}