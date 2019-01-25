package teymoori.red.story.customViews

import android.content.Context
import androidx.appcompat.widget.AppCompatEditText
import android.util.AttributeSet
import java.util.jar.Attributes

class MyEditText : AppCompatEditText {

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    public fun value(): String {
        return (return if (text != null) text.toString() else "")
    }
}