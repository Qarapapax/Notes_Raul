package com.example.notes_raul.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.notes_raul.R
import com.example.notes_raul.domain.Note

class NoteListAdapter : ListAdapter<Note, NoteItemViewHolder>(NoteItemDiffCallBack()) {

    var onNoteClickListener: ((Note) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteItemViewHolder  {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.notes,
            parent,
            false
        )
        return NoteItemViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: NoteItemViewHolder, position: Int) {
        val note = getItem(position)
        viewHolder.view.setOnClickListener {
            onNoteClickListener?.invoke(note)
        }
        viewHolder.tvText.text = note.text
        viewHolder.tvDate.text = note.date.toString()
    }
}
