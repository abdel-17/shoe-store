package com.example.shoestore.login

sealed class RegistrationError(override val message: String): Error() {
    object UsedEmailAddress: RegistrationError("Email address is used.")

    object InvalidEmail: RegistrationError("Email address is invalid.")

    object WeakPassword: RegistrationError("Password is weak.")
}