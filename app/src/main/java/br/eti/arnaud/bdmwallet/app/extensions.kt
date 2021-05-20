package br.eti.arnaud.bdmwallet.app

import androidx.annotation.StringRes
import org.greenrobot.eventbus.EventBus
import java.math.RoundingMode
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.*

fun Double.toRealCurrency(): String {
    return "R$ ${DecimalFormat("###,###,##0.00").apply {
        roundingMode = RoundingMode.CEILING
        decimalFormatSymbols = DecimalFormatSymbols.getInstance(Locale.GERMAN)
        isDecimalSeparatorAlwaysShown = true
    }.format(this)}"
}

fun Long.toBDMCurrency(): String {
    return "${DecimalFormat("###,###,##0.00").apply {
        decimalFormatSymbols = DecimalFormatSymbols.getInstance(Locale.GERMAN)
    }.format(this/100.0)} BDM"
}

fun throwErrorMessage(@StringRes msgResId: Int){
    EventBus.getDefault().post(ErrorMessageEvent(msgResId))
}

fun throwCatchableError(errorEvent: CatchableErrorEvent){
    EventBus.getDefault().post(errorEvent)
}