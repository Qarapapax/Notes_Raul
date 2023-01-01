package com.example.notes_raul.di

import android.app.Application
import com.example.notes_raul.data.AppDataBase
import com.example.notes_raul.data.NoteListRepositoryImpl
import com.example.notes_raul.data.NotesDao
import com.example.notes_raul.domain.NoteListRepository
import dagger.Binds
import dagger.Module
import dagger.Provides


@Module
interface DataModule {

    @ApplicationScope
    @Binds
    fun bindNoteListRepository(impl: NoteListRepositoryImpl): NoteListRepository

    companion object {

        @ApplicationScope
        @Provides
        fun provideNotesDao(
            application: Application
        ): NotesDao {
            return AppDataBase.getDBase(application).getDao()
        }
    }
}