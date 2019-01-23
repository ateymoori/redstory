package teymoori.red.story.mainboard

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.LiveData
import teymoori.red.story.repository.MainRepository
import teymoori.red.story.utils.base.RestHandler
import teymoori.red.story.utils.entities.MessageModel
import teymoori.red.story.utils.entities.StoryModel

class MainViewModel : ViewModel() {

    private var stories: LiveData<RestHandler<List<StoryModel>>> = MainRepository.instance.getStories()

    fun getStories(): LiveData<RestHandler<List<StoryModel>>> {
        return stories
    }

}