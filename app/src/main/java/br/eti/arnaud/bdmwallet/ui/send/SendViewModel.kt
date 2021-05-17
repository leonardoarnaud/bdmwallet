package br.eti.arnaud.bdmwallet.ui.send

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.eti.arnaud.bdmwallet.base.BaseViewModel

class SendViewModel : BaseViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Aqui voce envia BDM"
    }
    val text: LiveData<String> = _text
}