package br.eti.arnaud.bdmwallet.ui.statement

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.eti.arnaud.bdmwallet.base.BaseViewModel

class StatementViewModel : BaseViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Extra dos bdms aqui"
    }
    val text: LiveData<String> = _text
}