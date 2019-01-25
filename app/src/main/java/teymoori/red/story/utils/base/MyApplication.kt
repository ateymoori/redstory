package teymoori.red.story.utils.base

import android.app.Application
import android.graphics.Color
import androidx.core.content.ContextCompat
import com.orhanobut.hawk.Hawk

import es.dmoral.toasty.Toasty
import teymoori.red.story.R
import teymoori.red.story.utils.log


class MyApplication : Application() {

    companion object {
        lateinit var appInstance: Application
    }

    override fun onCreate() {
        super.onCreate()
        appInstance = this
        Hawk.init(this).build()
       // initOneSignal()
        initToasty()
        //microsoftAppCenter()
    }
//
//    private fun initOneSignal() {
//        OneSignal.startInit(this)
//                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
//                .unsubscribeWhenNotificationsAreDisabled(true)
//                .setNotificationReceivedHandler(OneSignalNotificationReceiveHandler(this))
//                .setNotificationOpenedHandler(OneSignalNotificationOpenHandler(this))
//                .init()
//        OneSignal.idsAvailable { userId, registrationId ->
//            Log.d("onesignal_", "User:$userId")
//            Gen.saveData(Gen.ONESIGNAL_USER, userId)
//            if (registrationId != null) {
//                Log.d("onesignal_", "registrationId:$registrationId")
//                Gen.saveData(Gen.ONESIGNAL_REG_ID, registrationId)
//            }
//        }
//    }

    private fun initToasty() {
        Toasty.Config.getInstance()
                .setErrorColor(ContextCompat.getColor(this, R.color.redError)) // optional
                .setInfoColor(ContextCompat.getColor(this, R.color.rightGreenStatusBar)) // optional
                .setSuccessColor(ContextCompat.getColor(this, R.color.rightGreenStatusBar)) // optional
                .setWarningColor(ContextCompat.getColor(this, R.color.redError)) // optional
                .setTextColor(Color.WHITE) // optional
                .tintIcon(true) // optional (apply textColor also to the icon)
                .apply();
    }


}