package teymoori.red.story.mainboard

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main_board.*
import teymoori.red.story.R
import teymoori.red.story.chatbaord.ChatBoardActivity
import teymoori.red.story.utils.base.BaseActivity
import teymoori.red.story.utils.base.RestHandler
import teymoori.red.story.utils.entities.StoryModel
import teymoori.red.story.utils.toastError

class MainBoardActivity : BaseActivity(), MainBoardListAdapter.StoryClickItem {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_board)

        val viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        observeStories(viewModel)
    }

    private fun observeStories(viewModel: MainViewModel) {
        viewModel.getStories().observe(this, Observer {
            when (it?.status) {
                RestHandler.Status.LOADING -> loading(true)
                RestHandler.Status.ERROR -> {
                    it.exception?.toastError();
                    loading(false)
                }
                RestHandler.Status.SUCCESS -> {
                    loading(false)
                    val adapter = it.data?.let { items -> MainBoardListAdapter(items) }
                    results.adapter = adapter
                    adapter?.storyClickHandler = this
                }
            }
        })

    }

    override fun onStorySelect(story: StoryModel) {
        val intentMessages = Intent(mActivity, ChatBoardActivity::class.java).putExtra("story", story)
        startActivity(intentMessages)
    }
}