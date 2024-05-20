package com.example.brainbooster.Validator

object RegistrationValidator {
    fun validateInput(name: String?, password: String?, email: String?): Boolean {
        return checkName(name) && checkPassword(password) && checkEmail(email)
    }

    fun checkName(name: String?): Boolean {
        return !name.isNullOrBlank()
    }

    fun checkPassword(password: String?): Boolean {
        return password?.let {
            it.length >= 6 && it.matches(Regex("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,}\$"))
        } ?: false
    }

    fun checkEmail(email: String?): Boolean {
        return email?.matches(Regex("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}\$"))
            ?: false
    }
}