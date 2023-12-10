package com.example.homeworksixteen.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homeworksixteen.Request
import com.example.homeworksixteen.network.Network
import com.example.homeworksixteen.register.RegistrationState
import com.example.homeworksixteen.responses.ResponseLogIn
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

sealed class LogInState {
    object Loading : LogInState()
    data class Success(val responseLogIn: ResponseLogIn) : LogInState()
    data class Failed(val errorMessage: String) : LogInState()
}

class LogInViewModel: ViewModel() {

    private val _logInFlow = MutableSharedFlow<LogInState>(replay = 1)
    val logInFlow: SharedFlow<LogInState> = _logInFlow.asSharedFlow()


    fun logIn(email: String, password: String) {
        val request = Request(email, password)
        viewModelScope.launch {
            try {
                _logInFlow.emit( LogInState.Loading)

                val response = Network.loginService().logIn(request)

                if (response.isSuccessful) {
                    val activeUser = response.body()!!
                    _logInFlow.emit( LogInState.Success(activeUser))
                } else {
                    _logInFlow.emit(LogInState.Failed("Request failed"))
                }
            } catch (e: Exception) {
                _logInFlow.emit( LogInState.Failed(e.message ?: "Unknown error"))
            }
        }
    }

}