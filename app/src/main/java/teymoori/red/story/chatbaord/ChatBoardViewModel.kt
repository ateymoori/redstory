package teymoori.red.story.chatbaord

import androidx.lifecycle.ViewModel
import androidx.lifecycle.LiveData
import teymoori.red.story.repository.MainRepository
import teymoori.red.story.utils.base.RestHandler
import teymoori.red.story.utils.entities.MessageModel
import teymoori.red.story.utils.entities.StoryModel

class ChatBoardViewModel : ViewModel() {

    lateinit var messages: LiveData<RestHandler<MutableList<MessageModel>>>
    fun getMessages(story_id: String): LiveData<RestHandler<MutableList<MessageModel>>> {
        messages = MainRepository.instance.getMessages(story_id)
        return messages
    }
}