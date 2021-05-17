package br.eti.arnaud.bdmwallet.ui.receive

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import br.eti.arnaud.bdmwallet.R
import br.eti.arnaud.bdmwallet.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ReceiveViewModel : BaseViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Aqui você recebe bdm"
    }
    val text: LiveData<String> = _text

    init {
        simulateProessing()
    }

    fun simulateProessing(){
        viewModelScope.launch(
            context = Dispatchers.IO
        ) {
            loading {
                throwErrorMessage(R.string.app_name)
                Thread.sleep(3000)
            }
        }
    }
}