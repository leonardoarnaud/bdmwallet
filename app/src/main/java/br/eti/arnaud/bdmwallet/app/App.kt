package br.eti.arnaud.bdmwallet.app

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import br.eti.arnaud.bdmwallet.BuildConfig
import br.eti.arnaud.bdmwallet.app.local.AppDatabase
import br.eti.arnaud.bdmwallet.app.local.model.ExchangeValues
import br.eti.arnaud.bdmwallet.app.remote.ExchangeService
import kotlinx.coroutines.*
import retrofit2.Retrofit

class App: Application() {

    lateinit var db: AppDatabase private set

    override fun onCreate() {
        super.onCreate()

        instantiateDb()
    }

    private fun instantiateDb() {
        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "bdmwallet"
        ).fallbackToDestructiveMigration().build()
    }

    init { instance = this }
    companion object {
        var instance: App? = null
    }
}