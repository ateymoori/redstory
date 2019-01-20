package teymoori.red.story.chatbaord

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import teymoori.red.story.R
import teymoori.red.story.utils.base.BaseActivity
import teymoori.red.story.utils.toast

class ChatBoardActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_board)

        val viewModel = ViewModelProviders.of(this).get(ChatBoardViewModel::class.java)
        observeMessages(viewModel, "1")

    }

    private fun observeMessages(viewModel: ChatBoardViewModel, story_id: String) =
        viewModel.getMessages(story_id).observe(this, Observer {
            if (it != null) {
                 it.toString().toast()
            }
        })
}
