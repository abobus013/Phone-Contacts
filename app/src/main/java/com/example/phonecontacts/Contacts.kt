package com.example.phonecontacts

import android.graphics.Bitmap

data class Contacts(
    val name: String,
    val number: String,
    var img: Bitmap?
)
