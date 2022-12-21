package com.example.notes_raul.data

import com.example.notes_raul.domain.Note
import javax.inject.Inject

class NoteListMapper @Inject constructor() {

    fun mapEntityToDBModel(note: Note) = NoteDBModel(
        id = note.id,
        text = note.text,
        date = note.date
    )

    fun mapDBModelToEntity(noteDBModel: NoteDBModel) = Note(
        id = noteDBModel.id,
        text = noteDBModel.text,
        date = noteDBModel.date
    )

    fun mapListDBModelToEntity(list: List<NoteDBModel>) = list.map {
        mapDBModelToEntity(it)
    }
}