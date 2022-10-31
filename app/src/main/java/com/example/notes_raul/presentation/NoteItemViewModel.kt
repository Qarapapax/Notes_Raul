package com.example.notes_raul.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.notes_raul.data.NoteListRepositoryImpl
import com.example.notes_raul.domain.AddNoteUseCase
import com.example.notes_raul.domain.EditNoteUseCase
import com.example.notes_raul.domain.GetNoteUseCase
import com.example.notes_raul.domain.Note

class NoteItemViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = NoteListRepositoryImpl(application)
    private val getNoteUseCase = GetNoteUseCase(repository)
    private val addNoteUseCase = AddNoteUseCase(repository)
    private val editNoteUseCase = EditNoteUseCase(repository)

    private var _errorInputText = MutableLiveData<Boolean>()
    val errorInputText: LiveData<Boolean>
        get() = _errorInputText

    private var _errorInputDate = MutableLiveData<Boolean>()
    val errorInputDate: LiveData<Boolean>
        get() = _errorInputDate

    private val _noteItem = MutableLiveData<Note>()
    val noteItem: LiveData<Note>
        get() = _noteItem

    private val _shouldCloseScreen = MutableLiveData<Unit>()
    val shouldCloseScreen: LiveData<Unit>
        get() = _shouldCloseScreen

    fun getNote(noteId: Int) {
        val item = getNoteUseCase.getNote(noteId)
        _noteItem.value = item
    }

    fun addNote(inputText: String?, inputDate: String?) {
        val text = parseText(inputText)
        val date = parseDate(inputDate)
        val fieldsValid = validateInput(text, date)
        if (fieldsValid) {
            val noteItem = Note(text, date)
            addNoteUseCase.addNote(noteItem)
            _shouldCloseScreen.value = Unit
        }
    }

    fun editNote(inputText: String?, inputDate: String?) {
        val text = parseText(inputText)
        val date = parseDate(inputDate)
        val fieldsValid = validateInput(text, date)
        if (fieldsValid) {
            _noteItem.value?.let {
                val item = it.copy(text = text, date = date)
                editNoteUseCase.editNote(item)
                _shouldCloseScreen.value = Unit
            }
        }
    }

    private fun parseText(inputText: String?): String {
        return inputText?.trim() ?: ""
    }

    private fun parseDate(inputDate: String?): String {
        return inputDate?.trim() ?: ""
    }

    private fun validateInput(text: String, date: String): Boolean {
        var result = true
        if (text.isBlank()) {
            _errorInputText.value = true
            result = false
        }
        if (date.isBlank()) {
            result = false
            _errorInputDate.value = true
        }
        return result
    }

    fun resetErrorInputText() {
        _errorInputText.value = false
    }

    fun resetErrorInputDate() {
        _errorInputDate.value = false
    }
}