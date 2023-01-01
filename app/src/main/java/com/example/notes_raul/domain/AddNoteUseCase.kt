package com.example.notes_raul.domain

import javax.inject.Inject

class AddNoteUseCase @Inject constructor(
    private val noteListRepository: NoteListRepository
) {

    fun addNote(note: Note) {
        noteListRepository.addNote(note)
    }
}