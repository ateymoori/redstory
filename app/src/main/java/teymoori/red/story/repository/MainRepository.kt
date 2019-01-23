package teymoori.red.story.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import teymoori.red.story.utils.RxDisposableManager
import teymoori.red.story.utils.base.RestClient
import teymoori.red.story.utils.entities.MessageModel
import teymoori.red.story.utils.entities.StoryModel

class MainRepository {
    companion object {
        val instance = MainRepository()
    }

    fun getStories(): LiveData<List<StoryModel>> {
        val data: MutableLiveData<List<StoryModel>> = MutableLiveData();
        val disposable = RestClient.service(true).getStories().observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io()).subscribe({ result ->
                if (result.isSuccessful) {
                    if (result.body() != null) {
                        result.body()?.let {
                            data.value = it
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

    }



    fun getMessages(story_id:String): LiveData<MutableList<MessageModel>> {
        val data: MutableLiveData<MutableList<MessageModel>> = MutableLiveData();
        val disposable = RestClient.service(true).getMessages(story_id).observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io()).subscribe({ result ->
                if (result.isSuccessful) {
                    if (result.body() != null) {
                        result.body()?.let {
                            data.value = it
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