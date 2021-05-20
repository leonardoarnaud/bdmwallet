package br.eti.arnaud.bdmwallet.base

import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData

abstract class BaseFragment: Fragment() {

    fun <T> LiveData<T>.observe(data: (result: T) -> Unit){
        this.observe(viewLifecycleOwner){ it?.let {
            data(it)
        }}
    }
}