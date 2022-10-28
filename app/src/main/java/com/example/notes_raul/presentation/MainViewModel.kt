package com.example.notes_raul.presentation

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.notes_raul.data.NoteListRepositoryImpl
import com.example.notes_raul.domain.DeleteNoteUseCase
import com.example.notes_raul.domain.EditNoteUseCase
import com.example.notes_raul.domain.GetNoteListUseCase
import com.example.notes_raul.domain.Note

class MainViewModel : ViewModel() {

    private val repository = NoteListRepositoryImpl

    private val getNoteListUseCase = GetNoteListUseCase(repository)
    private val deleteNoteUseCase = DeleteNoteUseCase(repository)
    private val editNoteUseCase = EditNoteUseCase(repository)

    val noteList = getNoteListUseCase.getNoteList()

    fun deleteNote(note: Note) {
        deleteNoteUseCase.deleteNote(note)
    }

}