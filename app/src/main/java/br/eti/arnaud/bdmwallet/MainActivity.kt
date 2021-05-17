package br.eti.arnaud.bdmwallet

import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import br.eti.arnaud.bdmwallet.base.BindingActivity
import br.eti.arnaud.bdmwallet.databinding.ActivityMainBinding

class MainActivity: BindingActivity<ActivityMainBinding>() {

    override fun onReady() {
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