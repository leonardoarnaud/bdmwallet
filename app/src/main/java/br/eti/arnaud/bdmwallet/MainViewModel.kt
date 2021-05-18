package br.eti.arnaud.bdmwallet

import br.eti.arnaud.bdmwallet.app.App
import br.eti.arnaud.bdmwallet.app.local.model.ExchangeValues
import br.eti.arnaud.bdmwallet.app.remote.ExchangeService
import br.eti.arnaud.bdmwallet.base.BaseViewModel
import kotlinx.coroutines.*
import retrofit2.Retrofit

class MainViewModel: BaseViewModel() {

    private var api = Retrofit.Builder()
        .baseUrl(BuildConfig.API_URL)
        .build()

    private var exchange = Retrofit.Builder()
        .baseUrl(BuildConfig.EXCHANGE_URL)
        .build()

    private var exchangeListeningJob: Job

    init {
        exchangeListeningJob = startExchangeListening()
    }

    private fun startExchangeListening(): Job {
        return GlobalScope.launch(Dispatchers.IO) {
            exchange.create(ExchangeService::class.java).let {
                while (isActive){
                    it?.exchangeValues()?.execute()?.body()?.let { response ->
                        db?.exchangeValuesDao()?.insert(
                            ExchangeValues(
                                uid = 0,
                                buy = response.real.compra,
                                sell = response.real.venda
                            )
                        )
                    }
                    delay(App.EXCHANGE_PULLING_DELAY)
                }
            }
        }
    }
}