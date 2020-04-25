package com.example.lab

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_input.*
import kotlinx.android.synthetic.main.fragment_input.view.*

class FragmentInput : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_input, container, false)
        childFragmentManager.beginTransaction().add(R.id.fragment_default_picture, FragmentDefaultPicture()).commit()
        rootView.editText.addTextChangedListener(object : TextWatcher {
           override fun afterTextChanged(s: Editable?) {
               if (rootView.editText.text.length <= 120) {
                   (activity as TextUpdater).passText(rootView.editText.text.toString())
               } else {
                   rootView.editText.setText(rootView.editText.text.take(120))
                   rootView.editText.setSelection(120)
               }
           }
           override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
           override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) { }
       })

        rootView.button_ok.setOnClickListener{
            val id = fonts.checkedRadioButtonId
            if(id != -1){
                if(editText.text.any()) {
                    val radio: RadioButton = fonts.findViewById(id)
                    (activity as MainActivity).updateFragment(radio, editText.text.toString())
                    editText.clearFocus()
                }
                else{
                    (activity as MainActivity).message("Enter text")
                }
            }
            else{
                (activity as MainActivity).message("Choose font")
            }
        }

        rootView.button_cancel.setOnClickListener{
            if(editText.text.isNotEmpty() || fonts.checkedRadioButtonId != -1)  {
                if(editText.text.isNotEmpty())  {
                    editText.text = null
                    editText.clearFocus()
                }
                if(fonts.checkedRadioButtonId != -1){
                    fonts.clearCheck()
                    (activity as MainActivity).clear()
                }
            }
            else{
                (activity as MainActivity).message("Nothing to cancel")
            }
        }

        rootView.button_openDB.setOnClickListener{
            val checkEmpty = DatabaseHelper.getInstance((activity as MainActivity)).getAllChoices()
            if(checkEmpty.size > 0){
                val intent = Intent((activity as MainActivity), DBShowerActivity::class.java)
                startActivity(intent)

            }
            else{
                (activity as MainActivity).message("DB is empty")
            }
        }
        return rootView
    }
}
