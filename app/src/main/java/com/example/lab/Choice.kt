package com.example.lab

import android.os.Parcel
import android.os.Parcelable

class Choice() :Parcelable {
    var id: Int = 0
    var date: String = ""
    var font: String = ""
    var text: String = ""

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(this.id)
        parcel.writeString(this.date)
        parcel.writeString(this.font)
        parcel.writeString(this.text)
    }

    override fun describeContents(): Int {
        return 0
    }

    protected constructor(`in`: Parcel) : this() {
        this.id = `in`.readInt()
        this.date = `in`.readString().toString()
        this.font = `in`.readString().toString()
        this.text = `in`.readString().toString()
    }

    companion object CREATOR : Parcelable.Creator<Choice> {
        override fun createFromParcel(parcel: Parcel): Choice {
            return Choice(parcel)
        }

        override fun newArray(size: Int): Array<Choice?> {
            return arrayOfNulls(size)
        }
    }

}