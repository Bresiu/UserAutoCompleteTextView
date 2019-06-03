package com.bresiu.commenttest

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val avatarProvider = AvatarProvider()
        val users = getUsers(avatarProvider)
        val adapter = ChatUserAdapter(layoutInflater, R.layout.user_row, users)
        val textView = findViewById<UserAutoCompleteTextView>(R.id.countries_list)
        val color = ContextCompat.getColor(this, R.color.colorAccent)
        textView.setTokenizer(UserMentionTokenizer(color))
        textView.setAdapter(adapter)
    }
}
