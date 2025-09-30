package com.example.csc371_kat_tan

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

// This class holds data
class RegisterViewModel : ViewModel() {

    // States for the registration fields
    val firstName = mutableStateOf("")
    val lastName = mutableStateOf("")
    val dob = mutableStateOf("")
    val email = mutableStateOf("")
    val password = mutableStateOf("")
    val confirmPassword = mutableStateOf("")

    // Used for errors in input
    val firstNameError = mutableStateOf("")
    val lastNameError = mutableStateOf("")
    val emailError = mutableStateOf("")
    val passwordError = mutableStateOf("")
    val confirmPasswordError = mutableStateOf("")


    // Validation of textfields
    fun validateInputs(): Boolean {
        var isValid = true

        // Validate First Name
        if (firstName.value.length < 3) {
            firstNameError.value = "First name must be at least 3 characters."
            isValid = false
        } else {
            firstNameError.value = ""
        }

        // Validate Last Name
        if (lastName.value.length < 2) {
            lastNameError.value = "Last name must be at least 2 characters."
            isValid = false
        } else {
            lastNameError.value = ""
        }

        // Validate Email
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email.value).matches()) {
            emailError.value = "Please enter a valid email address."
            isValid = false
        } else {
            emailError.value = ""
        }

        // Check password is valid
        if (password.value.length < 6) {
            passwordError.value = "Password must be at least 6 characters."
            isValid = false
        } else {
            passwordError.value = ""
        }

        // Check that passwords match
        if (password.value != confirmPassword.value) {
            confirmPasswordError.value = "Passwords do not match."
            isValid = false
        } else {
            confirmPasswordError.value = ""
        }

        return isValid
    }

    // Reset errors
    fun resetErrors() {
        firstNameError.value = ""
        lastNameError.value = ""
        emailError.value = ""
        passwordError.value = ""
        confirmPasswordError.value = ""
    }

    // Function to save user credentials to SharedPreferences
    fun saveUserCredentials(context: Context, email: String, password: String) {
        val sharedPreferences = context.getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("user_email", email)
        editor.putString("user_password", password)
        editor.apply()
    }

}
