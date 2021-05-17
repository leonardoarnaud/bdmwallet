package br.eti.arnaud.bdmwallet.ui.receive

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.eti.arnaud.bdmwallet.base.BaseViewModel

class ReceiveViewModel : BaseViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Aqui você recebe bdm"
    }
    val text: LiveData<String> = _text
}