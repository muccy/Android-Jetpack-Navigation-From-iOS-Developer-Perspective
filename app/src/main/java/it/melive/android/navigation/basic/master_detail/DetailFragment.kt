package it.melive.android.navigation.basic.master_detail


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import it.melive.android.navigation.basic.R
import it.melive.android.navigation.basic.ViewModel
import kotlinx.android.synthetic.main.fragment_detail.*
import java.lang.Exception

/**
 * A simple [Fragment] subclass.
 */
class DetailFragment : Fragment() {
    private val args: DetailFragmentArgs by navArgs()
    private val viewModel: ViewModel by activityViewModels()

    companion object {
        val RESULT_KEY = "DetailFragment.result"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        messageTextView.text = args.message

        findNavController().previousBackStackEntry?.savedStateHandle?.set(RESULT_KEY, "Hello from detail fragment!")

        goToModalButton.setOnClickListener {
            findNavController().navigate(R.id.wizard)
        }

        observeViewModel()
        observeStepsTextField()
    }

    private fun observeViewModel() {
        viewModel.steps.observe(viewLifecycleOwner, Observer {
            updateStepTextField()
        })
    }

    private fun updateStepTextField() {
        if (!stepsTextField.hasFocus()) {
            stepsTextField.setText(viewModel.steps.value.toString())
        }
    }

    private fun observeStepsTextField() {
        stepsTextField.doAfterTextChanged {
            try {
                viewModel.updateSteps(it.toString().toInt())
            }
            catch (e: Exception) {
                viewModel.updateSteps(0)
            }
        }
    }
}
