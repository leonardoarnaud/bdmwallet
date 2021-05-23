package br.eti.arnaud.bdmwallet.ui.main.vm

import android.util.Log
import br.eti.arnaud.bdmwallet.BuildConfig
import br.eti.arnaud.bdmwallet.app.*
import br.eti.arnaud.bdmwallet.app.local.model.Wallet
import br.eti.arnaud.bdmwallet.app.remote.ApiService
import br.eti.arnaud.bdmwallet.app.remote.model.response.BalanceResponse
import br.eti.arnaud.bdmwallet.app.remote.model.response.TransactionsResponse
import kotlinx.coroutines.*
import java.lang.Exception

class BalanceServicePullingJob(
    private val scope: CoroutineScope,
    private val onResponse: (
        wallet: Wallet,
        response: BalanceResponse
    ) -> Unit
): JobWatcher {

    private var pullingJob: Job? = null

    var wallet: Wallet? = null

    private fun startPullingJob(): Job {
        return scope.launch(Dispatchers.IO) {
            while (isActive){
                wallet?.let {
                    try {
                        ApiService.getInstance().balance(it.address)?.let { r ->
                            onResponse(it, r)
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

    override fun isActive(): Boolean = pullingJob?.isActive == true

}