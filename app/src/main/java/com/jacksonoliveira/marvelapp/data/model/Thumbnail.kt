package com.jacksonoliveira.marvelapp.data.model

import android.os.Parcel
import android.os.Parcelable

data class Thumbnail(
    private val path: String? = null,
    private val extension: String? = null
) : Parcelable {
    val imagePath: String
        get() {
            val fullUrl = "$path.$extension"
            return if (fullUrl.startsWith("http://")) {
                "https://" + fullUrl.substring(7)
            } else {
                fullUrl
            }
        }

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(path)
        parcel.writeString(extension)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Thumbnail> {
        override fun createFromParcel(parcel: Parcel): Thumbnail {
            return Thumbnail(parcel)
        }

        override fun newArray(size: Int): Array<Thumbnail?> {
            return arrayOfNulls(size)
        }
    }
}