package com.example.notes_raul.di

import androidx.lifecycle.ViewModel
import com.example.notes_raul.presentation.MainViewModel
import com.example.notes_raul.presentation.NoteItemViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    fun bindMainViewModel(viewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(NoteItemViewModel::class)
    fun bindNoteItemViewModel(viewModel: NoteItemViewModel): ViewModel
}