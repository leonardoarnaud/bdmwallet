package br.eti.arnaud.bdmwallet.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

abstract class BindingFragment<ViewBinding>: BaseFragment() {

    var vg: ViewGroup? = null
    lateinit var li: LayoutInflater

    abstract fun onBind(): ViewBinding

    private var _b: ViewBinding? = null
    val b: ViewBinding get() = _b!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        this.li = inflater
        this.vg = container
        _b = onBind()
        return (b as androidx.viewbinding.ViewBinding).root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onReady()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _b = null
    }

    abstract fun onReady()

}