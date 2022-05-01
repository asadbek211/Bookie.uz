package com.bizmiz.bookieuz.utils

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager


fun View.showSoftKeyboard() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    this.requestFocus()
    imm.showSoftInput(this, 0)
}
