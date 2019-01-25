package teymoori.red.story.utils.base

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.res.Resources
import androidx.core.content.ContextCompat
import android.util.Log
import android.view.View
import android.widget.Toast
import com.orhanobut.hawk.Hawk
import android.view.View.MeasureSpec
import android.view.ViewAnimationUtils
import android.view.inputmethod.InputMethodManager
import android.widget.ListView
import com.google.gson.Gson
import es.dmoral.toasty.Toasty
import teymoori.red.story.BuildConfig
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.Exception
import java.util.regex.Matcher
import java.util.regex.Pattern
import java.io.IOException

class Gen {
    companion object {
        private const val BASE_URL = "http://devcast.ir/red/"
        const val TOKEN_KEY = "token"
        const val USERNAME = "username"
        const val SERVER_ADDRESS = "server_address"
        const val ONESIGNAL_USER = "onesignal_user"
        const val ONESIGNAL_REG_ID = "onesignal_reg_id"
        const val TRANSACTION_ID = "transactionId"
        const val IPG_ASSETS_HTML_FILE = "ipg.html"
        private val gson: Gson = Gson()


        fun <T> saveData(key: String, value: T) = Hawk.put(key, value)

        fun <T> getData(key: String, defaultValue: T?): T? =
            if (defaultValue != null) Hawk.get(key, defaultValue) else Hawk.get(key)

        fun getToken(): String? = getData(TOKEN_KEY, null)

        private fun removeAllData() = Hawk.deleteAll()

        fun getServerAddress(): String {
            val address = getData(SERVER_ADDRESS, BASE_URL).toString()
            return if (!address.endsWith("/")) "$address/" else address

        }

        fun toast(msg: String?) {
            msg.let {
                Toasty.success(MyApplication.appInstance, it.toString(), Toast.LENGTH_LONG, true).show();
            }
        }

        fun toastError(msg: String?) {
            msg.let {
                Toasty.error(MyApplication.appInstance, it.toString(), Toast.LENGTH_LONG, true).show();
            }
        }

        fun setListViewHeightBasedChildes(listView: ListView) {
            val mAdapter = listView.adapter
            var totalHeight = 0
            for (i in 0 until mAdapter.count) {
                val mView = mAdapter.getView(i, null, listView)
                mView.measure(
                    MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED),
                    MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED)
                )
                totalHeight += mView.measuredHeight
            }
            val params = listView.layoutParams
            params.height = totalHeight + listView.dividerHeight * (mAdapter.count - 1)
            listView.layoutParams = params
            listView.requestLayout()
        }

        fun logOffUser() {
            Gen.saveData(Gen.TOKEN_KEY, null)
            Gen.saveData(Gen.USERNAME, null)
            removeAllData()
        }

        fun emailValidate(email: String): Boolean {
            val pattern: Pattern
            val matcher: Matcher
            val emailPattern = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"
            pattern = Pattern.compile(emailPattern)
            matcher = pattern.matcher(email)
            return matcher.matches()
        }

        fun log(msg: String) = log("debug_", msg)

        private fun log(tag: String, msg: String) = if (BuildConfig.DEBUG) Log.d(tag, msg) else null

        fun hideKeyboard(activity: Activity) {
            val imm = activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            var view = activity.currentFocus
            if (view == null) {
                view = View(activity)
            }
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }

        fun userLoggedIn(): Boolean = Gen.getData(Gen.TOKEN_KEY, "") != ""

        fun hasNavBar(): Boolean {
            val resources = MyApplication.appInstance.resources
            val id = resources.getIdentifier("config_showNavigationBar", "bool", "android")
            return id > 0 && resources.getBoolean(id)
        }

        fun getStringRes(res: Int): String = MyApplication.appInstance.getString(res) ?: ""
        fun getColorRes(res: Int): Int = ContextCompat.getColor(MyApplication.appInstance, res)

        fun toggle(v: View) {
            if (v.visibility == View.VISIBLE)
                hide(v)
            else
                show(v)
        }

        fun hide(v: View) = AnimationUtils.collapse(v)
        fun show(v: View) = AnimationUtils.expand(v)


        fun shareToMessagingApps(mActivity: Activity, title: String, message: String) {
            val intent = Intent(Intent.ACTION_SEND)
            intent.putExtra(Intent.EXTRA_TEXT, message)
            intent.type = "text/plain"
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK;
            mActivity.startActivity(Intent.createChooser(intent, title))
        }

    }
}