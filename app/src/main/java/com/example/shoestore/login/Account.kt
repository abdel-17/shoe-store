package com.example.shoestore.login

import android.util.Patterns

data class Account(val email: String, val password: String) {
    /**
     * @return `true` if [email] is a valid email address.
     */
    val hasValidEmail
        get() = Patterns.EMAIL_ADDRESS.matcher(email).matches()

    /**
     * A password is considered strong if it
     * has at least 8 characters, one letter,
     * and one digit.
     *
     * @return `true` if [password] is a strong password.
     */
    val hasStrongPassword
        get() = password.length >= 8 &&
                password.any(Char::isLetter) &&
                password.any(Char::isDigit)
}
