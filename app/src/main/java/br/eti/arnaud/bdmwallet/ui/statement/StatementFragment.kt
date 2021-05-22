package br.eti.arnaud.bdmwallet.ui.statement

import androidx.fragment.app.viewModels
import br.eti.arnaud.bdmwallet.R
import br.eti.arnaud.bdmwallet.app.local.model.WalletTransaction
import br.eti.arnaud.bdmwallet.app.tint
import br.eti.arnaud.bdmwallet.app.toBDMCurrency
import br.eti.arnaud.bdmwallet.app.toRealCurrency
import br.eti.arnaud.bdmwallet.base.BindingFragment
import br.eti.arnaud.bdmwallet.databinding.FragmentStatementBinding
import br.eti.arnaud.bdmwallet.databinding.ItemWalletTransactionBinding
import br.eti.arnaud.bdmwallet.ui.utils.AppRecyclerViewAdapter

class StatementFragment : BindingFragment<FragmentStatementBinding>() {

    private val vm: StatementViewModel by viewModels()

    override fun onReady() {

        vm.exchangeValues.observe {
            b.bdmExchangeValueTextview.text = it.buy.toRealCurrency()
        }

        vm.wallet.observe {
            b.bdmBalanceTextview.text = it.balance.toBDMCurrency()
        }

        vm.bdmToRealExchange.observe {
            b.bdmToRealExchangeTextview.text = it.toRealCurrency()
        }

        vm.walletTransactions.observe {
            b.statementRecyclerview.adapter = AppRecyclerViewAdapter(it,
                ItemWalletTransactionBinding.inflate(layoutInflater)
            ){ b, wt ->
                bindWalletTransactionItem(b, wt)
            }
        }
    }

    private fun bindWalletTransactionItem(b: ItemWalletTransactionBinding, wt: WalletTransaction) {
        b.amountTextview.text = wt.amount.toBDMCurrency()
        b.addressTextview.text = wt.address
        b.directionImageview.apply {
            when(wt.direction){
                WalletTransaction.DIRECTION_INFLOW -> {
                    setImageResource(R.drawable.ic_baseline_arrow_circle_down_24)
                    tint(R.color.inflow_color)
                }
                WalletTransaction.DIRECTION_OUTFLOW -> {
                    setImageResource(R.drawable.ic_baseline_arrow_circle_up_24)
                    tint(R.color.outflow_color)
                }
            }
        }
    }
}