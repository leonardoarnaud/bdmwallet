package br.eti.arnaud.bdmwallet.ui.receive

import android.widget.TextView
import androidx.fragment.app.viewModels
import br.eti.arnaud.bdmwallet.databinding.FragmentReceiveBinding
import br.eti.arnaud.bdmwallet.base.BindingFragment
import br.eti.arnaud.bdmwallet.databinding.FragmentStatementBinding

class ReceiveFragment : BindingFragment<FragmentReceiveBinding>() {

    val vm: ReceiveViewModel by viewModels()

    override fun onBind() = FragmentReceiveBinding.inflate(li, vg, false)

    override fun onReady() {
        val textView: TextView = b.textNotifications
        vm.text.observe {
            textView.text = it
        }
    }
}