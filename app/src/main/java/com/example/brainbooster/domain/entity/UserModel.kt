package com.example.brainbooster.domain.entity

data class UserModel(
    var imageid: String = "",
    val nickname: String = "",
    val score_math: Int = 0,
    var score_memory: Int = 0
) {

    constructor() : this("", "", 0, 0)
}
