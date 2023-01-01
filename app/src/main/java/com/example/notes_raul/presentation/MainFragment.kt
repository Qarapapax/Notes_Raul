package com.example.notes_raul.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.notes_raul.R
import javax.inject.Inject


class MainFragment : Fragment(R.layout.fragment_main) {
    private lateinit var viewModel: MainViewModel
    private lateinit var noteListAdapter: NoteListAdapter

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val component by lazy {
        (requireContext().applicationContext as NoteApplication).component
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        component.inject(this)
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView(view)
        viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]
        viewModel.noteList.observe(viewLifecycleOwner) {
            noteListAdapter.submitList(it)
        }
//        val buttonAddNote = findViewById<FloatingActionButton>(R.id.button_add_note)
//        buttonAddNote.setOnClickListener {
//            val intent = NoteItemActivity.newIntentAdd(this)
//            startActivity(intent)
//        }

    }


    private fun setupRecyclerView(view: View) {
        val rvNoteList = view.findViewById<RecyclerView>(R.id.rv_note_list)
        noteListAdapter = NoteListAdapter()
        rvNoteList.adapter = noteListAdapter

      //  setupClickListener()
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

//    private fun setupClickListener() {
//        noteListAdapter.onNoteClickListener = {
//            val intent = NoteItemActivity.newIntentEdit(this, it.id)
//            startActivity(intent)
//        }
//    }
}