package br.eti.arnaud.bdmwallet.base

import android.os.Bundle

abstract class BindingActivity<ViewBinding>: BaseActivity() {

    private var _b: ViewBinding? = null
    val b get() = _b!!

    protected abstract fun onBind(): ViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _b = onBind()
        setContentView((_b as androidx.viewbinding.ViewBinding).root)
        onReady()
    }

    abstract fun onReady()
}