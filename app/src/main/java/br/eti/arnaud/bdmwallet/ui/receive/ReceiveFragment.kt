package br.eti.arnaud.bdmwallet.ui.receive

import android.widget.TextView
import androidx.fragment.app.viewModels
import br.eti.arnaud.bdmwallet.databinding.FragmentReceiveBinding
import br.eti.arnaud.bdmwallet.base.BindingFragment

class ReceiveFragment : BindingFragment<FragmentReceiveBinding>() {

    val vm: ReceiveViewModel by viewModels()

    override fun onReady() {
        val textView: TextView = b.textNotifications
        vm.text.observe {
            textView.text = it
        }
    }
}