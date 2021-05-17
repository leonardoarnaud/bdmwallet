package br.eti.arnaud.bdmwallet.base

import android.os.Bundle
import br.eti.arnaud.bdmwallet.app.Binder

abstract class BindingActivity<ViewBinding>: BaseActivity() {

    private var _b: ViewBinding? = null
    val b get() = _b!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _b = Binder.bind(this)
        setContentView((_b as androidx.viewbinding.ViewBinding).root)
        onReady()
    }

    abstract fun onReady()
}