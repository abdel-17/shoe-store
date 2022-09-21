package com.example.shoestore.login

import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {
    /**
     * A map from each registered email address
     * to its password.
     *
     * Note: The data is lost when this
     * view model is destroyed.
     */
    // TODO save accounts in an encrypted database.
    private val registeredAccounts = mutableMapOf<String, String>()

    /**
     * Tries to register [account].
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
    fun registerAccount(account: Account) {
        if (registeredAccounts.containsKey(account.email))
            throw RegistrationError.UsedEmail
        if (!account.hasValidEmail)
            throw RegistrationError.InvalidEmail
        if (!account.hasStrongPassword)
            throw RegistrationError.WeakPassword
        registeredAccounts[account.email] = account.password
    }

    /**
     * @return `true` if [account] had been registered
     * before with [registerAccount].
     */
    fun isRegistered(account: Account) =
        registeredAccounts[account.email] == account.password
}