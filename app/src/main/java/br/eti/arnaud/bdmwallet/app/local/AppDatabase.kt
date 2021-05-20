package br.eti.arnaud.bdmwallet.app.local

import androidx.room.Database
import androidx.room.RoomDatabase
import br.eti.arnaud.bdmwallet.app.local.dao.ExchangeValuesDao
import br.eti.arnaud.bdmwallet.app.local.dao.WalletDao
import br.eti.arnaud.bdmwallet.app.local.model.ExchangeValues
import br.eti.arnaud.bdmwallet.app.local.model.Wallet

@Database(
    entities = [
        ExchangeValues::class,
        Wallet::class
    ],
    version = 8
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun exchangeValuesDao(): ExchangeValuesDao
    abstract fun walletDao(): WalletDao
}