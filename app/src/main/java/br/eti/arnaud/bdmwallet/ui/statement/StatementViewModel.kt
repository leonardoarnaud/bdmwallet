package br.eti.arnaud.bdmwallet.ui.statement

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.eti.arnaud.bdmwallet.base.BaseViewModel
import kotlinx.coroutines.Job

class StatementViewModel : BaseViewModel() {


    val exchangeValues get() = db.exchangeValuesDao().select()



}