package com.example.carDock.debug

import android.util.Log as BaseLog


class Log {

    companion object {

        operator fun invoke(text: String) {
            val deBug = true
            val tag = "composeExample"

            if (!deBug) return
            BaseLog.i(tag, text)
        }

    }

}