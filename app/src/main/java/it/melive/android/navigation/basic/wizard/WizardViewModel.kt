package it.melive.android.navigation.basic.wizard

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

class WizardViewModel(private val state: SavedStateHandle): ViewModel() {
    companion object {
        private val NAME_KEY = "name"
        private val SURNAME_KEY = "surname"
    }

    private val _name = state.getLiveData<String>(NAME_KEY)
    private val _surname = state.getLiveData<String>(SURNAME_KEY)

    val name: LiveData<String> = _name
    val surname: LiveData<String> = _surname

    fun insertName(name: String) {
        if (name != _name.value) {
            _name.value = name.trim()
        }
    }

    fun insertSurname(surname: String) {
        if (surname != _surname.value) {
            _surname.value = surname.trim()
        }
    }
}