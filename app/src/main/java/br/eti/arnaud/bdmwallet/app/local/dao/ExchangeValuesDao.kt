package br.eti.arnaud.bdmwallet.app.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import br.eti.arnaud.bdmwallet.app.local.model.ExchangeValues

@Dao
interface ExchangeValuesDao {
    @Query("SELECT * FROM exchangevalues LIMIT 1")
    fun select(): LiveData<ExchangeValues>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(exchangeValues: ExchangeValues)
}