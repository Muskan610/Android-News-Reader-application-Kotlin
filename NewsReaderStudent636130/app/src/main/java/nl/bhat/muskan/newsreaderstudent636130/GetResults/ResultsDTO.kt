package nl.bhat.muskan.newsreaderstudent636130.GetResults

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ResultsDTO (
    var Id: String,
    var Title: String,
    var Summary: String,
    var PublishDate: String,
    var Image: String,
    var Url: String,
    var IsLiked: String
)  : Parcelable
