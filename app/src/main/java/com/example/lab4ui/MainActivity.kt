package com.example.lab4ui

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.lab04ui.TimePickerFragment
import com.example.lab4ui.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    var subject: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        showDropdown()
    }

    private fun showDropdown() {
        binding.autoCompleteTextView.setText("Select Subject")
        val sub = resources.getStringArray(R.array.subjectName_array)
        val arrayAdapter = ArrayAdapter(this, R.layout.dropdown_item, sub)
        binding.autoCompleteTextView.setAdapter(arrayAdapter)
        binding.autoCompleteTextView.setOnItemClickListener { parent, _, position, _ ->
            subject = parent.getItemAtPosition(position) as String
            //Show Toast
            Toast.makeText(applicationContext, subject, Toast.LENGTH_LONG).show()

        }
    }

    fun showTimePickerDialog(v: View){
        val newTimePickerFragment = TimePickerFragment()
        newTimePickerFragment.show(supportFragmentManager, "Time Picker")
    }

    fun showDetail(v:View){
        binding.textShow.text = "ID: ${binding.editTextId.text}\n"+
                "Name: ${binding.editTextName.text}\n" +
                "Subject: $subject\nTime: ${binding.textTime.text}"
    }

    fun reset(v:View){
        binding.editTextId.text?.clear()
        binding.editTextName.text?.clear()
        showDropdown()
        binding.autoCompleteTextView.clearFocus()
        binding.textTime.text="_ _:_ _"
        binding.textShow.text="Show detail"
    }
}

