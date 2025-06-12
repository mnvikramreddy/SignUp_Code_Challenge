package com.example.codechallenge.model

import android.graphics.Bitmap

data class UserDetails(
    var profile: Bitmap? = null,
    var firstName: String = "",
    var email: String = "",
    var password: String = "",
    var website: String = ""
)
