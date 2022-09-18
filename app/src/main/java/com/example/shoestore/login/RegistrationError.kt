package com.example.shoestore.login

sealed class RegistrationError(override val message: String): Error() {
    object UsedEmail: RegistrationError("Email is used.")

    object InvalidEmail: RegistrationError("Email is invalid.")

    object WeakPassword: RegistrationError("Password is weak.")
}