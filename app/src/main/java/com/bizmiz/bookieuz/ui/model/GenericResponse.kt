package com.bizmiz.bookieuz.ui.model

data class GenericResponse<T>(
    var success: Boolean,
    var message: String,
    var data: T
)
