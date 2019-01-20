package teymoori.red.story.utils

import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import teymoori.red.story.utils.base.Gen
import teymoori.red.story.utils.base.MyApplication

fun String.toast() {
    Gen.toast(this)
}
fun String.toastError() {
    Gen.toastError(this)
}

fun String.log() {
    Gen.log(this)
}

fun String.isEmail(): Boolean {
    return Gen.emailValidate(this)
}

fun ImageView.loadFromURL(url: String?) {
    Glide.with(MyApplication.appInstance)
            .load(url)
            .into(this);
}

fun ImageView.config(img: Int, event: (Any) -> Unit) {
    this.setImageDrawable(ContextCompat.getDrawable(MyApplication.appInstance, img))
    this.setOnClickListener(event)
    this.visibility = View.VISIBLE
}


fun <T : RecyclerView.ViewHolder> T.listen(event: (position: Int, type: Int) -> Unit): T {
    itemView.setOnClickListener {
        event.invoke(adapterPosition, itemViewType)
    }
    return this
}