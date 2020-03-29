package com.example.lab1

import android.graphics.Typeface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_output.view.*
import java.util.*


class FragmentOutput : Fragment() {
    private var inputText: String? = ""
    private var inputFont: String? = "monospace"
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_output, container, false)
        inputText = arguments?.getString("input_txt")
        inputFont = arguments?.getString("input_radio_id")
        rootView.textView.text = inputText
        rootView.textView.typeface = Typeface.create(inputFont?.toLowerCase(Locale.ROOT), Typeface.NORMAL)
        return rootView
    }
    companion object {
        @JvmStatic
        fun newInstance(_inputText: String, _inputFont: String) : FragmentOutput {
            val fragment = FragmentOutput()
            val args = Bundle().apply{
                putString("input_txt", _inputText)
                putString("input_radio_id", _inputFont)
            }
            fragment.arguments = args
            return fragment
        }
    }
}
