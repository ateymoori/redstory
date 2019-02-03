package teymoori.red.story.chatbaord

import androidx.lifecycle.ViewModel
import androidx.lifecycle.LiveData
import teymoori.red.story.repository.MainRepository
import teymoori.red.story.utils.base.RestHandler
import teymoori.red.story.utils.entities.MessageEntity

class ChatBoardViewModel : ViewModel() {

    private lateinit var messages: LiveData<RestHandler<MutableList<MessageEntity>>>
    fun getMessages(story_id: Int): LiveData<RestHandler<MutableList<MessageEntity>>> {
        messages = MainRepository.instance.getMessages(story_id)
        return messages
    }
}