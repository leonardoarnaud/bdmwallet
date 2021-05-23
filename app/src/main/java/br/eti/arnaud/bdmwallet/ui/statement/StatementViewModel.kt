package br.eti.arnaud.bdmwallet.ui.statement

import androidx.lifecycle.*
import br.eti.arnaud.bdmwallet.app.local.model.Wallet
import br.eti.arnaud.bdmwallet.app.local.model.WalletTransaction
import br.eti.arnaud.bdmwallet.base.BaseViewModel
import java.lang.Exception

class StatementViewModel: BaseViewModel() {

    private val _exchangeValues get() = db.exchangeValuesDao().select()
    val exchangeValues = Transformations.map(_exchangeValues){
        it
    }

    private val _wallet get() = db.walletDao().select()
    val wallet: LiveData<Wallet> = Transformations.map(_wallet){
        loading(it == null)
        it
    }

    val bdmToRealExchange get() = MediatorLiveData<Double>().apply {
        Observer<Any?>{
            try {
                this.postValue(
                    convertBdmToReal(
                        bdm = wallet.value!!.balance,
                        realExchange = exchangeValues.value!!.sell
                    )
                )
            } catch (e: Exception){
                e.printStackTrace()
            }
        }.let {
            addSource(exchangeValues, it)
            addSource(wallet, it)
        }
    }

    private val _walletTransactions get() = db.walletTransactionDao().select()
    val walletTransactions = Transformations.map(_walletTransactions){
        it
    }

    private fun convertBdmToReal(bdm: Long, realExchange: Double): Double{
        return (bdm / 100).toDouble() * realExchange
    }


}