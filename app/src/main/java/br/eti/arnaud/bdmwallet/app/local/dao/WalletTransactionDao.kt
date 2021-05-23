package br.eti.arnaud.bdmwallet.app.local.dao

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*
import br.eti.arnaud.bdmwallet.app.local.model.ExchangeValues
import br.eti.arnaud.bdmwallet.app.local.model.WalletTransaction

@Dao
interface WalletTransactionDao {
    @Query("SELECT * FROM wallettransaction")
    fun select(): LiveData<List<WalletTransaction>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(exchangeValues: List<WalletTransaction>)
}