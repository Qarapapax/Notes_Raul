package com.example.notes_raul.presentation

import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.example.notes_raul.R
import com.example.notes_raul.domain.Note
import com.google.android.material.textfield.TextInputLayout
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class NoteItemFragment() : Fragment(R.layout.fragment_note_item) {

    private lateinit var viewModel: NoteItemViewModel

    private lateinit var tilText: TextInputLayout
    private lateinit var tilDate: TextInputLayout
    private lateinit var edText: EditText
    private lateinit var tvDate: TextView
    private lateinit var buttonPickDate: Button
    private lateinit var buttonSave: Button

    private var screenMode = MODE_UNKNOWN
    private var noteItemId = Note.UNDEFINED_ID

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val component by lazy {
        (requireContext().applicationContext as NoteApplication).component
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseParams()
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
        viewModel = ViewModelProvider(this, viewModelFactory)[NoteItemViewModel::class.java]
        initViews(view)
        addTextChangeListeners()
        launchRightMode()
        observeViewModel()
        datePicker()
    }

    private fun datePicker() {
        val myCalendar = Calendar.getInstance()
        val datePicker = DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
            myCalendar.set(Calendar.YEAR, year)
            myCalendar.set(Calendar.MONTH, month)
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            updateLabel(myCalendar)
        }
        buttonPickDate.setOnClickListener {
            DatePickerDialog(
                requireContext(),
                datePicker,
                myCalendar.get(Calendar.YEAR),
                myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)
            ).show()
        }
    }

    private fun observeViewModel() {
        viewModel.errorInputDate.observe(viewLifecycleOwner) {
            val message = if (it) {
                getString(R.string.error_input_date)
            } else {
                null
            }
            tilDate.error = message
        }
        viewModel.errorInputText.observe(viewLifecycleOwner) {
            val message = if (it) {
                getString(R.string.error_input_text)
            } else {
                null
            }
            tilText.error = message
        }
        viewModel.shouldCloseScreen.observe(viewLifecycleOwner) {
            activity?.onBackPressed()
        }
    }

    private fun launchRightMode() {
        when (screenMode) {
            MODE_EDIT -> launchEditMode()
            MODE_ADD -> launchAddMode()
        }
    }

    private fun addTextChangeListeners() {
        edText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                viewModel.resetErrorInputText()
            }

            override fun afterTextChanged(p0: Editable?) {}

        })
        tvDate.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                viewModel.resetErrorInputDate()
            }

            override fun afterTextChanged(p0: Editable?) {}

        })
    }

    private fun launchEditMode() {
        viewModel.getNote(noteItemId)
        viewModel.noteItem.observe(viewLifecycleOwner) {
            edText.setText(it.text)
            tvDate.text = it.date.toString()
        }
        buttonSave.setOnClickListener {
            viewModel.editNote(edText.text?.toString(), tvDate.text?.toString())
        }
    }

    fun launchAddMode() {
        buttonSave.setOnClickListener {
            viewModel.addNote(edText.text?.toString(), tvDate.text?.toString())
        }
    }

    private fun parseParams() {
        val args = requireArguments()
        if (!args.containsKey(SCREEN_MODE)) {
            throw RuntimeException("Param screen mode is absent $screenMode")
        }
        val mode = args.getString(SCREEN_MODE)
        if (mode != MODE_EDIT && mode != MODE_ADD) {
            throw RuntimeException("Unknown screen mode $mode")
        }
        screenMode = mode
        if (screenMode == MODE_EDIT) {
            if (!args.containsKey(NOTE_ITEM_ID)) {
                throw RuntimeException("Param note item ID is absent ")
            }
            noteItemId = args.getInt(NOTE_ITEM_ID, Note.UNDEFINED_ID)
        }
    }

    private fun initViews(view: View) {
        tilText = view.findViewById(R.id.til_note_text)
        tilDate = view.findViewById(R.id.til_date)
        edText = view.findViewById(R.id.et_note_text)
        tvDate = view.findViewById(R.id.tv_date)
        buttonPickDate = view.findViewById(R.id.pick_date_button)
        buttonSave = view.findViewById(R.id.save_button)
    }

    companion object {
        const val SCREEN_MODE = "extra_mode"
        const val NOTE_ITEM_ID = "extra_note_item_mode"
        const val MODE_EDIT = "mode_edit"
        const val MODE_ADD = "mode_add"
        const val MODE_UNKNOWN = ""

        fun newInstanceAddItem(): NoteItemFragment {
            return NoteItemFragment().apply {
                arguments = Bundle().apply {
                    putString(SCREEN_MODE, MODE_ADD)
                }
            }
        }

        fun newInstanceEditItem(noteItemId:Int): NoteItemFragment {
            return NoteItemFragment().apply {
                arguments = Bundle().apply {
                    putString(SCREEN_MODE, MODE_EDIT)
                    putInt(NOTE_ITEM_ID, noteItemId)
                }
            }
        }
    }


    private fun updateLabel(myCalendar: Calendar) {
        val myFormat = "dd-MM-yyyy"
        val sdf = SimpleDateFormat(myFormat, Locale.UK)
        tvDate.text = sdf.format(myCalendar.time)
    }
}