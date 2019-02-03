package teymoori.red.story.utils.base


import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.*
import teymoori.red.story.utils.entities.MessagesResponseModel
import teymoori.red.story.utils.entities.StoryResponseModel

interface RestAPI {

    @GET("stories")
    fun getStories():
            Observable<Response<StoryResponseModel>>

    @GET("messages")
    fun getMessages(@Query("story") id: Int):
            Observable<Response<MessagesResponseModel>>


}