package br.eti.arnaud.bdmwallet.ui.utils

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class AppRecyclerViewAdapter<ViewBinding, T>(
    private val list: List<T>,
    private val viewBinding: ViewBinding,
    private val bind: (b: ViewBinding, item: T) -> Unit
) : RecyclerView.Adapter<AppRecyclerViewAdapter<ViewBinding, T>.ViewHolder>() {

    override fun onCreateViewHolder(vg: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(viewBinding)

    override fun onBindViewHolder(vh: ViewHolder, position: Int) = bind(vh.b, list[position])

    override fun getItemCount() = list.size

    inner class ViewHolder(val b: ViewBinding) : RecyclerView.ViewHolder(
        (b as androidx.viewbinding.ViewBinding).root
    )


}
