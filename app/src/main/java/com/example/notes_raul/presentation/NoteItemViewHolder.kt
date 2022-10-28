package com.example.notes_raul.presentation

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.notes_raul.R

class NoteItemViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
    val tvText = view.findViewById<TextView>(R.id.tv_text)
    val tvDate = view.findViewById<TextView>(R.id.tv_date)
}
