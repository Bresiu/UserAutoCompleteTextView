package com.bresiu.commenttest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hendraanggrian.appcompat.socialview.Mention
import com.hendraanggrian.appcompat.widget.MentionArrayAdapter
import com.hendraanggrian.appcompat.widget.SocialAutoCompleteTextView
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.RecyclerView
import com.linkedin.android.spyglass.suggestions.interfaces.Suggestible
import com.linkedin.android.spyglass.tokenization.QueryToken
import com.linkedin.android.spyglass.tokenization.impl.WordTokenizer
import com.linkedin.android.spyglass.tokenization.impl.WordTokenizerConfig
import com.linkedin.android.spyglass.tokenization.interfaces.QueryTokenReceiver
import com.linkedin.android.spyglass.ui.MentionsEditText
import kotlinx.android.synthetic.main.activity_main.*
import java.util.ArrayList
import android.opengl.ETC1.getHeight
import android.text.Selection.getSelectionStart
import android.text.Layout




class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val users = getUsers()
        val adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, users)
        val textView = findViewById<UserAutoCompleteTextView>(R.id.countries_list)
        textView.setTokenizer(UserMentionTokenizer())
        textView.setAdapter(adapter)
    }
}
