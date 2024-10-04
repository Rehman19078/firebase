package com.example.firbase.app.ui.auth.login

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.firbase.app.utilities.DataState

@SuppressLint("UnrememberedMutableState")
@Composable
fun LoginScreen(SigninVM:SigninVM = viewModel()) {
    val signinState by SigninVM.signinState.observeAsState(null)
    val context = LocalContext.current
    var email by SigninVM.email
    var password by SigninVM.password
    LaunchedEffect(signinState) {

        when (val signin=signinState) {
            is DataState.Loading->{
                Toast.makeText(context, "loading", Toast.LENGTH_SHORT).show()
            }
            is DataState.Success -> {
                Toast.makeText(context, "Login Successfull", Toast.LENGTH_SHORT).show()
            }
            is DataState.Error -> {
                Toast.makeText(context, signin.errorMessage, Toast.LENGTH_SHORT).show()

            }
            else -> Unit
        }
    }
    Column(
        modifier = Modifier.fillMaxSize().padding(start = 20.dp, end = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Login Screen", fontWeight = FontWeight.Bold, fontSize = 24.sp)
        Spacer(modifier = Modifier.height(100.dp))
        Text(text = "Email", modifier = Modifier.align(Alignment.Start))
        Spacer(modifier = Modifier.height(10.dp))
        TextField(value = email, onValueChange = {
            SigninVM.email.value = it
        }, modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(30.dp))
        Text(text = "Password", modifier = Modifier.align(Alignment.Start))
        Spacer(modifier = Modifier.height(10.dp))
        TextField(value = password, onValueChange = {
            SigninVM.password.value = it
        }, modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(50.dp))
        Button(onClick = {
            SigninVM.signin()
        }) {
            Text(text = "Login")
        }
    }
}