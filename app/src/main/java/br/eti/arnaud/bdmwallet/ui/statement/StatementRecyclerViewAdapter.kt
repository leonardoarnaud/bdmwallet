package br.eti.arnaud.bdmwallet.ui.statement

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.recyclerview.widget.RecyclerView
import br.eti.arnaud.bdmwallet.R
import br.eti.arnaud.bdmwallet.app.local.model.WalletTransaction
import br.eti.arnaud.bdmwallet.app.tint
import br.eti.arnaud.bdmwallet.app.toBDMCurrency
import br.eti.arnaud.bdmwallet.databinding.ItemWalletTransactionBinding

class StatementRecyclerViewAdapter(private val walletTransactionList: List<WalletTransaction>) :
    RecyclerView.Adapter<StatementRecyclerViewAdapter.ViewHolder>() {

    override fun onBindViewHolder(vh: ViewHolder, i: Int) {
        walletTransactionList[i].let {
            vh.b.amountTextview.text = it.amount.toBDMCurrency()
            vh.b.addressTextview.text = it.address

            vh.b.directionImageview.apply {
                when(it.direction){
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

    class ViewHolder(val b: ItemWalletTransactionBinding) : RecyclerView.ViewHolder(b.root)

    override fun onCreateViewHolder(vg: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemWalletTransactionBinding.inflate(LayoutInflater.from(vg.context)))
    }

    override fun getItemCount() = walletTransactionList.size
}
