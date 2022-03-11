package com.android.sharedpreferences

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    lateinit var button: Button
    lateinit var text: EditText
    lateinit var text1: EditText
    lateinit var checkBox: CheckBox

    var count : Int = 0

    var name:String? = null
    var message:String? = null
    var checkbox:Boolean? = null

    private lateinit var  sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button = findViewById(R.id.button1)
        text = findViewById(R.id.editTextTextPersonName)
        text1  = findViewById(R.id.editTextTextMultiLine)
        checkBox = findViewById(R.id.checkBox)

        button.setOnClickListener {
            count++
            button.setText("" + count)
        }
    }

    override fun onPause() {
        super.onPause()

        saveData()

    }

    private fun saveData(){
        sharedPreferences = this.getSharedPreferences("saveData", Context.MODE_PRIVATE)

        name = text.text.toString()
        message = text1.text.toString()
        checkbox = checkBox.isChecked

        val editor = sharedPreferences.edit()

        editor.putString("key name", name)
        editor.putString("key message", message)
        editor.putInt("key count", count)
        editor.putBoolean("key checkBox",checkbox!!)

        editor.apply()

        Toast.makeText(applicationContext,"Your data is saved",Toast.LENGTH_SHORT).show()
    }

    private fun retrieveData(){

        sharedPreferences = this.getSharedPreferences("saveData", Context.MODE_PRIVATE)

        name = sharedPreferences.getString("key name", null)
        message = sharedPreferences.getString("key message", null)
        count = sharedPreferences.getInt("key count", 0)
        checkbox = sharedPreferences.getBoolean("key checkBox", false)

        text.setText(name)
        text1.setText(message)
        button.setText("" + count)
        checkBox.isChecked = checkbox!!
    }

    override fun onResume() {
        super.onResume()

        retrieveData()
    }
}