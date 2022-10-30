package com.example.notes_raul.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.notes_raul.domain.Note
import com.example.notes_raul.domain.NoteListRepository
import java.lang.RuntimeException

object NoteListRepositoryImpl : NoteListRepository {

    private val noteListLD = MutableLiveData<List<Note>>()
    private val noteList = mutableListOf<Note>()

    private var autoIncrementID = 0

    init {
        for (i in 0 until 0) {
            val item = Note("Text $i", "$i")
            addNote(item)
        }
    }

    override fun addNote(note: Note) {
        if (note.id == Note.UNDEFINED_ID) {
            note.id = autoIncrementID++
        }
        noteList.add(note)
        updateList()
    }

    override fun deleteNote(note: Note) {
        noteList.remove(note)
        updateList()
    }

    override fun editNote(note: Note) {
        val oldElement = getNote(note.id)
        noteList.remove(oldElement)
        addNote(note)
    }

    override fun getNote(noteId: Int): Note {
        return noteList.find {
            it.id == noteId
        } ?: throw RuntimeException("Element with id $noteId not found")
    }

    override fun getNoteList(): LiveData<List<Note>> {
        return noteListLD
    }

    private fun updateList() {
        noteListLD.value = noteList.toList()
    }


}