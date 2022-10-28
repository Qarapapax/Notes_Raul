package com.example.notes_raul.domain

class EditNoteUseCase(private val noteListRepository: NoteListRepository) {
    fun editNote(note: Note) {
        noteListRepository.editNote(note)
    }
}