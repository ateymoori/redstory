package teymoori.red.story.utils.entities

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class StoryResponseModel(
    val api_authorization: String,
    val api_message: String,
    val api_status: Int,
    val `data`: List<StoryEntity>
): Parcelable

@Parcelize
data class StoryEntity(
    val background: String,
    val description: String,
    val genre: Int,
    val id: Int,
    val lang: Int,
    val poster_address: String,
    val read_count: Int,
    val stars: Int,
    val title: String
): Parcelable