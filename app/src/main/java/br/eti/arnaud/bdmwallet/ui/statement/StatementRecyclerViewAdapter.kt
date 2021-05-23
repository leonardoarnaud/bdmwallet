package br.eti.arnaud.bdmwallet.ui.statement

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.eti.arnaud.bdmwallet.databinding.ItemWalletTransactionBinding

class StatementRecyclerViewAdapter<T>(
    private var list: List<T>,
    private val bind: (b: ItemWalletTransactionBinding, item: T) -> Unit
) : RecyclerView.Adapter<StatementRecyclerViewAdapter<T>.ViewHolder>() {

    override fun onCreateViewHolder(vg: ViewGroup, viewType: Int) =
        ViewHolder(ItemWalletTransactionBinding.inflate(
            LayoutInflater.from(vg.context), vg, false
        ))

    override fun onBindViewHolder(vh: ViewHolder, position: Int) = bind(vh.b, list[position])

    override fun getItemCount() = list.size

    fun update(list: List<Any>) {
        this.list = list as List<T>
        notifyDataSetChanged()
    }

    inner class ViewHolder(val b: ItemWalletTransactionBinding): RecyclerView.ViewHolder(
        (b as androidx.viewbinding.ViewBinding).root
    )


}
