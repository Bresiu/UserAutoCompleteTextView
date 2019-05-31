package com.bresiu.commenttest

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity

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
