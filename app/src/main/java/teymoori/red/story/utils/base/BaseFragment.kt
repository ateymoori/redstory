package teymoori.red.story.utils.base

import android.content.Context
import androidx.fragment.app.Fragment

open class BaseFragment : androidx.fragment.app.Fragment() {

    fun getCTX(): Context {
        return activity ?: MyApplication.appInstance.applicationContext
    }


}