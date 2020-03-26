package com.example.lab1

import android.graphics.Typeface
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_input.*
import kotlinx.android.synthetic.main.fragment_output.*
import java.util.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        /*editText.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(textView.typeface != Typeface.MONOSPACE){
                    if(editText.text.length <= 120) {
                        textView.text = s
                    }
                    else{
                        editText.setText(editText.text.take(120))
                        editText.setSelection(120)
                        Toast.makeText(this@MainActivity, "Only 120 symbols", Toast.LENGTH_LONG).show()
                    }
                }
            }
        })*/
    }
    fun acceptOk(view: View){
        val firstFragment = FragmentOutput()
        val manager = supportFragmentManager
        val transaction = manager.beginTransaction()
        transaction.replace(R.id.fragment_default_picture,firstFragment)
        transaction.addToBackStack(null)
        transaction.commit()
        /*val id = fonts.checkedRadioButtonId
        if(id != -1){
            if(editText.text.any()) {
                val radio:RadioButton = findViewById(id)
                val newTypeface = Typeface.create(radio.text.toString().toLowerCase(Locale.ROOT), Typeface.NORMAL)
                if (textView.typeface != newTypeface){
                    textView.typeface = newTypeface
                    textView.text = editText.text
                    editText.clearFocus()
                }
                else{
                    Toast.makeText(this, "Nothing changed", Toast.LENGTH_SHORT).show()
                }
            }
            else{
                Toast.makeText(this, "Enter text", Toast.LENGTH_SHORT).show()
            }
        }
        else{
            Toast.makeText(this, "Choose font", Toast.LENGTH_SHORT).show()
        }*/
    }
    fun cancel(view: View){
        val firstFragment = FragmentDefaultPicture()
        val manager = supportFragmentManager
        val transaction = manager.beginTransaction()
        transaction.replace(R.id.fragment_default_picture, firstFragment)
        transaction.addToBackStack(null)
        transaction.commit()
        /*editText.setText("")
        editText.clearFocus()
        textView.text = ""
        textView.typeface = Typeface.MONOSPACE
        fonts.clearCheck()*/
    }
}