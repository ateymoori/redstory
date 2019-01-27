package teymoori.red.story.mainboard

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main_board.*
import teymoori.red.story.chatbaord.ChatBoardActivity
import teymoori.red.story.utils.base.BaseActivity
import teymoori.red.story.utils.base.RestHandler
import teymoori.red.story.utils.entities.StoryModel
import teymoori.red.story.utils.toastError
import androidx.recyclerview.widget.GridLayoutManager
import teymoori.red.story.R


class MainBoardActivity : BaseActivity(), MainBoardListAdapter.StoryClickItem {

    private lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_board)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
    }

    override fun onResume() {
        super.onResume()
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

                    val gridLayoutManager = GridLayoutManager(mContext, 2)
                    gridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                        override fun getSpanSize(position: Int): Int {
                            return (if (position % 5 == 0) 2 else 1)
                        }

                    }
                    results.layoutManager = gridLayoutManager


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