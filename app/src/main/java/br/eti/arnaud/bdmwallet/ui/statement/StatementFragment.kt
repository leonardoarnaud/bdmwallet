package br.eti.arnaud.bdmwallet.ui.statement

import android.util.Log
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import br.eti.arnaud.bdmwallet.R
import br.eti.arnaud.bdmwallet.app.local.model.WalletTransaction
import br.eti.arnaud.bdmwallet.app.tint
import br.eti.arnaud.bdmwallet.app.toBDMCurrency
import br.eti.arnaud.bdmwallet.app.toRealCurrency
import br.eti.arnaud.bdmwallet.app.utils.Timestamp
import br.eti.arnaud.bdmwallet.base.BindingFragment
import br.eti.arnaud.bdmwallet.databinding.FragmentStatementBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.logging.Handler

class StatementFragment : BindingFragment<FragmentStatementBinding>() {

    private val vm: StatementViewModel by viewModels()

    override fun onBind() = FragmentStatementBinding.inflate(li, vg, false)

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
            setupStatementRecyclerViewAdapter(it)
        }
    }

    private fun setupStatementRecyclerViewAdapter(list: List<WalletTransaction>) {
        b.statementRecyclerview.apply {
            adapter?.let { (it as StatementRecyclerViewAdapter<*>).update(list); return }
            adapter = StatementRecyclerViewAdapter(list){ b, wt ->
                b.amountTextview.text = wt.amount.toBDMCurrency()
                b.addressTextview.text = wt.address
                Timestamp(wt.timestamp).let {
                    b.dateTextview.text = it.getDate()
                    b.timeTextview.text = it.getTime()
                }
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
    }
}