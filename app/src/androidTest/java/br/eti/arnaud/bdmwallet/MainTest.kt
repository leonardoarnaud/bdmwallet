package br.eti.arnaud.bdmwallet

import androidx.lifecycle.ViewModelProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import br.eti.arnaud.bdmwallet.tools.BaseTest
import br.eti.arnaud.bdmwallet.ui.main.vm.MainViewModel
import br.eti.arnaud.bdmwallet.ui.statement.StatementViewModel
import getOrAwaitValue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before

@RunWith(AndroidJUnit4::class)
class MainTest: BaseTest() {

    lateinit var mainViewModel: MainViewModel
    lateinit var statementViewModel: StatementViewModel

    @Before
    fun init() {
        testActivityRule.scenario.onActivity {
            GlobalScope.launch(Dispatchers.IO){
                mainViewModel = ViewModelProvider(it).get(MainViewModel::class.java)
                statementViewModel = ViewModelProvider(it).get(StatementViewModel::class.java)
            }.apply {
                while (isActive){ /* esperando terminar */ }
            }
        }
    }

    @Test
    fun ao_iniciar_activity_o_cambio_deveria_ser_atualizado() {
        val oldValue = statementViewModel.exchangeValues.getOrAwaitValue()
        Thread.sleep(5000)
        assertNotSame(oldValue, statementViewModel.exchangeValues.getOrAwaitValue())
    }

    @Test
    fun ao_definir_endereco_saldo_deve_ser_atualizado() {
        val oldValue = statementViewModel.wallet.getOrAwaitValue()
        mainViewModel.saveAddress("3FgStowr1tn5eFpa3UcKotoGSfDtEcfEA1w")
        Thread.sleep(5000)
        assertNotSame(oldValue, statementViewModel.wallet.getOrAwaitValue())
    }
}