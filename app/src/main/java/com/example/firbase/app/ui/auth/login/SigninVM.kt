package com.example.firbase.app.ui.auth.login

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.firbase.app.data.model.login.SigninRequest
import com.example.firbase.app.data.model.login.SigninResponse
import com.example.firbase.app.data.model.signup.SignupRequest
import com.example.firbase.app.data.model.signup.SignupResponse
import com.example.firbase.app.data.repository.Repository
import com.example.firbase.app.utilities.DataState
import kotlinx.coroutines.launch

class SigninVM: ViewModel() {
    var signinState = MutableLiveData<DataState<SigninResponse?>?>(null)
    var email = mutableStateOf("")
    var password = mutableStateOf("")
    var repository = Repository()

    fun signin() {
        viewModelScope.launch {
            signinState.value = DataState.Loading
            signinState.value = repository.signIn(SigninRequest(email.value, password.value))
        }
    }
}