package com.example.notes_raul.domain

import javax.inject.Inject

class GetNoteUseCase @Inject constructor(
    private val noteListRepository: NoteListRepository
) {


    fun getNote(noteId: Int): Note {
        return noteListRepository.getNote(noteId)
    }
}