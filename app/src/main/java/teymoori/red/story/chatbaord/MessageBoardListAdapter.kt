package teymoori.red.story.chatbaord

import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.message_item.view.*
import teymoori.red.story.R
import teymoori.red.story.utils.customViews.MyTextView
import teymoori.red.story.utils.entities.MessageModel
import teymoori.red.story.utils.listen
import android.view.Gravity
import teymoori.red.story.utils.base.MyApplication


class MessageBoardListAdapter(var items: MutableList<MessageModel>) :
    RecyclerView.Adapter<MessageBoardListAdapter.Holder>() {
    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): Holder {
        return Holder(LayoutInflater.from(parent.context).inflate(R.layout.message_item, parent, false))
//            .listen { pos, _ ->
//               messageClickHandler.onMessageSelect(items[pos])
//            }
    }

    fun addItem(item: MessageModel) {
        items.add(item)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.message.text = items[position].message?.trim()
        holder.sender.text = items[position].sender_name?.trim()
        if (items[position].left_right == "left") {
            holder.message.background = ContextCompat.getDrawable(MyApplication.appInstance, R.drawable.rounded_green)
            holder.container.gravity = Gravity.START
        } else {
            holder.message.background = ContextCompat.getDrawable(MyApplication.appInstance, R.drawable.rounded_red)
            holder.container.gravity = Gravity.END
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }


    class Holder(private val view: View) : RecyclerView.ViewHolder(view) {
        val message: MyTextView = view.message
        val sender: MyTextView = view.sender
        val container: LinearLayout = view.container
    }

    lateinit var messageClickHandler: MessageClickItem

    interface MessageClickItem {
        fun onMessageSelect(story: MessageModel)
    }
}