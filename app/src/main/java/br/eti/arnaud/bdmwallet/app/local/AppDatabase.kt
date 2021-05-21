package br.eti.arnaud.bdmwallet.app.local

import androidx.room.Database
import androidx.room.RoomDatabase
import br.eti.arnaud.bdmwallet.app.local.dao.ExchangeValuesDao
import br.eti.arnaud.bdmwallet.app.local.dao.WalletDao
import br.eti.arnaud.bdmwallet.app.local.dao.WalletTransactionDao
import br.eti.arnaud.bdmwallet.app.local.model.ExchangeValues
import br.eti.arnaud.bdmwallet.app.local.model.Wallet
import br.eti.arnaud.bdmwallet.app.local.model.WalletTransaction

@Database(
    entities = [
        ExchangeValues::class,
        Wallet::class,
        WalletTransaction::class
    ],
    version = 9
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun exchangeValuesDao(): ExchangeValuesDao
    abstract fun walletDao(): WalletDao
    abstract fun walletTransactionDao(): WalletTransactionDao
}