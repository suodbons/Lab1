package com.example.lab1

import android.os.Bundle
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment


class MainActivity : AppCompatActivity(), TextUpdater{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if(savedInstanceState == null){
            supportFragmentManager.beginTransaction().add(R.id.fragment_input, FragmentInput()).commit()
        }
    }
    override fun passText(input_text: String) {
        if(supportFragmentManager.fragments.size > 1){
            val outputFragment =  supportFragmentManager.fragments[1]
            if (outputFragment is FragmentOutput){
                outputFragment.arguments?.putString("input_txt", input_text)
                supportFragmentManager.beginTransaction().detach(outputFragment).attach(outputFragment).commit()
            }
        }
    }
    fun updateFragment(radio: RadioButton, text: String){
        var outputFragment = Fragment()
        if(supportFragmentManager.fragments.size > 1){
            outputFragment = supportFragmentManager.fragments[1]
        }
        if (outputFragment is FragmentOutput){
            if(outputFragment.arguments?.getString("input_radio_id") != radio.text.toString()){
                outputFragment.arguments?.putString("input_radio_id", radio.text.toString())
                supportFragmentManager.beginTransaction().detach(outputFragment).attach(outputFragment).commit()
            }
            else{
                message("Nothing changed")
            }
        }
        else{
            outputFragment = FragmentOutput.newInstance(text, radio.text.toString())
            supportFragmentManager.beginTransaction().replace(R.id.fragment_default_picture, outputFragment).commit()
        }
    }
    fun clear(){
        supportFragmentManager.beginTransaction().replace(R.id.fragment_default_picture, FragmentDefaultPicture()).commit()
    }
    fun message(message: String){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}