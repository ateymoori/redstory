package teymoori.red.story.mainboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.LiveData
import teymoori.red.story.repository.MainRepository
import teymoori.red.story.utils.base.RestHandler
import teymoori.red.story.utils.entities.StoryEntity

class MainViewModel : ViewModel() {

    private var stories: LiveData<RestHandler<List<StoryEntity>>> = MainRepository.instance.getStories()

    fun getStories(): LiveData<RestHandler<List<StoryEntity>>> {
        return stories
    }

}