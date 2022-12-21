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

    private lateinit var viewModel: MainViewModel
    private lateinit var noteListAdapter: NoteListAdapter

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val component by lazy {
        (application as NoteApplication).component
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        component.inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupRecyclerView()
        viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]
        viewModel.noteList.observe(this) {
            noteListAdapter.submitList(it)
        }
        val buttonAddNote = findViewById<FloatingActionButton>(R.id.button_add_note)
        buttonAddNote.setOnClickListener {
            val intent = NoteItemActivity.newIntentAdd(this)
            startActivity(intent)
        }
    }

    private fun setupRecyclerView() {
        val rvNoteList = findViewById<RecyclerView>(R.id.rv_note_list)
        noteListAdapter = NoteListAdapter()
        rvNoteList.adapter = noteListAdapter

        setupClickListener()
        setupSwipeListener(rvNoteList)
    }

    private fun setupSwipeListener(rvNoteList: RecyclerView) {
        val callBack = object :
            ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val item = noteListAdapter.currentList[viewHolder.adapterPosition]
                viewModel.deleteNote(item)
            }
        }
        val itemTouchHelper = ItemTouchHelper(callBack)
        itemTouchHelper.attachToRecyclerView(rvNoteList)
    }

    private fun setupClickListener() {
        noteListAdapter.onNoteClickListener = {
            val intent = NoteItemActivity.newIntentEdit(this, it.id)
            startActivity(intent)
        }
    }

}