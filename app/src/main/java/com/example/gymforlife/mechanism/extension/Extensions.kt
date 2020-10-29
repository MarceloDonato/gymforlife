package com.example.gymforlife.mechanism.extension

import android.app.Activity
import android.graphics.Paint
import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableString
import android.text.Spanned
import android.text.style.StyleSpan
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import java.text.Normalizer
import java.text.SimpleDateFormat
import java.util.*

fun <T1 : Any, T2 : Any, R : Any> safeLet(p1: T1?, p2: T2?, block: (T1, T2) -> R?): R? {
    return if (p1 != null && p2 != null) block(p1, p2) else null
}

fun <T1 : Any, T2 : Any, T3 : Any, R : Any> safeLet(
    p1: T1?, p2: T2?, p3: T3?,
    block: (T1, T2, T3) -> R?
): R? {
    return if (p1 != null && p2 != null && p3 != null) block(p1, p2, p3) else null
}

fun <T1 : Any, T2 : Any, T3 : Any, T4 : Any, R : Any> safeLet(
    p1: T1?, p2: T2?, p3: T3?, p4: T4?,
    block: (T1, T2, T3, T4) -> R?
): R? {
    return if (p1 != null && p2 != null && p3 != null && p4 != null) block(p1, p2, p3, p4) else null
}

fun <T1 : Any, T2 : Any, T3 : Any, T4 : Any, T5 : Any, R : Any> safeLet(
    p1: T1?, p2: T2?, p3: T3?,
    p4: T4?, p5: T5?,
    block: (T1, T2, T3, T4, T5) -> R?
): R? {
    return if (p1 != null && p2 != null && p3 != null && p4 != null && p5 != null) block(
        p1, p2, p3,
        p4, p5
    ) else null
}

fun SpannableString.formatItalicWords(boldWordsList: List<String>?): Spannable {
    return this.apply {
        boldWordsList?.forEach { word ->
            val index = this.indexOf(word)
            if (index >= 0) {
                this.setSpan(
                    StyleSpan(Typeface.ITALIC), index, index + word.length,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                )
            }
        }
    }
}

fun EditText.setDrawableEnd(drawable: Int) {
    this.setCompoundDrawablesWithIntrinsicBounds(0, 0, drawable, 0)
}

private val REGEX_UNACCENT = "\\p{InCombiningDiacriticalMarks}+".toRegex()

fun String.normalize() = run {
    val temp = Normalizer.normalize(this.toLowerCase(Locale.getDefault()), Normalizer.Form.NFD)
    REGEX_UNACCENT.replace(temp, "")
}

fun List<String>.toLongList(): List<Long> {
    return this.map {
        it.toLong()
    }
}

fun List<String>.toIntList(): List<Int> {
    return this.map {
        it.toInt()
    }
}

fun Date.toFormat(format: String) = SimpleDateFormat(format, Locale.getDefault()).format(this)
    .toString()

fun String.toDate(format: String): Date = SimpleDateFormat(format, Locale.getDefault()).parse(this)

fun TextView.setTextUnderline() {
    paintFlags = paintFlags or Paint.UNDERLINE_TEXT_FLAG
}

fun Date.toImageName() = "${SimpleDateFormat("yyyyMMddHHmm", Locale.getDefault()).format(this)}.png"

fun EditText.get() = text.toString().trim()

fun Activity.toast(message: String) = Toast.makeText(this, message, Toast.LENGTH_LONG).show()

fun View.toast(message: String) = Toast.makeText(context, message, Toast.LENGTH_LONG).show()

