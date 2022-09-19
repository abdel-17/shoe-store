package com.example.shoestore.login

import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {
    /**
     * A map from each registered email address
     * to its password.
     */
    // TODO save accounts in a database
    private val registeredAccounts = mutableMapOf<String, String>()

    /**
     * Registers the given account.
     *
     * @throws [RegistrationError.UsedEmail] if
     * the email address of [account] is already used.
     *
     * @throws [RegistrationError.InvalidEmail] if
     * the email address of [account] is invalid.
     *
     * @throws [RegistrationError.WeakPassword] if
     * the password of [account] is weak.
     */
    fun register(account: Account) {
        if (registeredAccounts.containsKey(account.email))
            throw RegistrationError.UsedEmail
        if (!account.hasValidEmail)
            throw RegistrationError.InvalidEmail
        if (!account.hasStrongPassword)
            throw RegistrationError.WeakPassword
        registeredAccounts[account.email] = account.password
    }

    /**
     * @return `true` if [account] is registered
     * with the correct password.
     */
    fun isRegistered(account: Account) =
        registeredAccounts[account.email] == account.password
}