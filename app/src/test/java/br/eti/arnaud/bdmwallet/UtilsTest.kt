package br.eti.arnaud.bdmwallet

import br.eti.arnaud.bdmwallet.app.utils.Timestamp
import br.eti.arnaud.bdmwallet.ui.main.vm.MainViewModel
import org.junit.Test

import org.junit.Assert.*
import org.mockito.Mock

class UtilsTest {

    private var timestamp: Timestamp = Timestamp(1621632127)

    @Test
    fun timestamp_deveria_mostrar_a_data_correta() {
        assertEquals("21/05/2021", timestamp.getDate())
    }

    @Test
    fun timestamp_deveria_mostrar_a_hora_correta() {
        assertEquals("18:22:07", timestamp.getTime())
    }

}