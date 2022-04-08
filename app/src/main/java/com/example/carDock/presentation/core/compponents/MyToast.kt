package com.example.carDock.presentation.core.compponents

import android.widget.Toast

class MyToast(
    private val msg: String = "",
    private val len: Int = Toast.LENGTH_LONG
) {
    fun show() {
        Toast.makeText(com.example.carDock.AppModule.instance, msg, len).show()
    }
}