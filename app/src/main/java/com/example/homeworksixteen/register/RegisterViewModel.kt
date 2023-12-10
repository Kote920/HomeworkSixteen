package com.example.homeworksixteen.register


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homeworksixteen.responses.ResponseRegister
import com.example.homeworksixteen.Request
import com.example.homeworksixteen.network.Network
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch


sealed class RegistrationState {
    object Loading : RegistrationState()
    data class Success(val responseRegister: ResponseRegister) : RegistrationState()
    data class Failed(val errorMessage: String) : RegistrationState()
}

class RegisterViewModel : ViewModel() {

    private val _registrationFlow = MutableSharedFlow<RegistrationState>(replay = 1)
    val registrationFlow: SharedFlow<RegistrationState> = _registrationFlow.asSharedFlow()

//    val request = RegistrationRequest("eve.holt@reqres.in", "pistol")

    fun register(email: String, password: String) {
        val request = Request(email, password)
        viewModelScope.launch {
            try {
                _registrationFlow.emit( RegistrationState.Loading)

                val response = Network.registerService().register(request)

                if (response.isSuccessful) {
                    val registeredUser = response.body()!!
                    _registrationFlow.emit( RegistrationState.Success(registeredUser))
                } else {
                    _registrationFlow.emit( RegistrationState.Failed("Request failed"))
                }
            } catch (e: Exception) {
                _registrationFlow.emit( RegistrationState.Failed(e.message ?: "Unknown error"))
            }
        }
    }
}