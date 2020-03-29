package com.example.lab1

import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.fragment_input.*


class MainActivity : AppCompatActivity(), TextUpdater{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if(savedInstanceState == null){
            supportFragmentManager.beginTransaction().replace(R.id.fragment_input, FragmentInput()).commit()
            supportFragmentManager.beginTransaction().replace(R.id.fragment_default_picture, FragmentDefaultPicture()).commit()
        }
    }
    override fun passText(input_text: String) {
        var outputFragment =  supportFragmentManager.fragments[1]
        outputFragment.arguments?.putString("input_txt", input_text)
        supportFragmentManager.beginTransaction().detach(outputFragment).attach(outputFragment).commit()
    }

    fun acceptOk(view: View){
        val id = fonts.checkedRadioButtonId
        if(id != -1){
            if(editText.text.any()) {
                val radio:RadioButton = findViewById(id)
                var outputFragment = supportFragmentManager.fragments[1]
                if (outputFragment is FragmentOutput){
                    if(outputFragment.arguments?.getString("input_radio_id") != radio.text.toString()){
                        if(outputFragment.arguments?.getString("input_radio_id") != radio.text.toString()){
                            outputFragment.arguments?.putString("input_radio_id", radio.text.toString())
                        }
                        supportFragmentManager.beginTransaction().detach(outputFragment).attach(outputFragment).commit()
                    }
                    else{
                        Toast.makeText(this, "Nothing changed", Toast.LENGTH_SHORT).show()
                    }
                }
                else{
                    outputFragment = FragmentOutput.newInstance(editText.text.toString(), radio.text.toString())
                    supportFragmentManager.beginTransaction().replace(R.id.fragment_default_picture, outputFragment).commit()
                    supportFragmentManager.fragments[0].arguments?.apply {
                        putBoolean("update", true)
                    }
                    editText.clearFocus()
                }
            }
            else{
                Toast.makeText(this, "Enter text", Toast.LENGTH_SHORT).show()
            }
        }
        else{
            Toast.makeText(this, "Choose font", Toast.LENGTH_SHORT).show()
        }
    }
    fun cancel(view: View){
        if(editText.text.isNotEmpty() || fonts.checkedRadioButtonId != -1)  {
            if(editText.text.isNotEmpty())  {
                editText.text = null
                editText.clearFocus()
            }
            if(fonts.checkedRadioButtonId != -1){
                fonts.clearCheck()
            }
        }
        else{
            Toast.makeText(this, "Nothing to cancel", Toast.LENGTH_SHORT).show()
        }
        supportFragmentManager.beginTransaction().replace(R.id.fragment_default_picture, FragmentDefaultPicture()).commit()
    }
}