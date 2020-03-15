package it.melive.android.navigation.basic.wizard


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.findNavController

import kotlinx.android.synthetic.main.fragment_wizard_with_field.*

/**
 * A simple [Fragment] subclass.
 */
class WizardNameFragment : WizardFragment() {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        editText.hint = "Insert Name"
        observeLiveData(viewModel.name)
    }

    override fun onNextButtonClicked() {
        val directions = WizardNameFragmentDirections.actionWizardNameFragmentToWizardSurnameFragment()
        findNavController().navigate(directions)
    }

    override fun onTextFieldChanged() {
        viewModel.insertName(editText.text.toString())
    }
}
