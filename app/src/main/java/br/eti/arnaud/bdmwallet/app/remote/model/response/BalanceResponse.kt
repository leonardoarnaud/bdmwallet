package br.eti.arnaud.bdmwallet.app.remote.model.response

data class BalanceResponse (
    val address: String,
    val confirmations: Int,
    val balance: Long)