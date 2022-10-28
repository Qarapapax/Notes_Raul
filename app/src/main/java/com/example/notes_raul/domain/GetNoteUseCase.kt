package com.example.notes_raul.domain

class GetNoteUseCase(private val noteListRepository: NoteListRepository) {
    fun getNote(noteId: Int): Note {
        return noteListRepository.getNote(noteId)
    }
}