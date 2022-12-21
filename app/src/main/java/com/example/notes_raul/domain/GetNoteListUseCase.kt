package com.example.notes_raul.domain

import androidx.lifecycle.LiveData
import javax.inject.Inject

class GetNoteListUseCase @Inject constructor(
    private val noteListRepository: NoteListRepository
) {

    fun getNoteList(): LiveData<List<Note>> {
        return noteListRepository.getNoteList()
    }
}