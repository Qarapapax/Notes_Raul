package com.example.notes_raul.di

import android.app.Application
import com.example.notes_raul.presentation.MainActivity
import com.example.notes_raul.presentation.MainFragment
import com.example.notes_raul.presentation.NoteItemActivity
import dagger.Binds
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component(
    modules = [
        DataModule::class,
        ViewModelModule::class
    ]
)
interface ApplicationComponent {

    fun inject(fragment: MainFragment)

    fun inject(activity: NoteItemActivity)

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance application: Application
        ): ApplicationComponent
    }
}