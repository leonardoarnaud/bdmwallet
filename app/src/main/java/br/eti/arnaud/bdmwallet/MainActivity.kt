package br.eti.arnaud.bdmwallet

import android.view.Menu
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import br.eti.arnaud.bdmwallet.app.toRealCurrency
import br.eti.arnaud.bdmwallet.base.BindingActivity
import br.eti.arnaud.bdmwallet.databinding.ActivityMainBinding


class MainActivity: BindingActivity<ActivityMainBinding>() {

    lateinit var vm: MainViewModel

    override fun onReady() {
        vm = ViewModelProvider(this).get(MainViewModel::class.java)
        setSupportActionBar(b.toolbar)

        vm.exchangeValues.observe {
            b.toolbarTitle.text = ("1 BDM = ${it.buy?.toRealCurrency()}")
        }

        setupNavigationBar()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_toolbar, menu)
        return true
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