package br.eti.arnaud.bdmwallet.app.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class WalletTransaction (
    @PrimaryKey(autoGenerate = true) val uid: Long = 0,
    @ColumnInfo(name = "amount") val amount: Long,
    @ColumnInfo(name = "direction") val direction: Boolean,
    @ColumnInfo(name = "timestamp") val timestamp: Long,
    @ColumnInfo(name = "address") val address: String,
    @ColumnInfo(name = "status") val status: Byte,
){
    companion object {
        const val DIRECTION_INFLOW = true
        const val DIRECTION_OUTFLOW = false
    }
}