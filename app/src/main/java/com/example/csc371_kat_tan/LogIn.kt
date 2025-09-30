package com.example.csc371_kat_tan

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import android.widget.Toast
import androidx.compose.animation.slideIn
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.TextStyle
import com.example.csc371_kat_tan.ui.theme.Blue1
import com.example.csc371_kat_tan.ui.theme.FugazOne
import com.example.csc371_kat_tan.ui.theme.Gray1
import java.util.regex.Pattern
import com.example.csc371_kat_tan.ui.theme.Purple1
import kotlin.jvm.java

// Creates the Log In Activity
class LogIn : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MaterialTheme {
                LogIn(LocalContext.current)
            }
        }
    }
}

// Log In Function
@Composable
fun LogIn(context: Context) {
    // Creating states for textfields
    val emailState = remember { mutableStateOf("") }
    val passwordState = remember { mutableStateOf("") }

    // Create states for errors
    val emailError = remember { mutableStateOf("") }
    val passwordError = remember { mutableStateOf("") }

    // Function to validate the email format
    fun isValidEmail(email: String): Boolean {
        val emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$"
        return Pattern.compile(emailRegex).matcher(email).matches()
    }

    // Function to validate login credentials
    fun validateLogin(): Boolean {
        if (emailState.value.isEmpty() || passwordState.value.isEmpty()) return false
        val sharedPreferences = context.getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
        val storedEmail = sharedPreferences.getString("user_email", "")
        val storedPassword = sharedPreferences.getString("user_password", "")

        if (emailState.value != storedEmail || passwordState.value != storedPassword) {
            Toast.makeText(context, "Invalid credentials", Toast.LENGTH_SHORT).show()
            return false
        }
        return true

    }

    // UI for Log In Page
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Gray1),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            modifier = Modifier.paddingFromBaseline(bottom = 20.dp).align(Alignment.CenterHorizontally),
            text = "Log In",
            fontSize = 30.sp,
            fontFamily = FugazOne,
        )

        // Email Field
        OutlinedTextField(
            value = emailState.value,
            onValueChange = { emailState.value = it },
            label = { Text("Email") },
            isError = emailError.value.isNotEmpty(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Purple1,
                unfocusedBorderColor = Color.Black,
                errorBorderColor = Color.Red
            )

        )
        if (emailError.value.isNotEmpty()) {
            Text(
                text = emailError.value,
                color = Color.Red,
                style = TextStyle(fontSize = 12.sp),
                modifier = Modifier.padding(start = 16.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Password Field
        OutlinedTextField(
            value = passwordState.value,
            onValueChange = { passwordState.value = it },
            label = { Text("Password") },
            isError = passwordError.value.isNotEmpty(),
            visualTransformation = PasswordVisualTransformation(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Purple1,
                unfocusedBorderColor = Color.Black,
                errorBorderColor = Color.Red
            )

        )
        if (passwordError.value.isNotEmpty()) {
            Text(
                text = passwordError.value,
                color = Color.Red,
                style = TextStyle(fontSize = 12.sp),
                modifier = Modifier.padding(start = 16.dp)
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Log In Button
        Button(
            onClick = {
                // Validate login
                if (validateLogin()) {
                    // If login is successful, navigate to the next screen
                    Toast.makeText(context, "Log In successful", Toast.LENGTH_SHORT).show()

                    // Go to Homepage after Login
                    context.startActivity(Intent(context, Homepage::class.java))
                }
                else {
                    Toast.makeText(context, "Invalid Credentials", Toast.LENGTH_SHORT).show()
                }
            },
            colors = ButtonDefaults.buttonColors(containerColor = Blue1),
            modifier = Modifier.padding(20.dp).width(200.dp).height(50.dp)
        ) {
            Text("Log In", color = Color.Black, fontSize = 18.sp)
        }

        Box(
           modifier = Modifier.width(140.dp).height(120.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.marubike),
                contentDescription = "Maru Bike",
                modifier = Modifier
                    .aspectRatio(1f) // Set the aspect ratio to maintain original proportions
                    .align(Alignment.BottomCenter).width(120.dp).height(80.dp), // Align the image to the center
                contentScale = ContentScale.Fit // Show full image
            )

        }

    }
}

@Preview(showBackground = true)
@Composable
fun LogInScreenPreview() {
    LogIn(context = LocalContext.current)
}

