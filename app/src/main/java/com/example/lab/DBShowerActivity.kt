package com.example.lab

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_dbshower.*

class DBShowerActivity : AppCompatActivity() {
    var adapter : ChoicesAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dbshower)
        adapter = ChoicesAdapter(this@DBShowerActivity)
        ListDB.adapter = adapter

        button_clearDB.setOnClickListener{
            DatabaseHelper.getInstance(this@DBShowerActivity).deleteAllChoices()
            Toast.makeText(this, "DB is cleared", Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    class ChoicesAdapter() : BaseAdapter(){
        var list: ArrayList<Choice>? = null
        var context : Context? = null

        constructor(context: Context?) : this(){
            this.list = DatabaseHelper.getInstance(context!!).getAllChoices()
            this.context = context
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            var view : View? = convertView
            if(view == null){
                view = View.inflate(context, R.layout.item_choice, null)
            }
            val txtDate : TextView = view?.findViewById(R.id.itemChoiceTxtDate) as TextView
            val txtFont : TextView = view.findViewById(R.id.itemChoiceTxtFont) as TextView
            val txtText : TextView = view.findViewById(R.id.itemChoiceTxtText) as TextView
            val imgDelete : ImageView = view.findViewById(R.id.itemChoiceImgDelete) as ImageView
            txtDate.text = list?.get(position)?.date
            txtFont.text = list?.get(position)?.font
            txtText.text = list?.get(position)?.text

            imgDelete.setOnClickListener {
                DatabaseHelper.getInstance(context!!).deleteChoice(list?.get(position)?.id)
                list?.removeAt(position)
                notifyDataSetChanged()
            }

            return view
        }

        override fun getItem(position: Int): Choice? {
            return list?.get(position)
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getCount(): Int {
            return list!!.size
        }

    }
}
