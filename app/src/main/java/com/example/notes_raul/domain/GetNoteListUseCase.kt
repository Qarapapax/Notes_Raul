package com.example.notes_raul.domain

import androidx.lifecycle.LiveData

class GetNoteListUseCase(private val noteListRepository: NoteListRepository) {
    fun getNoteList():LiveData<List<Note>> {
        return noteListRepository.getNoteList()
    }
}