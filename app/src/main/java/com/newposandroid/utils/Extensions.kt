package com.newposandroid.utils

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.newposandroid.R
import java.text.NumberFormat
import java.util.Locale

fun View.hide() {
    visibility = View.GONE
}

fun View.show() {
    visibility = View.VISIBLE
}

fun Fragment.toast(context: Context, msg: String, isLong: Boolean) {
    if (isLong) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
    } else {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }
}

fun ImageView.glide(url: String?, progressDrawable: CircularProgressDrawable) {
    val options = RequestOptions()
        .placeholder(progressDrawable)
        .error(R.drawable.ic_broken_image)

    Glide.with(context)
        .setDefaultRequestOptions(options)
        .load(url)
        .into(this)
}

fun placeHolderProgressBar(context: Context): CircularProgressDrawable {
    return CircularProgressDrawable(context).apply {
        strokeWidth = 8f
        centerRadius = 40f
        start()
    }
}

fun Int.toCent(): String {
    if (this.toString().length == 1) {
        return this.toString().let { "0$it" }
    }
    return this.toString()
}

fun Context.showProgressDialog(message: String): Dialog {
    val dialog = Dialog(this)
    dialog.setContentView(R.layout.progress_bar)

    val progressBar = dialog.findViewById<ProgressBar>(R.id.progressBar)
    val progressText = dialog.findViewById<TextView>(R.id.textViewProgressBar)

    progressText.text = message
    progressBar.isIndeterminate = true

    dialog.setCancelable(false)
    dialog.show()

    dialog.window?.decorView?.postDelayed({
        dialog.dismiss()
    }, Constants.PROGRESS_BAR_DURATION.toLong())

    return dialog
}

fun <T> Context.navigateAndClear(clazz: Class<T>) {
    val intent = Intent(this, clazz).apply {
        addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
        addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
    }
    startActivity(intent)
}

fun Any.convertRupiah(): String {
    val localId = Locale("in", "ID")
    val formatter = NumberFormat.getCurrencyInstance(localId).apply {
        maximumFractionDigits = 0
    }
    val strFormat = formatter.format(this)
    return strFormat.replaceRange(2,2, " ").orEmpty().trimEnd { it == '.' }.trimEnd { it == ',' }
}