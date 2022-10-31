package com.example.notes_raul.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface NotesDao {

    @Query("SELECT * FROM notes")
    fun getNoteList(): LiveData<List<NoteDBModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addNote(noteDBModel: NoteDBModel)

    @Query("DELETE FROM notes WHERE id=:noteItemId")
    fun deleteNote(noteItemId: Int)

    @Query("SELECT * FROM notes WHERE id=:noteItemId LIMIT 1")
    fun getNote(noteItemId: Int): NoteDBModel
}