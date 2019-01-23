package teymoori.red.story.chatbaord

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.LiveData
import teymoori.red.story.repository.MainRepository
import teymoori.red.story.utils.entities.MessageModel
import teymoori.red.story.utils.entities.StoryModel

class ChatBoardViewModel : ViewModel() {

    lateinit var messages: LiveData<MutableList<MessageModel>>
    fun getMessages(story_id: String): LiveData<MutableList<MessageModel>> {
        messages = MainRepository.instance.getMessages(story_id)
        return messages
    }
}