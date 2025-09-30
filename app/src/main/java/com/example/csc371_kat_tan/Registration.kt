package com.example.csc371_kat_tan

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.csc371_kat_tan.ui.theme.FugazOne
import com.example.csc371_kat_tan.ui.theme.Pink1
import com.example.csc371_kat_tan.ui.theme.Purple1

// Runs the Activity
class Registration : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MaterialTheme {
                Registration(LocalContext.current)
            }
        }
    }
}

//Allows users to register for an account
@Composable
fun Registration(context: Context) {

    // Gets the viewmodel to store user data
    val registrationViewModel: RegisterViewModel = viewModel()

    // Bind UI elements to the ViewModel state
    val firstName = registrationViewModel.firstName.value
    val lastName = registrationViewModel.lastName.value
    val dob = registrationViewModel.dob.value
    val email = registrationViewModel.email.value
    val password = registrationViewModel.password.value
    val confirmPassword = registrationViewModel.confirmPassword.value

    // For errors in textfields
    val firstNameError = registrationViewModel.firstNameError.value
    val lastNameError = registrationViewModel.lastNameError.value
    val emailError = registrationViewModel.emailError.value
    val passwordError = registrationViewModel.passwordError.value
    val confirmPasswordError = registrationViewModel.confirmPasswordError.value

    // UI For Registering
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFE9EBEA))
    ) {

        // Column to hold composables
        Column(
            modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Text("Registration", fontSize = 30.sp, fontFamily = FugazOne)

            // Sets colors of textfield borders when not in use and in use
            val textFieldColors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Purple1,
                unfocusedBorderColor = Color.Black,
                focusedLabelColor = Purple1,
                unfocusedLabelColor = Color.Black
            )

            // First Name Field
            OutlinedTextField(
                value = firstName,
                onValueChange = { registrationViewModel.firstName.value = it },
                label = { Text("First Name") },
                colors = textFieldColors,
                isError = firstNameError.isNotEmpty(),
                modifier = Modifier.padding(16.dp)
            )
            if (firstNameError.isNotEmpty()) {
                Text(
                    text = firstNameError,
                    color = Color.Red,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(start = 5.dp)
                )
            }

            // Last Name Field
            OutlinedTextField(
                value = lastName,
                onValueChange = { registrationViewModel.lastName.value = it },
                label = { Text("Last Name") },
                colors = textFieldColors,
                isError = lastNameError.isNotEmpty(),
                modifier = Modifier.padding(16.dp)
            )
            if (lastNameError.isNotEmpty()) {
                Text(
                    text = lastNameError,
                    color = Color.Red,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(start = 5.dp)
                )
            }

            // Date of Birth Field (Optional)
            OutlinedTextField(
                value = dob,
                onValueChange = { registrationViewModel.dob.value = it },
                label = { Text("Date of Birth") },
                colors = textFieldColors,
                modifier = Modifier.padding(16.dp)
            )

            // Email Field
            OutlinedTextField(
                value = email,
                onValueChange = { registrationViewModel.email.value = it },
                label = { Text("Email") },
                colors = textFieldColors,
                isError = emailError.isNotEmpty(),
                modifier = Modifier.padding(16.dp)
            )
            if (emailError.isNotEmpty()) {
                Text(
                    text = emailError,
                    color = Color.Red,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(start = 5.dp)
                )
            }

            // Password Field
            OutlinedTextField(
                value = password,
                onValueChange = { registrationViewModel.password.value = it },
                label = { Text("Password") },
                colors = textFieldColors,
                isError = passwordError.isNotEmpty(),
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.padding(16.dp)
            )
            if (passwordError.isNotEmpty()) {
                Text(
                    text = passwordError,
                    color = Color.Red,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(start = 5.dp)
                )
            }

            // Confirm Password Field
            OutlinedTextField(
                value = confirmPassword,
                onValueChange = { registrationViewModel.confirmPassword.value = it },
                label = { Text("Re-Type Password") },
                colors = textFieldColors,
                isError = confirmPasswordError.isNotEmpty(),
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.padding(16.dp).paddingFromBaseline(bottom = 20.dp)
            )
            if (confirmPasswordError.isNotEmpty()) {
                Text(
                    text = confirmPasswordError,
                    color = Color.Red,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(start = 5.dp)
                )
            }

            // Register Button
            Button(
                onClick = {
                    // Reset error messages before validation
                    registrationViewModel.resetErrors()

                    // Validate all fields
                    val isValid = registrationViewModel.validateInputs()

                    if (isValid) {

                        registrationViewModel.saveUserCredentials(context, email.toString(), password.toString())

                            // Provide feedback to the user
                        Toast.makeText(context, "Success!", Toast.LENGTH_SHORT).show()

                        // Navigate to MainActivity
                        context.startActivity(Intent(context, MainActivity::class.java))
                    } else {
                        Toast.makeText(context, "Errors in Field", Toast.LENGTH_SHORT).show()
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = Pink1),
                modifier = Modifier.padding(20.dp).width(200.dp).height(50.dp)

            ) {
                Text("Register", color = Color.Black, fontSize = 20.sp)
            }
        }
    }
}
