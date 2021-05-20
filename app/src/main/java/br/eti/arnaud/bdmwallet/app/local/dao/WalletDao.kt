package br.eti.arnaud.bdmwallet.app.local.dao

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*
import br.eti.arnaud.bdmwallet.app.local.model.ExchangeValues
import br.eti.arnaud.bdmwallet.app.local.model.Wallet

@Dao
interface WalletDao {
    @Query("SELECT * FROM wallet LIMIT 1")
    fun select(): LiveData<Wallet>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(wallet: Wallet)
}