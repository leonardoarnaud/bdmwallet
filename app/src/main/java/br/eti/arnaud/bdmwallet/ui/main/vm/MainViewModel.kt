package br.eti.arnaud.bdmwallet.ui.main.vm

import androidx.lifecycle.viewModelScope
import br.eti.arnaud.bdmwallet.app.App
import br.eti.arnaud.bdmwallet.app.JobWatcher
import br.eti.arnaud.bdmwallet.app.local.model.Wallet
import br.eti.arnaud.bdmwallet.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel: BaseViewModel() {

    private val walletSyncJobWatcher: JobWatcher = WalletSyncJobWatcher(viewModelScope)
    private val exchangeSyncJobWatcher: JobWatcher = ExchangeSyncJobWatcher(viewModelScope)

    init {
        exchangeSyncJobWatcher.start()
        walletSyncJobWatcher.start()
    }

    fun setAddress(address: CharSequence) {
        viewModelScope.launch(Dispatchers.IO){
            App.instance?.db?.walletDao()?.insert(
                Wallet(
                    address = address.toString()
                )
            )
        }
    }

    override fun onCleared() {
        super.onCleared()

        exchangeSyncJobWatcher.finish()
        walletSyncJobWatcher.finish()
    }


}