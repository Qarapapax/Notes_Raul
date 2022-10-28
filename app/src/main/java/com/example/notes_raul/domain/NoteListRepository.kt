package com.example.notes_raul.domain

import androidx.lifecycle.LiveData

interface NoteListRepository {
    fun addNote(note: Note)

    fun deleteNote(note: Note)

    fun editNote(note: Note)

    fun getNoteList(): LiveData<List<Note>>

    fun getNote(noteId: Int): Note
}