package com.example.notes_raul.domain

import javax.inject.Inject

class EditNoteUseCase@Inject constructor(
    private val noteListRepository: NoteListRepository
) {

    fun editNote(note: Note) {
        noteListRepository.editNote(note)
    }
}