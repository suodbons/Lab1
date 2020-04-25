package com.example.lab

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context?, name: String?, factory: SQLiteDatabase.CursorFactory?, version:Int): SQLiteOpenHelper(context, name, factory, version) {
    companion object{
        private var instance : DatabaseHelper? = null
        fun getInstance(ctx:Context):DatabaseHelper{
            if(instance == null){
                instance = DatabaseHelper(ctx, "Choices", null, 1)
            }
            return instance!!
        }
    }

    val createSQL : String = "CREATE TABLE \"CHOICES_TABLE\"(\"id\" INTEGER PRIMARY KEY AUTOINCREMENT, \"DATE\" TEXT, \"FONT\" TEXT, \"TEXT\" TEXT)"

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(createSQL)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
    fun addChoice (font: String?, text:String?) : Long {
        val values = ContentValues()
        values.put("date", java.util.Calendar.getInstance().time.toString())
        values.put("font", font)
        values.put("text", text)
        return this.writableDatabase.insert("CHOICES_TABLE", null, values)
    }
    fun deleteChoice(id: Int?){
        this.writableDatabase.delete("CHOICES_TABLE", "\"id\"=?", arrayOf("$id"))
    }
    @SuppressLint("Recycle")
    fun deleteAllChoices(){
        val cursor : Cursor = this.writableDatabase.query("CHOICES_TABLE", arrayOf("\"id\"", "\"date\"", "\"font\"", "\"text\""), null, null, null, null, null)
        if(cursor.moveToFirst()){
            do{
                deleteChoice(cursor.getInt(0))
            }while(cursor.moveToNext())
        }
    }
    @SuppressLint("Recycle")
    fun getLastChoice() : Choice{
        val cursor : Cursor = this.writableDatabase.query("CHOICES_TABLE", arrayOf("\"id\"", "\"date\"", "\"font\"", "\"text\""), null, null, null, null, null)
        cursor.moveToLast()
        val choice = Choice()
        choice.id = cursor.getInt(0)
        choice.date = cursor.getString(1)
        choice.font = cursor.getString(2)
        choice.text = cursor.getString(3)
        return choice
    }
    @SuppressLint("Recycle")
    fun getAllChoices() : ArrayList<Choice>{
        val list: ArrayList<Choice> = ArrayList()
        val cursor : Cursor = this.writableDatabase.query("CHOICES_TABLE", arrayOf("\"id\"", "\"date\"", "\"font\"", "\"text\""), null, null, null, null, null)
        if(cursor.moveToFirst()){
            do{
                val choice = Choice()
                choice.id = cursor.getInt(0)
                choice.date = cursor.getString(1)
                choice.font = cursor.getString(2)
                choice.text = cursor.getString(3)
                list.add(choice)
            }while(cursor.moveToNext())
        }
        return list
    }
}