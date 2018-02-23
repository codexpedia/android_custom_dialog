package com.example.customdialog

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.app.AlertDialog
import android.widget.Toast

class MyFragmentDialog : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val args = arguments
        val title = args.getString("title", "")
        val message = args.getString("message", "")

        return AlertDialog.Builder(activity)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(android.R.string.yes) { dialog, which -> Toast.makeText(context, "OK", Toast.LENGTH_SHORT).show() }
                .setNegativeButton(android.R.string.no) { dialog, which -> Toast.makeText(context, "Cancel", Toast.LENGTH_SHORT).show() }
                .create()
    }
}