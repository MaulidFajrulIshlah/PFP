package com.geminiboy.pfp.view.util

import android.app.Dialog
import android.content.Context
import android.view.Window
import com.geminiboy.pfp.R

class LoadingDialog(context: Context) {
    private val dialog: Dialog = Dialog(context)

    init {
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.loading_item)
    }

    fun startLoading() {
        dialog.show()
    }

    fun dismissLoading() {
        dialog.dismiss()
    }
}