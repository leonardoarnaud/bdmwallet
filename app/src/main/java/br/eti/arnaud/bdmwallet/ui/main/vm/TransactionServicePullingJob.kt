package br.eti.arnaud.bdmwallet.ui.main.vm

import br.eti.arnaud.bdmwallet.BuildConfig
import br.eti.arnaud.bdmwallet.app.JobWatcher
import br.eti.arnaud.bdmwallet.app.local.model.Wallet
import br.eti.arnaud.bdmwallet.app.remote.ApiService
import br.eti.arnaud.bdmwallet.app.remote.model.response.TransactionsResponse
import kotlinx.coroutines.*

class TransactionServicePullingJob(
    private val scope: CoroutineScope,
    private val onResponse: (
        wallet: Wallet,
        response: List<TransactionsResponse>
    ) -> Unit
): JobWatcher {

    private var pullingJob: Job? = null

    var wallet: Wallet? = null

    override fun isActive(): Boolean = pullingJob?.isActive == true

    private fun startPullingJob(): Job {
        return scope.launch(Dispatchers.IO) {
            while (isActive){
                wallet?.let {
                    try {
                        ApiService.getInstance().transactions(it.address)?.let { r ->
                            onResponse(it, r[0])
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
                delay(BuildConfig.SERVER_PULLING_DELAY)
            }
        }
    }

    override fun start() {
        pullingJob = startPullingJob()
    }

    override fun finish() {
        pullingJob?.cancel()
    }

}