package br.eti.arnaud.bdmwallet.app.local

import androidx.room.Database
import androidx.room.RoomDatabase
import br.eti.arnaud.bdmwallet.app.local.dao.ExchangeValuesDao
import br.eti.arnaud.bdmwallet.app.local.model.ExchangeValues

@Database(
    entities = [ExchangeValues::class],
    version = 5
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun exchangeValuesDao(): ExchangeValuesDao
}