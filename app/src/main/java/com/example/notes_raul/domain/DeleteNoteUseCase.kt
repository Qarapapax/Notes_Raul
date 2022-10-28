package com.example.notes_raul.domain

class DeleteNoteUseCase(private val noteListRepository: NoteListRepository) {
    fun deleteNote(note: Note) {
        noteListRepository.deleteNote(note)
    }
}