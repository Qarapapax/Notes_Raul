package com.example.notes_raul.presentation

import androidx.lifecycle.ViewModel
import com.example.notes_raul.domain.DeleteNoteUseCase
import com.example.notes_raul.domain.EditNoteUseCase
import com.example.notes_raul.domain.GetNoteListUseCase
import com.example.notes_raul.domain.Note
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val getNoteListUseCase: GetNoteListUseCase,
    private val deleteNoteUseCase: DeleteNoteUseCase,
    private val editNoteUseCase: EditNoteUseCase
) : ViewModel() {

    val noteList = getNoteListUseCase.getNoteList()

    fun deleteNote(note: Note) {
        deleteNoteUseCase.deleteNote(note)
    }
}