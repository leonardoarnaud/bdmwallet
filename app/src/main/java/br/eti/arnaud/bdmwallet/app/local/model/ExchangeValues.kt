package br.eti.arnaud.bdmwallet.app.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ExchangeValues(
    @PrimaryKey val uid: Int,
    @ColumnInfo(name = "buy") val buy: Double,
    @ColumnInfo(name = "sell") val sell: Double,
)