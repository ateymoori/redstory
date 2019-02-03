package teymoori.red.story.mainboard

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import kotlinx.android.synthetic.main.stories_board_item.view.*
import teymoori.red.story.R
import teymoori.red.story.utils.customViews.MyTextView
import teymoori.red.story.utils.entities.StoryEntity
import teymoori.red.story.utils.entities.StoryModel
import teymoori.red.story.utils.listen
import teymoori.red.story.utils.loadFromURL

class MainBoardListAdapter (val items: List<StoryEntity>) : androidx.recyclerview.widget.RecyclerView.Adapter<MainBoardListAdapter.Holder>() {
    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): Holder {
        return Holder(LayoutInflater.from(parent.context).inflate(R.layout.stories_board_item, parent, false))
            .listen { pos, _ ->
                storyClickHandler.onStorySelect(items[pos])
            }
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.title.text = items[position].title
        holder.by.text = "Amir"
        holder.cover.loadFromURL(items[position].poster_address)

    }

    override fun getItemCount(): Int {
        return items.size
    }


    class Holder(private val view: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(view) {
        val title: MyTextView = view.title
        val by: MyTextView = view.by
        val cover: ImageView = view.cover
    }

    lateinit var storyClickHandler: StoryClickItem

    interface StoryClickItem {
        fun onStorySelect(story: StoryEntity)
    }
}