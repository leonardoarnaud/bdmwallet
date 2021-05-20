package br.eti.arnaud.bdmwallet.app.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Wallet(
    @PrimaryKey(autoGenerate = true) val uid: Int = 0,
    @ColumnInfo(name = "address") val address: String,
    @ColumnInfo(name = "balance") val balance: Long = 0L,
)