package com.example.firbase.app.data.service.auth

import com.example.firbase.app.data.model.login.SigninRequest
import com.example.firbase.app.data.model.login.SigninResponse
import com.example.firbase.app.data.model.signup.SignupRequest
import com.example.firbase.app.data.model.signup.SignupResponse
import com.example.firbase.app.utilities.DataState

interface FirbaseService {
    suspend fun signUp(signUpRequest: SignupRequest) : DataState<SignupResponse?>
    suspend fun signIn(signinRequest: SigninRequest) : DataState<SigninResponse?>
}