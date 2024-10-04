package com.example.firbase.app.ui.auth.signup

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.firbase.app.data.model.signup.SignupRequest
import com.example.firbase.app.data.model.signup.SignupResponse
import com.example.firbase.app.data.repository.Repository
import com.example.firbase.app.utilities.DataState
import kotlinx.coroutines.launch

class signupVM : ViewModel() {
    var signUpState = MutableLiveData<DataState<SignupResponse?>?>(null)
    var name = mutableStateOf("")
    var email = mutableStateOf("")
    var password = mutableStateOf("")
    var repository = Repository()

    fun signup() {
        viewModelScope.launch {
            signUpState.value = DataState.Loading
            signUpState.value = repository.signUp(SignupRequest(email.value, password.value))
        }
    }
}