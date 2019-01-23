package teymoori.red.story.chatbaord

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.ViewGroup
import kotlinx.android.synthetic.main.activity_chat_board.*
import teymoori.red.story.R
import teymoori.red.story.utils.base.BaseActivity
import teymoori.red.story.utils.entities.MessageModel
import teymoori.red.story.utils.entities.StoryModel
import teymoori.red.story.utils.loadFromURL
import teymoori.red.story.utils.toast
import android.view.MotionEvent
import android.support.v7.widget.RecyclerView
import teymoori.red.story.utils.log
import android.support.v7.widget.DefaultItemAnimator


class ChatBoardActivity : BaseActivity(), MessageBoardListAdapter.MessageClickItem {
    override fun onMessageSelect(story: MessageModel) {
        // addItem()
    }

    lateinit var story: StoryModel
    lateinit var adapter: MessageBoardListAdapter
    var messages: MutableList<MessageModel> = mutableListOf<MessageModel>()
    var lastItemReleased = 0;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_board)
        story = intent.getParcelableExtra("story")
        bg.loadFromURL(story.background)
        val viewModel = ViewModelProviders.of(this).get(ChatBoardViewModel::class.java)
        observeMessages(viewModel, story.id)

        "page".toast()
        adapter = MessageBoardListAdapter(messages)
        results.adapter = adapter
        results.itemAnimator = DefaultItemAnimator()
        results.addOnItemTouchListener(object : RecyclerView.SimpleOnItemTouchListener() {
            override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
                if (e.action == MotionEvent.ACTION_DOWN)
                    addItem()
                return true
            }
        })
    }

    private fun addItem() {
        if (lastItemReleased < messages.size) {
            adapter.addItem(messages[lastItemReleased])
            lastItemReleased++
        }
    }

    private fun observeMessages(viewModel: ChatBoardViewModel, story_id: String) =
        viewModel.getMessages(story_id).observe(this, Observer {
            if (it != null) {
                messages = it
                addItem()
            }
        })
}
