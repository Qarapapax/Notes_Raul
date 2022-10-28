package com.example.notes_raul.domain

class AddNoteUseCase(private val noteListRepository: NoteListRepository) {
    fun addNote(note: Note) {
        noteListRepository.addNote(note)
    }
}