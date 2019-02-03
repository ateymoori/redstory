package teymoori.red.story.utils.base

import android.annotation.SuppressLint
import android.app.Activity
import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import android.view.WindowManager
import android.view.View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
import android.view.View.SYSTEM_UI_FLAG_HIDE_NAVIGATION




@SuppressLint("Registered")
open class BaseActivity : AppCompatActivity() {

    private lateinit var progress: ProgressDialog
    lateinit var mContext: Context
    lateinit var mActivity: Activity
    var needsAuthenticate: Boolean = true
    var TAG ="${this.javaClass.simpleName}_"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = this
        mActivity = this
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
           // val window = window
            //window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)
        }
        progress = ProgressDialog(this)
        progress.setTitle("Loading")
        progress.setMessage("Please waiting for loading")

        checkAccess()
    }

    private fun checkAccess() {
        if (needsAuthenticate && !Gen.userLoggedIn()) {
//            startActivity(Intent(this, SignInActivity::class.java))
//            finish()
        }
    }

    fun FullScreencall() {
        if (Build.VERSION.SDK_INT > 11 && Build.VERSION.SDK_INT < 19) { // lower api
            val v = this.window.decorView
            v.systemUiVisibility = View.GONE
        } else if (Build.VERSION.SDK_INT >= 19) {
            //for new api versions.
            val decorView = window.decorView
            val uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
            decorView.systemUiVisibility = uiOptions
        }
    }

    fun destroy() {
        finish()
    }

    fun loading(show: Boolean) {
        if (show)
            progress.show()
        else
            progress.dismiss()
    }
}