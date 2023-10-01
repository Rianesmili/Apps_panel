package com.example.apppan.feature.inscription

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.apppan.data.Inscription
import com.example.apppan.data.InscriptionReponse
import com.example.apppan.repository.InscriptionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class InscriptionViewModel @Inject constructor(private val repository: InscriptionRepository) : ViewModel() {

    private val _name = MutableLiveData<String>()
    private val _email = MutableLiveData<String>()
    private val _phone= MutableLiveData<String>()

    val name: LiveData<String> get() = _name
    val email: LiveData<String> get() = _email
    val telephone: LiveData<String> get() = _phone

    fun updateNom(value: String) {
        _name.value = value
    }

    fun updateEmail(value: String) {
        _email.value = value
    }

    fun updateTelephone(value: String) {
        _phone.value = value
    }

    suspend fun registerUser(): InscriptionReponse {
        val registrationModel = Inscription(
            name = _name.value ?: "",
            email = _email.value ?: "",
            phone = _phone.value ?: ""
        )
        return repository.registerUser(registrationModel)
    }
}
