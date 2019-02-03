package teymoori.red.story.storyintroduction

import android.content.Intent
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_introduce.*
import teymoori.red.story.R
import teymoori.red.story.chatbaord.ChatBoardActivity
import teymoori.red.story.utils.base.BaseActivity
import teymoori.red.story.utils.entities.StoryEntity
import teymoori.red.story.utils.loadFromURL

class IntroduceActivity : BaseActivity() {
    lateinit var story: StoryEntity
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_introduce)

        story = intent.getParcelableExtra("story")
        bg.loadFromURL(story.poster_address)
        firstTitle.text = story.title
        secondTitle.text = story.description
        readsCount.text = story.read_count.toString()


        bg.setOnClickListener{
            val intentMessages = Intent(mActivity, ChatBoardActivity::class.java).putExtra("story", story)
            startActivity(intentMessages)
            finish()
        }

    }
}
