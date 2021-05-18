package br.eti.arnaud.bdmwallet.app

import java.math.RoundingMode
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.*

fun Double.toRealCurrency(): String {
    return "R$ ${DecimalFormat("#.##").apply {
        roundingMode = RoundingMode.CEILING
        decimalFormatSymbols = DecimalFormatSymbols.getInstance(Locale.FRANCE)
    }.format(this)}"

}