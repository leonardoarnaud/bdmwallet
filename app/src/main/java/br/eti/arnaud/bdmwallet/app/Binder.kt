package br.eti.arnaud.bdmwallet.app

import android.view.LayoutInflater
import android.view.ViewGroup
import br.eti.arnaud.bdmwallet.base.BindingActivity
import br.eti.arnaud.bdmwallet.ui.main.MainActivity
import br.eti.arnaud.bdmwallet.databinding.ActivityMainBinding
import br.eti.arnaud.bdmwallet.databinding.FragmentSendBinding
import br.eti.arnaud.bdmwallet.databinding.FragmentStatementBinding
import br.eti.arnaud.bdmwallet.databinding.FragmentReceiveBinding
import br.eti.arnaud.bdmwallet.base.BindingFragment
import br.eti.arnaud.bdmwallet.ui.send.SendFragment
import br.eti.arnaud.bdmwallet.ui.statement.StatementFragment
import br.eti.arnaud.bdmwallet.ui.receive.ReceiveFragment

class Binder {
    companion object{
        fun <ViewBinding>bind(
            f: BindingFragment<ViewBinding>,
            linflater: LayoutInflater,
            c: ViewGroup?
        ): ViewBinding {
            return when (f){

                // Fragment -> Binding
                // ----------------------------
                is StatementFragment -> FragmentStatementBinding.inflate(linflater, c, false)
                is SendFragment -> FragmentSendBinding.inflate(linflater, c, false)
                is ReceiveFragment -> FragmentReceiveBinding.inflate(linflater, c, false)

                //-----------------------------

                else -> throw ClassNotFoundException(
                    "Binder de ${f.javaClass.simpleName} não instanciado"
                )
            } as ViewBinding
        }

        fun <ViewBinding>bind(
            a: BindingActivity<ViewBinding>
        ): ViewBinding {
            return when (a){

                // Activity -> Binding
                // ----------------------------
                is MainActivity -> ActivityMainBinding.inflate(a.layoutInflater)

                //-----------------------------

                else -> throw ClassNotFoundException(
                    "Binder de ${a.javaClass.simpleName} não instanciado"
                )
            } as ViewBinding
        }
    }

}