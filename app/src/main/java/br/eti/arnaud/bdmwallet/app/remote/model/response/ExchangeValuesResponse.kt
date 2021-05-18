package br.eti.arnaud.bdmwallet.app.remote.model.response

data class ExchangeValuesResponse(
    val real: Values
){
    data class Values(
        val venda: Double,
        val compra: Double
    )
}