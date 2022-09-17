package com.example.shoestore.login

import androidx.lifecycle.ViewModel

/**
 * The view model of [LoginFragment].
 */
class LoginViewModel : ViewModel() {
    /**
     * A map from each email address to its password.
     */
    private val accounts = mutableMapOf<String, String>()

    /**
     * @return `true` if [account] is registered.
     */
    fun verifyLogin(account: Account) =
        accounts[account.emailAddress] == account.password

    /**
     * Registers the given account.
     *
     * @throws [RegistrationError.UsedEmailAddress] if
     * the email address of [account] is already used.
     *
     * @throws [RegistrationError.InvalidEmail] if
     * the email address of [account] is invalid.
     *
     * @throws [RegistrationError.WeakPassword] if
     * the password of [account] is weak.
     */
    fun register(account: Account) {
        if (accounts.containsKey(account.emailAddress))
            throw RegistrationError.UsedEmailAddress
        if (!account.hasValidEmailAddress)
            throw RegistrationError.InvalidEmail
        if (!account.hasStrongPassword)
            throw RegistrationError.WeakPassword
        accounts[account.emailAddress] = account.password
    }
}