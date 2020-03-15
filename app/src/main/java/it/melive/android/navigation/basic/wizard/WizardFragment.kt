package it.melive.android.navigation.basic.wizard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.navigation.navGraphViewModels
import it.melive.android.navigation.basic.R
import it.melive.android.navigation.basic.ViewModel
import kotlinx.android.synthetic.main.fragment_wizard_with_field.*

abstract class WizardFragment: Fragment() {
    val viewModel: WizardViewModel by navGraphViewModels(R.id.wizard)
    val sharedViewModel: ViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_wizard_with_field, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        nextButton.setOnClickListener {
            onNextButtonClicked()
        }
        editText.doAfterTextChanged {
            onTextFieldChanged()
        }
    }

    abstract fun onNextButtonClicked()
    abstract fun onTextFieldChanged()

    fun observeLiveData(liveData: LiveData<String>) {
        liveData.observe(viewLifecycleOwner, Observer {
            if (!editText.hasFocus()) {
                editText.setText(it)
            }
        })
    }
}