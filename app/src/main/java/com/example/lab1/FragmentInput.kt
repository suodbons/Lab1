package com.example.lab1

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_input.view.*

class FragmentInput : Fragment() {
    lateinit var comm: TextUpdater
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_input, container, false)
        comm = activity as TextUpdater
        rootView.editText.addTextChangedListener(object : TextWatcher {
           override fun afterTextChanged(s: Editable?) {
               if (rootView.editText.text.length <= 120) {
                   comm.passText(rootView.editText.text.toString())
               } else {
                   rootView.editText.setText(rootView.editText.text.take(120))
                   rootView.editText.setSelection(120)
               }
           }
           override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
           override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) { }
       })
        return rootView
    }
}
