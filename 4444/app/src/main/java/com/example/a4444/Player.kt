package com.example.a4444




import android.os.Parcel
import android.os.Parcelable

data class Player(
    var name: String = "",

    var gender: String = "",
    var phone: String = "",
    var score: String="",
    var hobbies: String = "",
    var avatarPath: String? = null,

) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString()?:"",

    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(phone)
        parcel.writeString(gender)
        parcel.writeString(hobbies)
        parcel.writeString(avatarPath)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Player> {
        override fun createFromParcel(parcel: Parcel): Player {
            return Player(parcel)
        }

        override fun newArray(size: Int): Array<Player?> {
            return arrayOfNulls(size)
        }
    }
}