package com.example.notes_raul.presentation

import androidx.recyclerview.widget.DiffUtil
import com.example.notes_raul.domain.Note

class NoteItemDiffCallBack : DiffUtil.ItemCallback<Note>() {
    override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
        return newItem == oldItem
    }

}