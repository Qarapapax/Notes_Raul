package com.example.notes_raul.domain

import javax.inject.Inject

class DeleteNoteUseCase @Inject constructor(
    private val noteListRepository: NoteListRepository
) {

    fun deleteNote(note: Note) {
        noteListRepository.deleteNote(note)
    }
}