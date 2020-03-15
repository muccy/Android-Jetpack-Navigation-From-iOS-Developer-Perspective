package it.melive.android.navigation.basic.master_detail


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import it.melive.android.navigation.basic.*
import kotlinx.android.synthetic.main.fragment_master.*

/**
 * A simple [Fragment] subclass.
 */
class MasterFragment : Fragment() {
    private val viewModel: ViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_master, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        nextButton.setOnClickListener {
            val direction =
                MasterFragmentDirections.actionMasterFragmentToDetailFragment(
                    message = "It's me!",
                    title = "Detail Screen"
                )
            findNavController().navigate(direction)
        }

        goToModalButton.setOnClickListener {
            val directions =
                WizardDirections.actionGlobalWizard()
            findNavController().navigate(directions)
        }

        openAlertButton.setOnClickListener {
            val directions = AlertDialogFragmentDirections.actionGlobalAlertDialogFragment(title = "May I have your attention, please?", message = "Will the real Slim Shady please stand up?")
            findNavController().navigate(directions)
        }

        openSheetButton.setOnClickListener {
            val directions = BottomSheetDialogFragmentDirections.actionGlobalBottomSheetDialogFragment(text = "And anytime you feel the pain\n" +
                    "Hey Jude, refrain\n" +
                    "Don't carry the world upon your shoulders\n" +
                    "For well you know that it's a fool\n" +
                    "Who plays it cool\n" +
                    "By making his world a little colder\n" +
                    "Na-na-na, na, na\n" +
                    "Na-na-na, na")
            findNavController().navigate(directions)
        }

        observeDetailResult()
        observeViewModel()
    }

    private fun observeDetailResult() {
        findNavController().currentBackStackEntry?.savedStateHandle?.getLiveData<String>(
            DetailFragment.RESULT_KEY)?.observe(
            viewLifecycleOwner, Observer {
                resultTextView.text = it
            })
    }

    private fun observeViewModel() {
        viewModel.steps.observe(viewLifecycleOwner, Observer {
            stepsTextView.text = "Steps count: $it"
        })

        viewModel.wizardOutcome.observe(viewLifecycleOwner, Observer {
            if (it.isNullOrEmpty()) {
                wizardTextView.text = "Wizard not called yet"

            }
            else {
                wizardTextView.text = "Wizard produced: \"$it\""
            }
        })
    }
}
