package com.abdallahehab.bankapp.domain

import android.os.Parcelable
import androidx.versionedparcelable.VersionedParcelize
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserData(
    val userName:String,
    var email:String,
    val balance:Int
):Parcelable
