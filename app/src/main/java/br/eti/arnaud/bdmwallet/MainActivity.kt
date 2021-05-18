package br.eti.arnaud.bdmwallet

import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import br.eti.arnaud.bdmwallet.base.BindingActivity
import br.eti.arnaud.bdmwallet.databinding.ActivityMainBinding
import kotlinx.coroutines.Job

class MainActivity: BindingActivity<ActivityMainBinding>() {

    lateinit var vm: MainViewModel

    override fun onReady() {
        vm = ViewModelProvider(this).get(MainViewModel::class.java)

        vm.exchangeValues.observe {
            Toast.makeText(this, "${it?.buy} -  ${it?.sell}", Toast.LENGTH_LONG).show()
        }

        setupNavigationBar()
    }

    private fun setupNavigationBar() {
        findNavController(R.id.nav_host_fragment_activity_main).let {
            setupActionBarWithNavController(it, AppBarConfiguration(
                setOf(
                    R.id.navigation_statement,
                    R.id.navigation_send,
                    R.id.navigation_receive
                )
            ))
            b.navView.setupWithNavController(it)
        }
    }
}