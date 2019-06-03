package com.bresiu.commenttest

import android.text.Spannable
import android.text.SpannableString
import android.text.Spanned
import android.text.TextUtils
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.widget.MultiAutoCompleteTextView
import androidx.annotation.ColorInt

class UserMentionTokenizer(@ColorInt val color: Int) : MultiAutoCompleteTextView.Tokenizer {

    override fun findTokenStart(text: CharSequence, cursor: Int): Int {
        var i = cursor
        while (i > 0 && text[i - 1] != '@') i--
        return when {
            i < 1 || (text[i - 1] != '@') -> cursor
            text[i - 1] == '@' -> i
            else -> cursor
        }
    }

    override fun findTokenEnd(text: CharSequence, cursor: Int): Int {
        var i = cursor
        val len = text.length
        while (i < len) if (text[i].isWhitespace()) return i else i++
        return len
    }

    override fun terminateToken(text: CharSequence): CharSequence {
        var i = text.length
        while (i > 0 && text[i - 1].isWhitespace()) i--
        return when {
            i > 0 && text[i - 1].isWhitespace() -> text
            text is Spanned -> {
                val sp = SpannableString("$text ")
                TextUtils.copySpansFrom(text, 0, text.length, Any::class.java, sp, 0)
                sp
            }
            else -> getSpannable(text)
        }
    }

    private fun getSpannable(text: CharSequence): SpannableString {
        Log.d("BRS", "getSpannable: $text")
        return SpannableString("@$text ").apply { setSpan(ForegroundColorSpan(color), 0, text.length + 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE) }
    }
}