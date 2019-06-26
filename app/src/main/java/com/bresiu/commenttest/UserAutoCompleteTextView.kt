package com.bresiu.commenttest

import android.content.Context
import android.text.TextUtils
import android.text.method.QwertyKeyListener
import android.util.AttributeSet
import android.widget.MultiAutoCompleteTextView


class UserAutoCompleteTextView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : MultiAutoCompleteTextView(context, attrs, defStyleAttr) {
    private lateinit var tokenizer: Tokenizer

    override fun replaceText(text: CharSequence) {
        clearComposingText()
        val end = selectionEnd
        val start = tokenizer.findTokenStart(getText(), end) - 1
        val editable = getText()
        val original = TextUtils.substring(editable, start, end)
        QwertyKeyListener.markAsReplaced(editable, start, end, original)
        editable.replace(start, end, tokenizer.terminateToken(text))
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

    override fun convertSelectionToString(selectedItem: Any?): CharSequence {
        if (selectedItem is User) {
            return selectedItem.name
        }
        return super.convertSelectionToString(selectedItem)
    }
}