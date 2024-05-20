package com.example.brainbooster.ui.register

import com.example.brainbooster.Validator.RegistrationValidator
import org.junit.Test
import com.google.common.truth.Truth.assertThat

class RegistrValidationTest {
    @Test
    fun `login or password or mail is empty returns false`(){
        val result = RegistrationValidator.validateInput(
            "",
            "",
            "89166386901"
        )
        assertThat(result).isFalse()
    }
    @Test
    fun `name is not null`(){
        val result = RegistrationValidator.checkName(
            "mister"
        )
        assertThat(result).isTrue()
    }
    @Test
    fun `password contains latin letters and digits`(){
        val result = RegistrationValidator.checkPassword(
            "123456"
        )
        assertThat(result).isFalse()
    }
    @Test
    fun `mail is correct type`(){
        val result = RegistrationValidator.checkEmail(
            "normallogin@mail",
        )
        assertThat(result).isFalse()
    }
    @Test
    fun `correct input returns true`(){
        val result = RegistrationValidator.validateInput(
            "Legend",
            "normalpassword1242344567",
            "normal_email@mail.ru"
        )
        assertThat(result).isTrue()
    }
}