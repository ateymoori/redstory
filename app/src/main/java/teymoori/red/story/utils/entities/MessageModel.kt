package teymoori.red.story.utils.entities

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class MessageModel(
    val has_delay: String?,
    val id: String?,
    val left_right: String?,
    val message: String?,
    val message_type: String?,
    val sender_avatar: String?,
    val sender_name: String?,
    val story: String?
) : Parcelable