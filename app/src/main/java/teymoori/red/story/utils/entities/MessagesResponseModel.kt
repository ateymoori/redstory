package teymoori.red.story.utils.entities

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class MessagesResponseModel(
    val api_authorization: String,
    val api_message: String,
    val api_status: Int,
    val `data`: MutableList<MessageEntity>?
): Parcelable

@Parcelize
data class MessageEntity(
    val has_delay: Int?,
    val id: Int?,
    val left_right: String?,
    val message: String?,
    val message_type: Int?,
    val sender_avatar: String?,
    val sender_name: String?,
    val story: Int?
): Parcelable