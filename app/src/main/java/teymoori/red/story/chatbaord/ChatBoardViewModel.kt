package teymoori.red.story.chatbaord

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.LiveData
import teymoori.red.story.repository.MainRepository
import teymoori.red.story.utils.entities.MessageModel
import teymoori.red.story.utils.entities.StoryModel

class ChatBoardViewModel : ViewModel() {

    lateinit var messages: LiveData<List<MessageModel>>
    fun getMessages(story_id: String): LiveData<List<MessageModel>> {
        messages = MainRepository.instance.getMessages(story_id)
        return messages
    }
}