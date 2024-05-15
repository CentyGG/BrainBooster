package com.example.brainbooster.Domain

import java.lang.reflect.Constructor

data class UserModel(
    val name: String = "",
    val image_id: String = "",
    val score1: Int = 0,
    val score2: Int = 0
) {
    constructor() : this("", "", 0, 0)
}
