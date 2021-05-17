package br.eti.arnaud.bdmwallet.ui.statement

import androidx.fragment.app.viewModels
import br.eti.arnaud.bdmwallet.base.BindingFragment
import br.eti.arnaud.bdmwallet.databinding.FragmentStatementBinding

class StatementFragment : BindingFragment<FragmentStatementBinding>() {

    val vm: StatementViewModel by viewModels()

    override fun onReady() {
        vm.text.observe {
            b.textStatement.text = it
        }
    }
}