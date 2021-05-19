package br.eti.arnaud.bdmwallet

import androidx.lifecycle.ViewModelProvider
import androidx.test.core.app.launchActivity
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import br.eti.arnaud.bdmwallet.tools.BaseTest
import br.eti.arnaud.bdmwallet.ui.statement.StatementViewModel
import getOrAwaitValue
import kotlinx.coroutines.delay

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before

@RunWith(AndroidJUnit4::class)
class MainTest: BaseTest() {

    lateinit var vm: StatementViewModel

    @Before
    fun init() {
        testActivityRule.scenario.onActivity {
            vm = ViewModelProvider(it).get(StatementViewModel::class.java)
        }
    }

    @Test
    fun ao_iniciar_activity_o_cambio_deveria_ser_atualizado() {
        val oldValue = vm.exchangeValues.getOrAwaitValue()
        Thread.sleep(5000)
        assertNotSame(oldValue, vm.exchangeValues.getOrAwaitValue())
    }
}