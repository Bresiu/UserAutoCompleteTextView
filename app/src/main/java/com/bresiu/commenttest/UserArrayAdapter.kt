package com.bresiu.commenttest

import android.widget.TextView
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ImageView


class ChatUserAdapter(
        private val layoutInflater: LayoutInflater,
        private val layout: Int,
        private val users: List<User>
) : ArrayAdapter<User>(layoutInflater.context, layout, users) {

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    internal class ViewHolder {
        var userName: TextView? = null
        var avatar: ImageView? = null
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val viewHolder: ViewHolder
        var rowView: View? = convertView
        if (rowView == null) {
            rowView = layoutInflater.inflate(layout, null, true)
            viewHolder = ViewHolder()
            viewHolder.userName = rowView.findViewById(R.id.name)
            viewHolder.avatar = rowView.findViewById(R.id.avatar)
            rowView.tag = viewHolder
        } else {
            viewHolder = rowView.tag as ViewHolder
        }
        val item = users[position]
        viewHolder.userName?.text = item.name
        viewHolder.avatar?.setImageResource(item.avatar)
        return rowView!!
    }
}