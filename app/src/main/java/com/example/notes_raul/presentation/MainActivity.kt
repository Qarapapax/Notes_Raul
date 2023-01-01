package com.example.notes_raul.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.notes_raul.R
import com.example.notes_raul.data.AppDataBase
import com.google.android.material.floatingactionbutton.FloatingActionButton
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    val mainFragment = MainFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction().replace(R.id.container, mainFragment).commit()
    }
}