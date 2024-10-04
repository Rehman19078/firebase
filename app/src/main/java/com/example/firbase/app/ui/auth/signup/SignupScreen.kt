package com.example.firbase.app.ui.auth.signup

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
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.firbase.app.utilities.DataState

@Composable
fun SignUpScreen(signupVM:signupVM = viewModel()) {
    val signupState by signupVM.signUpState.observeAsState(null)
    val context = LocalContext.current
    var email by signupVM.email
    var password by signupVM.password
    LaunchedEffect(signupState) {

        when (val signup=signupState) {
            is DataState.Loading->{
                Toast.makeText(context, "loading", Toast.LENGTH_SHORT).show()
            }
            is DataState.Success -> {
                Toast.makeText(context, "Resgistration Successfull", Toast.LENGTH_SHORT).show()
            }
            is DataState.Error -> {
                Toast.makeText(context, signup.errorMessage, Toast.LENGTH_SHORT).show()

            }
            else -> Unit
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 20.dp, end = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
Text(text = "Sign UP Screen", fontWeight = FontWeight.Bold, fontSize = 20.sp)
        Spacer(modifier = Modifier.height(100.dp))
        Text(text = "Email", modifier = Modifier.align(Alignment.Start))
        Spacer(modifier = Modifier.height(10.dp))
        TextField(value = email, onValueChange = {
            signupVM.email.value = it
        }, modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(30.dp))
        Text(text = "Password", modifier = Modifier.align(Alignment.Start))
        Spacer(modifier = Modifier.height(10.dp))
        TextField(value = password, onValueChange = {
            signupVM.password.value= it
        },modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(40.dp))
        Button(onClick = {
            signupVM.signup()
        }) {
            Text(text = "SignUp")
        }

    }
}
