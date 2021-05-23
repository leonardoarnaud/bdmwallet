package br.eti.arnaud.bdmwallet.ui.main.vm

import br.eti.arnaud.bdmwallet.BuildConfig
import br.eti.arnaud.bdmwallet.app.JobWatcher
import br.eti.arnaud.bdmwallet.app.remote.ExchangeService
import br.eti.arnaud.bdmwallet.app.remote.model.response.ExchangeValuesResponse
import kotlinx.coroutines.*

class ExchangeServicePullingJob(
    private val scope: CoroutineScope,
    private val onResponse: (response: ExchangeValuesResponse) -> Unit
): JobWatcher {

    private var exchangeListeningJob: Job? = null

    override fun start() {
        exchangeListeningJob = startExchangeListening()
    }

    override fun finish() {
        exchangeListeningJob?.cancel()
    }

    override fun isActive(): Boolean = exchangeListeningJob?.isActive == true

    private fun startExchangeListening(): Job {
        return scope.launch(Dispatchers.IO) {
            while (isActive){
                try {
                    ExchangeService.getInstance().exchangeValues()?.let { onResponse(it) }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
                delay(BuildConfig.SERVER_PULLING_DELAY)
            }
        }
    }
}