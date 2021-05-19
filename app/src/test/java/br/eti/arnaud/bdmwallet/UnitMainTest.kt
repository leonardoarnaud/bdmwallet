package br.eti.arnaud.bdmwallet

import org.junit.Test

import org.junit.Assert.*

class UnitMainTest {

    val vm = MainViewModel()

    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun exchangeValuesShouldNotNull() {
        Thread.sleep(2000)
        assertNotNull(vm.exchangeValues.value)
    }
}