package com.bresiu.commenttest

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.util.Log
import androidx.appcompat.widget.AppCompatMultiAutoCompleteTextView
import android.text.Editable
import android.text.Spannable
import android.text.TextWatcher
import android.text.style.ForegroundColorSpan
import android.widget.Filter
import android.widget.MultiAutoCompleteTextView
import java.lang.IndexOutOfBoundsException


class UserAutoCompleteTextView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : MultiAutoCompleteTextView(context, attrs, defStyleAttr) {
    private lateinit var tokenizer: Tokenizer

    init {
        addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
                if (s.endsWith('@')) s.setSpan(ForegroundColorSpan(Color.GREEN), s.length - 1, s.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            }
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
        })
    }

    override fun setTokenizer(t: Tokenizer) {
        tokenizer = t
        super.setTokenizer(tokenizer)
    }

    override fun enoughToFilter(): Boolean {
        val text = text
        val end = selectionEnd
        if (end < 0) return false
        val start = tokenizer.findTokenStart(text, end)
        return end - start >= threshold && start > 0 && text[start - 1] == '@'
    }
}