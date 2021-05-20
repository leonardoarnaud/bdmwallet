package br.eti.arnaud.bdmwallet.ui.statement

import androidx.fragment.app.viewModels
import br.eti.arnaud.bdmwallet.app.toBDMCurrency
import br.eti.arnaud.bdmwallet.app.toRealCurrency
import br.eti.arnaud.bdmwallet.base.BindingFragment
import br.eti.arnaud.bdmwallet.databinding.FragmentStatementBinding

class StatementFragment : BindingFragment<FragmentStatementBinding>() {

    private val vm: StatementViewModel by viewModels()

    override fun onReady() {

        vm.exchangeValues.observe {
            b.bdmExchangeValueTextview.text = it.buy.toRealCurrency()
        }

        vm.wallet.observe {
            b.bdmBalanceTextview.text = it.balance.toBDMCurrency()
        }

        vm.bdmToRealExchange.observe{
            b.bdmToRealExchangeTextview.text = it.toRealCurrency()
        }
    }
}