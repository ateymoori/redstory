package teymoori.red.story.utils.entities

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class StoryModel(
    val description: String?,
    val genere: String?,
    val id: String,
    val lang: String?,
    val poster_address: String?,
    val stars: String?,
    val background: String?,
    val title: String?
): Parcelable