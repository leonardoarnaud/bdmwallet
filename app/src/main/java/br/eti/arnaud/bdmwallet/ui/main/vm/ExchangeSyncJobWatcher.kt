package br.eti.arnaud.bdmwallet.ui.main.vm

import android.util.Log
import androidx.lifecycle.viewModelScope
import br.eti.arnaud.bdmwallet.BuildConfig
import br.eti.arnaud.bdmwallet.app.App
import br.eti.arnaud.bdmwallet.app.JobWatcher
import br.eti.arnaud.bdmwallet.app.local.model.ExchangeValues
import br.eti.arnaud.bdmwallet.app.remote.ExchangeService
import br.eti.arnaud.bdmwallet.base.BaseViewModel
import kotlinx.coroutines.*

class ExchangeSyncJobWatcher(private val scope: CoroutineScope): JobWatcher {

    private var exchangeListeningJob: Job? = null

    override fun start() {
        exchangeListeningJob = startExchangeListening()
    }

    override fun finish() {
        exchangeListeningJob?.cancel()
    }

    private fun startExchangeListening(): Job {
        return scope.launch(Dispatchers.IO) {
            ExchangeService.getInstance().let {
                while (isActive){
                    try {
                        it.exchangeValues()?.let { response ->
                            App.instance?.db?.exchangeValuesDao()?.insert(
                                ExchangeValues(
                                    uid = 0,
                                    buy = response.real.compra,
                                    sell = response.real.venda
                                )
                            )
                        }
                        Log.i("BDMWallet", "Cotação atualizada")
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                    delay(BuildConfig.SERVER_PULLING_DELAY)
                }
            }
        }
    }
}