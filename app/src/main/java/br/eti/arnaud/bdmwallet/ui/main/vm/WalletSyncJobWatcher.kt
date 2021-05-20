package br.eti.arnaud.bdmwallet.ui.main.vm

import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.viewModelScope
import br.eti.arnaud.bdmwallet.BuildConfig
import br.eti.arnaud.bdmwallet.app.*
import br.eti.arnaud.bdmwallet.app.local.model.Wallet
import br.eti.arnaud.bdmwallet.app.remote.ApiService
import br.eti.arnaud.bdmwallet.base.BaseViewModel
import kotlinx.coroutines.*
import java.lang.Exception

class WalletSyncJobWatcher(
    val scope: CoroutineScope,
): JobWatcher {

    private val db = App.instance?.db
    private var balanceUpdatingJob: Job? = null
    private val walletObserver = Observer<Wallet?>{
        it?.let {
            if (balanceUpdatingJob?.isActive != true){
                balanceUpdatingJob = startBalanceUpdatingJob(it)
            }
            return@Observer
        }
        throwCatchableError(CatchableErrorEvent(CatchableErrorCode.ADDRESS_NOT_DEFINED))
    }

    private val walletObservable get() = db?.walletDao()?.select()

    private fun startBalanceUpdatingJob(wallet: Wallet): Job {
        return scope.launch(Dispatchers.IO) {
            ApiService.getInstance().let {
                while (isActive){
                    try {
                        it.balance(wallet.address)?.let { response ->
                            db?.walletDao()?.insert(
                                Wallet(
                                    uid = wallet.uid,
                                    address = wallet.address,
                                    balance = response.balance
                                )
                            )
                        }
                        Log.i("BDMWallet", "Carteira atualizada")
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                    delay(BuildConfig.SERVER_PULLING_DELAY)
                }
            }
        }
    }

    override fun start() {
        walletObservable?.observeForever(walletObserver)
    }

    override fun finish() {
        walletObservable?.removeObserver(walletObserver)
        balanceUpdatingJob?.cancel()
    }

}