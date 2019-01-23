package teymoori.red.story.utils.base


import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.*
import teymoori.red.story.utils.entities.MessageModel
import teymoori.red.story.utils.entities.StoryModel

interface RestAPI {

    @GET("stories.php")
    fun getStories():
            Observable<Response<List<StoryModel>>>

    @GET("messages.php")
    fun getMessages(@Query("story_id") story_id: String):
            Observable<Response<MutableList<MessageModel>>>


}