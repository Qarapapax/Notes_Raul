package com.example.notes_raul.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.example.notes_raul.data.NoteListRepositoryImpl
import com.example.notes_raul.domain.DeleteNoteUseCase
import com.example.notes_raul.domain.EditNoteUseCase
import com.example.notes_raul.domain.GetNoteListUseCase
import com.example.notes_raul.domain.Note

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = NoteListRepositoryImpl(application)

    private val getNoteListUseCase = GetNoteListUseCase(repository)
    private val deleteNoteUseCase = DeleteNoteUseCase(repository)
    private val editNoteUseCase = EditNoteUseCase(repository)

    val noteList = getNoteListUseCase.getNoteList()

    fun deleteNote(note: Note) {
        deleteNoteUseCase.deleteNote(note)
    }
}