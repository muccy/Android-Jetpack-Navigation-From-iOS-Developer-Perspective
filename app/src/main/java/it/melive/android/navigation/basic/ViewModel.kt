package it.melive.android.navigation.basic

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

class ViewModel(private val state : SavedStateHandle): ViewModel() {
    companion object {
        private val STEPS_KEY = "steps"
        private val WIZARD_OUTCOME_KEY = "wizardOutcome"
    }

    private val _steps = state.getLiveData(STEPS_KEY, 0)
    val steps: LiveData<Int> = _steps

    private val _wizardOutcome = state.getLiveData<String?>(WIZARD_OUTCOME_KEY, null)
    val wizardOutcome: LiveData<String?> = _wizardOutcome

    fun updateSteps(steps: Int) {
        if (_steps.value != steps) {
            _steps.value = steps
        }
    }

    fun finishWizardWith(name: String, surname: String) {
        _wizardOutcome.value = "$name $surname"
    }
}