package com.example.notes_raul.presentation

import android.app.Application
import com.example.notes_raul.di.DaggerApplicationComponent

class NoteApplication : Application() {

    val component by lazy {
        DaggerApplicationComponent.factory().create(this)
    }
}