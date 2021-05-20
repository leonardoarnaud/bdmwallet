package br.eti.arnaud.bdmwallet.app.remote

import br.eti.arnaud.bdmwallet.BuildConfig
import br.eti.arnaud.bdmwallet.app.remote.model.response.ExchangeValuesResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface ExchangeService {
    @GET("cambiobdm.php")
    suspend fun exchangeValues(): ExchangeValuesResponse?

    companion object {
        fun getInstance(): ExchangeService {
            return Retrofit.Builder()
                .baseUrl(BuildConfig.EXCHANGE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ExchangeService::class.java)
        }
    }
}