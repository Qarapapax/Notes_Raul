package com.example.notes_raul.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "notes")
data class NoteDBModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val text: String,
    val date: String,
)
