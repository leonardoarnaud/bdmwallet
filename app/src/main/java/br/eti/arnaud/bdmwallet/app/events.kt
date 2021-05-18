package br.eti.arnaud.bdmwallet.app

import androidx.annotation.StringRes

class CatchableErrorEvent (val code: CatchableErrorCode)
class ErrorMessageEvent (@StringRes val msgStringRes: Int)
class LoadingStartEvent
class LoadingStopEvent

enum class CatchableErrorCode {
    ADDRESS_NOT_DEFINED
}