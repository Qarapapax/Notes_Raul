package com.example.notes_raul.presentation

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.notes_raul.R

class NoteItemActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_item)
        val mode = intent.getStringExtra(EXTRA_SCREEN_MODE)
        Log.d("NoteItemActivity", mode.toString())
    }

    companion object {
        private const val EXTRA_SCREEN_MODE = "extra_mode"
        private const val EXTRA_NOTE_ITEM_ID = "extra_note_item_mode"
        private const val MODE_EDIT = "mode_edit"
        private const val MODE_ADD = "mode_add"

        fun newIntentAdd(context: Context): Intent {
            val intent = Intent(context, NoteItemActivity::class.java)
            intent.putExtra(EXTRA_SCREEN_MODE, MODE_ADD)
            return intent
        }

        fun newIntentEdit(context: Context, noteItemID: Int): Intent {
            val intent = Intent(context, NoteItemActivity::class.java)
            intent.putExtra(EXTRA_SCREEN_MODE, MODE_EDIT)
            intent.putExtra(EXTRA_NOTE_ITEM_ID, noteItemID)
            return intent
        }

    }
}