package com.example.notes_raul.presentation

import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.notes_raul.R
import com.example.notes_raul.data.AppDataBase
import com.example.notes_raul.domain.Note
import com.google.android.material.textfield.TextInputLayout
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class NoteItemActivity : AppCompatActivity() {

//    private lateinit var viewModel: NoteItemViewModel
//
//    private lateinit var tilText: TextInputLayout
//    private lateinit var tilDate: TextInputLayout
//    private lateinit var edText: EditText
//    private lateinit var tvDate: TextView
//    private lateinit var buttonPickDate: Button
//    private lateinit var buttonSave: Button
//
//    private var screenMode = MODE_UNKNOWN
//    private var noteItemId = Note.UNDEFINED_ID
//
//    @Inject
//    lateinit var viewModelFactory: ViewModelFactory
//
//    private val component by lazy {
//        (application as NoteApplication).component
//    }
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        component.inject(this)
//
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_note_item)
//
//        parseIntent()
//        viewModel = ViewModelProvider(this, viewModelFactory)[NoteItemViewModel::class.java]
//        initViews()
//        addTextChangeListeners()
//        launchRightMode()
//        observeViewModel()
//        datePicker()
//    }
//
//    private fun datePicker() {
//        val myCalendar = Calendar.getInstance()
//        val datePicker = DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
//            myCalendar.set(Calendar.YEAR, year)
//            myCalendar.set(Calendar.MONTH, month)
//            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
//            updateLabel(myCalendar)
//        }
//        buttonPickDate.setOnClickListener {
//            DatePickerDialog(
//                this,
//                datePicker,
//                myCalendar.get(Calendar.YEAR),
//                myCalendar.get(Calendar.MONTH),
//                myCalendar.get(Calendar.DAY_OF_MONTH)
//            ).show()
//        }
//    }
//
//    private fun observeViewModel() {
//        viewModel.errorInputDate.observe(this) {
//            val message = if (it) {
//                getString(R.string.error_input_date)
//            } else {
//                null
//            }
//            tilDate.error = message
//        }
//        viewModel.errorInputText.observe(this) {
//            val message = if (it) {
//                getString(R.string.error_input_text)
//            } else {
//                null
//            }
//            tilText.error = message
//        }
//        viewModel.shouldCloseScreen.observe(this) {
//            finish()
//        }
//    }
//
//    private fun launchRightMode() {
//        when (screenMode) {
//            MODE_EDIT -> launchEditMode()
//            MODE_ADD -> launchAddMode()
//        }
//    }
//
//    private fun addTextChangeListeners() {
//        edText.addTextChangedListener(object : TextWatcher {
//            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
//
//            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//                viewModel.resetErrorInputText()
//            }
//
//            override fun afterTextChanged(p0: Editable?) {}
//
//        })
//        tvDate.addTextChangedListener(object : TextWatcher {
//            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
//
//            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//                viewModel.resetErrorInputDate()
//            }
//
//            override fun afterTextChanged(p0: Editable?) {}
//
//        })
//    }
//
//    private fun launchEditMode() {
//        viewModel.getNote(noteItemId)
//        viewModel.noteItem.observe(this) {
//            edText.setText(it.text)
//            tvDate.text = it.date.toString()
//        }
//        buttonSave.setOnClickListener {
//            viewModel.editNote(edText.text?.toString(), tvDate.text?.toString())
//        }
//    }
//
//    fun launchAddMode() {
//        buttonSave.setOnClickListener {
//            viewModel.addNote(edText.text?.toString(), tvDate.text?.toString())
//        }
//    }
//
//    private fun parseIntent() {
//        if (!intent.hasExtra(EXTRA_SCREEN_MODE)) {
//            throw RuntimeException("Param screen mode is absent")
//        }
//        val mode = intent.getStringExtra(EXTRA_SCREEN_MODE)
//        if (mode != MODE_EDIT && mode != MODE_ADD) {
//            throw RuntimeException("Unknown screen mode $mode")
//        }
//        screenMode = mode
//        if (screenMode == MODE_EDIT) {
//            if (!intent.hasExtra(EXTRA_NOTE_ITEM_ID)) {
//                throw RuntimeException("Param note item is absent")
//            }
//            noteItemId = intent.getIntExtra(EXTRA_NOTE_ITEM_ID, Note.UNDEFINED_ID)
//        }
//    }
//
//    private fun initViews() {
//        tilText = findViewById(R.id.til_note_text)
//        tilDate = findViewById(R.id.til_date)
//        edText = findViewById(R.id.et_note_text)
//        tvDate = findViewById(R.id.tv_date)
//        buttonPickDate = findViewById(R.id.pick_date_button)
//        buttonSave = findViewById(R.id.save_button)
//    }
//
//    companion object {
//        private const val EXTRA_SCREEN_MODE = "extra_mode"
//        private const val EXTRA_NOTE_ITEM_ID = "extra_note_item_mode"
//        private const val MODE_EDIT = "mode_edit"
//        private const val MODE_ADD = "mode_add"
//        private const val MODE_UNKNOWN = ""
//
//        fun newIntentAdd(context: Context): Intent {
//            val intent = Intent(context, NoteItemActivity::class.java)
//            intent.putExtra(EXTRA_SCREEN_MODE, MODE_ADD)
//            return intent
//        }
//
//        fun newIntentEdit(context: Context, noteItemID: Int): Intent {
//            val intent = Intent(context, NoteItemActivity::class.java)
//            intent.putExtra(EXTRA_SCREEN_MODE, MODE_EDIT)
//            intent.putExtra(EXTRA_NOTE_ITEM_ID, noteItemID)
//            return intent
//        }
//
//    }
//
//    private fun updateLabel(myCalendar: Calendar) {
//        val myFormat = "dd-MM-yyyy"
//        val sdf = SimpleDateFormat(myFormat, Locale.UK)
//        tvDate.text = sdf.format(myCalendar.time)
//    }
}
