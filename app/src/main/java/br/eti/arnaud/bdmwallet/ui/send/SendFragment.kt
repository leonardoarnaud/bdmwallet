package br.eti.arnaud.bdmwallet.ui.send

import android.widget.TextView
import androidx.fragment.app.viewModels
import br.eti.arnaud.bdmwallet.base.BindingFragment
import br.eti.arnaud.bdmwallet.databinding.FragmentSendBinding

class SendFragment : BindingFragment<FragmentSendBinding>() {

    val vm: SendViewModel by viewModels()

    override fun onReady() {
        vm.text.observe {
            b.textSend.text = it
        }

    }

}