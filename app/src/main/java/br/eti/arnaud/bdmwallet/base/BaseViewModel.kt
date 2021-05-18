package br.eti.arnaud.bdmwallet.base

import androidx.annotation.StringRes
import androidx.lifecycle.ViewModel
import br.eti.arnaud.bdmwallet.app.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import org.greenrobot.eventbus.EventBus

abstract class BaseViewModel: ViewModel() {

    val db get() = App.instance?.db!!

    var loadingStack: Int = 0

    fun loading(job: () -> Unit){
        loading(true)
        job()
        loading(false)
    }

    private fun loading(b: Boolean){
        if (b){
            loadingStack++
        } else {
            if (loadingStack > 0){
                loadingStack--
            }
        }
        if (loadingStack > 0){
            EventBus.getDefault().post(LoadingStartEvent())
        } else {
            EventBus.getDefault().post(LoadingStopEvent())
        }
    }

    fun throwErrorMessage(@StringRes msgResId: Int){
        EventBus.getDefault().post(ErrorMessageEvent(msgResId))
    }

    fun throwCatchableError(errorEvent: CatchableErrorEvent){
        EventBus.getDefault().post(errorEvent)
    }

}

