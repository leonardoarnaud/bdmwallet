package br.eti.arnaud.bdmwallet.base

import androidx.lifecycle.ViewModel
import br.eti.arnaud.bdmwallet.app.App
import br.eti.arnaud.bdmwallet.app.LoadingStartEvent
import br.eti.arnaud.bdmwallet.app.LoadingStopEvent
import org.greenrobot.eventbus.EventBus

abstract class BaseViewModel: ViewModel() {

    val db get() = App.instance?.db!!

    var loadingStack: Int = 0

    fun loading(job: () -> Unit){
        loading(true)
        job()
        loading(false)
    }

    fun loading(b: Boolean){
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
}

