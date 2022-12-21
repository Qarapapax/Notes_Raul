package com.example.notes_raul.data

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.notes_raul.domain.Note
import com.example.notes_raul.domain.NoteListRepository
import javax.inject.Inject

class NoteListRepositoryImpl @Inject constructor(
    private val noteListDao: NotesDao,
    private val mapper: NoteListMapper
) : NoteListRepository {


    override fun addNote(note: Note) {
        noteListDao.addNote(mapper.mapEntityToDBModel(note))
    }

    override fun deleteNote(note: Note) {
        noteListDao.deleteNote(note.id)
    }

    override fun editNote(note: Note) {
        noteListDao.addNote(mapper.mapEntityToDBModel(note))
    }

    override fun getNote(noteId: Int): Note {
        val dbModel = noteListDao.getNote(noteId)
        return mapper.mapDBModelToEntity(dbModel)
    }

    override fun getNoteList(): LiveData<List<Note>> =
        Transformations.map(noteListDao.getNoteList()) {
            mapper.mapListDBModelToEntity(it)
        }
}