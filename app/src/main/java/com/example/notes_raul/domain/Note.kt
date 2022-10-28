package com.example.notes_raul.domain

data class Note(
    val text: String,
    val date: String,
    var id: Int = UNDEFINED_ID
) {
    companion object {
        const val UNDEFINED_ID = -1
    }
}
