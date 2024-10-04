package com.example.firbase.app.data.repository

import com.example.firbase.app.data.model.login.SigninRequest
import com.example.firbase.app.data.model.login.SigninResponse
import com.example.firbase.app.data.model.signup.SignupRequest
import com.example.firbase.app.data.model.signup.SignupResponse
import com.example.firbase.app.data.service.auth.FirbaseService
import com.example.firbase.app.utilities.DataState
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await

class Repository : FirbaseService {
    private val firebaseAuth = FirebaseAuth.getInstance()
    override suspend fun signUp(signUpRequest: SignupRequest): DataState<SignupResponse?> {
        return try {
            val response = firebaseAuth.createUserWithEmailAndPassword(
                signUpRequest.email,
                signUpRequest.password
            ).await()
            DataState.Success(
                SignupResponse(response.user?.uid ?: "")
            )
        } catch (e: Exception) {
            DataState.Error(e.message.toString())
        }

    }

    override suspend fun signIn(signinRequest: SigninRequest): DataState<SigninResponse?> {
  return try {
      val response = firebaseAuth.signInWithEmailAndPassword(
          signinRequest.email,
          signinRequest.password
      ).await()
      DataState.Success(
          SigninResponse(response.user?.uid ?: "")
      )
  } catch (e: Exception) {
      DataState.Error(e.message.toString())
  }
    }
}