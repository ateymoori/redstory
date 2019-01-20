package teymoori.red.story.mainboard

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.LiveData
import teymoori.red.story.repository.MainRepository
import teymoori.red.story.utils.entities.MessageModel
import teymoori.red.story.utils.entities.StoryModel

class MainViewModel : ViewModel() {

    private var stories: LiveData<List<StoryModel>> = MainRepository.instance.getStories()

    fun getStories(): LiveData<List<StoryModel>> {
        return stories
    }

}