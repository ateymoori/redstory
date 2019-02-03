package teymoori.red.story.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import teymoori.red.story.utils.RxDisposableManager
import teymoori.red.story.utils.base.RestClient
import teymoori.red.story.utils.base.RestHandler
import teymoori.red.story.utils.entities.MessageEntity
import teymoori.red.story.utils.entities.StoryEntity

class MainRepository {
    companion object {
        val instance = MainRepository()
    }

    fun getStories(): LiveData<RestHandler<List<StoryEntity>>> {
        val data: MutableLiveData<RestHandler<List<StoryEntity>>> = MutableLiveData();
        val disposable = RestClient.service(true).getStories().observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io()).subscribe({ result ->
                if (result.isSuccessful) {
                    if (result.body() != null) {
                        result.body()?.let {
                            data.value = RestHandler.success(it.data)
                        }
                    } else
                        failed("")
                } else
                    failed("${result.code()} ${result.message()}")
            }, { error ->
                failed("Failed: ${error.message}")
            })
        RxDisposableManager.getCompositeDisposable().add(disposable)
        return data
    }


    private fun failed(error: String?) {
        RestHandler.error<String>(error)
    }


    fun getMessages(story_id: Int): LiveData<RestHandler<MutableList<MessageEntity>>> {
        val data: MutableLiveData<RestHandler<MutableList<MessageEntity>>> = MutableLiveData();
        val disposable = RestClient.service(true).getMessages(story_id).observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io()).subscribe({ result ->
                if (result.isSuccessful) {
                    if (result.body() != null) {
                        result.body()?.let {
                            data.value = RestHandler.success(it.data)
                        }
                    } else
                        failed("")
                } else
                    failed("${result.code()} ${result.message()}")
            }, { error ->
                failed("Failed: ${error.message}")
            })
        RxDisposableManager.getCompositeDisposable().add(disposable)
        return data
    }


}