package teymoori.red.story.utils.base

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.support.v7.app.AlertDialog
import android.view.KeyEvent
import android.view.View
import teymoori.red.story.R
import teymoori.red.story.utils.customViews.MyButton
import teymoori.red.story.utils.customViews.MyTextView

class BaseDialog @SuppressLint("InflateParams") constructor(mContext: Context, msg: String, leftBTN: String?, rightBTN: String) {


    private var dialogView: View
    var dialog: AlertDialog
    private var dialogBuilder: AlertDialog.Builder


    init {
        val mActivity = mContext as Activity
        dialogBuilder = AlertDialog.Builder(mActivity)
        val inflater = mActivity.layoutInflater
        dialogView = inflater.inflate(R.layout.base_dialog_view, null)
        val msgV = dialogView.findViewById<MyTextView>(R.id.msg)
        val leftV = dialogView.findViewById<MyButton>(R.id.left)
        val rightV = dialogView.findViewById<MyButton>(R.id.right)
        if (leftBTN == null)
            leftV.visibility = View.GONE
        msgV.text = msg
        rightV.text = rightBTN
        leftV.text = leftBTN
        dialogBuilder.setView(dialogView)
        dialog = dialogBuilder.create()
        dialogBuilder.setCancelable(false)
        dialog.setCanceledOnTouchOutside(false)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        setRightClickEvent { dialog.dismiss() }
        setLeftClickEvent { dialog.dismiss() }
    }

    fun setCancelable(cancelable: Boolean): BaseDialog {
        dialogBuilder.setCancelable(cancelable)
        if (!cancelable)
            dialog.setOnKeyListener { _, keyCode, _ -> keyCode == KeyEvent.KEYCODE_BACK }
        return this
    }

    fun setRightClickEvent(event: (Any) -> Unit): BaseDialog {
        dialogView.findViewById<MyButton>(R.id.right).setOnClickListener(event)
        return this
    }

    fun setLeftClickEvent(event: (Any) -> Unit): BaseDialog {
        dialogView.findViewById<MyButton>(R.id.left).setOnClickListener(event)
        return this
    }

    fun show() {
        dialog.show()
    }

    fun dismiss() {
        dialog.dismiss()
    }

}