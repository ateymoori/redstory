package teymoori.red.story.chatbaord

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_chat_board.*
import teymoori.red.story.R
import teymoori.red.story.utils.base.BaseActivity
import teymoori.red.story.utils.entities.StoryModel
import teymoori.red.story.utils.loadFromURL
import teymoori.red.story.utils.toast

class ChatBoardActivity : BaseActivity() {
    lateinit var story:StoryModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_board)
        story = intent.getParcelableExtra("story")
        bg.loadFromURL(story.background)
        val viewModel = ViewModelProviders.of(this).get(ChatBoardViewModel::class.java)
        observeMessages(viewModel, story.id)

    }

    private fun observeMessages(viewModel: ChatBoardViewModel, story_id: String) =
        viewModel.getMessages(story_id).observe(this, Observer {
            if (it != null) {
                val adapter = MessageBoardListAdapter(it)
                results.adapter = adapter
            }

        })
}
