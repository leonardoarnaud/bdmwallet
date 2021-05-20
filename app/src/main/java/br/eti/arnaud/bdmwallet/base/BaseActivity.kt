package br.eti.arnaud.bdmwallet.base

import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import br.eti.arnaud.bdmwallet.R
import br.eti.arnaud.bdmwallet.app.*
import com.google.android.material.snackbar.Snackbar
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

abstract class BaseActivity: AppCompatActivity() {

    fun <T> LiveData<T>.observe(data: (result: T) -> Unit){
        this.observe(this@BaseActivity){ it?.let {
            data(it)
        }}
    }

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    open fun onMessageEvent(event: ErrorMessageEvent?) {
        event?.msgStringRes?.let {
            Snackbar.make(findViewById(R.id.container), getString(it), Snackbar.LENGTH_LONG).show()
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    open fun onMessageEvent(event: CatchableErrorEvent) {
        Log.e("ERROR", "Ocorreu um erro, código: ${event.code}" )
        onError(event.code)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    open fun onMessageEvent(event: LoadingStartEvent?) {
        findViewById<View>(R.id.progress_bar)?.visibility = View.VISIBLE
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    open fun onMessageEvent(event: LoadingStopEvent?) {
        findViewById<View>(R.id.progress_bar)?.visibility = View.GONE
    }

    open fun onError(code: CatchableErrorCode){}

}