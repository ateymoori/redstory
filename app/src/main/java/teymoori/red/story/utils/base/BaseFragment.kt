package teymoori.red.story.utils.base

import android.content.Context
import android.support.v4.app.Fragment

open class BaseFragment : Fragment() {

    fun getCTX(): Context {
        return activity ?: MyApplication.appInstance.applicationContext
    }


}