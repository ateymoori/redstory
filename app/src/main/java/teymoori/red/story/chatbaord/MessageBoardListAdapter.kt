package teymoori.red.story.chatbaord

import androidx.core.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.message_item.view.*
import teymoori.red.story.R
import teymoori.red.story.utils.customViews.MyTextView
import teymoori.red.story.utils.entities.MessageModel
import android.view.Gravity
import android.widget.ImageView
import teymoori.red.story.utils.base.MyApplication
import teymoori.red.story.utils.loadFromURL


class MessageBoardListAdapter(var items: MutableList<MessageModel>) :
    androidx.recyclerview.widget.RecyclerView.Adapter<MessageBoardListAdapter.Holder>() {
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
        val message = items[position].message
        holder.message.text = message?.trim()
        holder.sender.text = items[position].sender_name?.trim()
        if (items[position].left_right == "left") {
            holder.message.background = ContextCompat.getDrawable(MyApplication.appInstance, R.drawable.rounded_green)
            holder.container.gravity = Gravity.START
        } else {
            holder.message.background = ContextCompat.getDrawable(MyApplication.appInstance, R.drawable.rounded_red)
            holder.container.gravity = Gravity.END
        }

        when (items[position].message_type?.toInt()) {
            2 -> {
                holder.message.visibility = View.GONE
                holder.image.visibility = View.VISIBLE
                holder.image.loadFromURL(items[position].message)
            }
            else -> {
                holder.message.visibility = View.VISIBLE
                holder.image.visibility = View.GONE
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }


    class Holder(private val view: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(view) {
        val message: MyTextView = view.message
        val sender: MyTextView = view.sender
        val image: ImageView = view.image
        val container: LinearLayout = view.container
    }

    lateinit var messageClickHandler: MessageClickItem

    interface MessageClickItem {
        fun onMessageSelect(story: MessageModel)
    }
}