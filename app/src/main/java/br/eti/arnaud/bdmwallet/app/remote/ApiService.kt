package br.eti.arnaud.bdmwallet.app.remote

import br.eti.arnaud.bdmwallet.BuildConfig
import br.eti.arnaud.bdmwallet.app.remote.model.response.BalanceResponse
import br.eti.arnaud.bdmwallet.app.remote.model.response.TransactionsResponse
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

interface ApiService {
    @GET("addresses/balance/{address}")
    suspend fun balance(@Path(value="address") address: String): BalanceResponse?

    @GET("transactions/address/{address}/limit/999")
    suspend fun transactions(
        @Path("address") address: String,
        @Query("after") afterId: String? = null,
    ): List<List<TransactionsResponse>>?

    companion object {
        fun getInstance(): ApiService {
            return Retrofit.Builder()
                .baseUrl(BuildConfig.API_URL)
                .client(OkHttpClient.Builder()
                    .connectTimeout(3, TimeUnit.MINUTES)
                    .writeTimeout(3, TimeUnit.MINUTES)
                    .readTimeout(3, TimeUnit.MINUTES)
                    .build())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiService::class.java)
        }
    }
}