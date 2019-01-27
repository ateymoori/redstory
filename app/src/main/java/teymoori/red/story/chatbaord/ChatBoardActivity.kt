package teymoori.red.story.chatbaord

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_chat_board.*
import teymoori.red.story.R
import teymoori.red.story.utils.base.BaseActivity
import teymoori.red.story.utils.entities.MessageModel
import teymoori.red.story.utils.entities.StoryModel
import teymoori.red.story.utils.loadFromURL
import android.view.MotionEvent
import androidx.recyclerview.widget.RecyclerView
import teymoori.red.story.utils.base.RestHandler
import teymoori.red.story.utils.toastError

class ChatBoardActivity : BaseActivity(), MessageBoardListAdapter.MessageClickItem {
    lateinit var story: StoryModel
    private lateinit var adapter: MessageBoardListAdapter
    private var messages: MutableList<MessageModel> = mutableListOf()
    private var lastItemReleased = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_board)
        story = intent.getParcelableExtra("story")
        bg.loadFromURL(story.background)
        val viewModel = ViewModelProviders.of(this).get(ChatBoardViewModel::class.java)
        observeMessages(viewModel, story.id)
        adapter = MessageBoardListAdapter(messages)
        storyTitle.text = story.title
        results.addOnScrollListener(CustomScrollHandler())
        results.adapter = adapter
        results.itemAnimator = androidx.recyclerview.widget.DefaultItemAnimator()
        results.addOnItemTouchListener(object : androidx.recyclerview.widget.RecyclerView.SimpleOnItemTouchListener() {
            override fun onInterceptTouchEvent(rv: androidx.recyclerview.widget.RecyclerView, e: MotionEvent): Boolean {
                if (e.action == MotionEvent.ACTION_UP)
                    if (!scrolling)
                        addItem()
                return false
            }

        })
        next.setOnClickListener {
            addItem()
        }
        home.setOnClickListener {
            finish()
        }
    }

    private fun addItem() {
        if (lastItemReleased < messages.size) {
            adapter.addItem(messages[lastItemReleased])
            lastItemReleased++
            results.scrollToPosition(adapter.itemCount - 1)
        }
    }

    override fun onResume() {
        super.onResume()
        FullScreencall()
    }

    private fun observeMessages(viewModel: ChatBoardViewModel, story_id: String) {
        viewModel.getMessages(story_id).observe(this, Observer {
            when (it?.status) {
                RestHandler.Status.LOADING -> loading(true)
                RestHandler.Status.ERROR -> {
                    it.exception?.toastError()
                    loading(false)
                }
                RestHandler.Status.SUCCESS -> {
                    loading(false)
                    messages = it.data!!
                    addItem()
                }
            }
        })
    }

    companion object {
        var scrolling = false
    }

    class CustomScrollHandler : RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            when (newState) {
                RecyclerView.SCROLL_STATE_IDLE -> {
                    scrolling = false
                }
                RecyclerView.SCROLL_STATE_DRAGGING -> {
                    scrolling = true
                }
                RecyclerView.SCROLL_STATE_SETTLING -> {
                }
            }
        }
    }

    override fun onMessageSelect(story: MessageModel) {
        // addItem()
    }
}
