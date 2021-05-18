package br.eti.arnaud.bdmwallet

import androidx.lifecycle.viewModelScope
import br.eti.arnaud.bdmwallet.app.local.model.ExchangeValues
import br.eti.arnaud.bdmwallet.app.remote.ExchangeService
import br.eti.arnaud.bdmwallet.base.BaseViewModel
import kotlinx.coroutines.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainViewModel: BaseViewModel() {

    private var api = Retrofit.Builder()
        .baseUrl(BuildConfig.API_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private var exchange = Retrofit.Builder()
        .baseUrl(BuildConfig.EXCHANGE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private var exchangeListeningJob: Job

    val exchangeValues get() = db.exchangeValuesDao().select()

    init {
        exchangeListeningJob = startExchangeListening()
    }

    override fun onCleared() {
        super.onCleared()

        exchangeListeningJob.cancel()
    }

    private fun startExchangeListening(): Job {
        return viewModelScope.launch(Dispatchers.IO) {
            exchange.create(ExchangeService::class.java).let {
                while (isActive){
                    it.exchangeValues()?.let { response ->
                        db.exchangeValuesDao().insert(
                            ExchangeValues(
                                uid = 0,
                                buy = response.real.compra,
                                sell = response.real.venda
                            )
                        )
                    }
                    delay(BuildConfig.EXCHANGE_PULLING_DELAY)
                }
            }
        }
    }
}