package it.melive.android.navigation.basic.wizard


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.findNavController
import it.melive.android.navigation.basic.R
import kotlinx.android.synthetic.main.fragment_wizard_with_field.*

/**
 * A simple [Fragment] subclass.
 */
class WizardSurnameFragment : WizardFragment() {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        editText.hint = "Insert Surname"
        nextButton.text = "Save"
        observeLiveData(viewModel.surname)
    }

    override fun onNextButtonClicked() {
        sharedViewModel.finishWizardWith(name = viewModel.name.value ?: "", surname = viewModel.surname.value ?: "")
        findNavController().popBackStack(R.id.wizard, true)
    }

    override fun onTextFieldChanged() {
        viewModel.insertSurname(editText.text.toString())
    }
}
