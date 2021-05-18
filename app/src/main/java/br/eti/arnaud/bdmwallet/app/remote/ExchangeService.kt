package br.eti.arnaud.bdmwallet.app.remote

import br.eti.arnaud.bdmwallet.app.remote.model.response.ExchangeValuesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ExchangeService {
    @GET("cambiobdm.php")
    suspend fun exchangeValues(): ExchangeValuesResponse?

}