package com.example.customdialog

import android.app.Activity
import android.app.Dialog
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.Gravity
import android.view.Window
import android.view.WindowManager.LayoutParams
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class CustomDialog(private val mActivity: Activity, sMsg: String, sBtnYes: String, sBtnNo: String) : Dialog(mActivity) {
    private var ivClose: ImageView? = null
    private var btnYes: Button? = null
    private var btnNo: Button? = null
    private var mtvMessage: TextView? = null
    private var mivIcon: ImageView? = null
    internal var mListerner: CustomDialogCompleteListener? = null
    private var msMsg = ""
    private var msBtnYes = ""
    private var msBtnNo = ""
    private var mLogodrawable: Drawable? = null
    private val mnLayoutResId = R.layout.popup_generic


    init {
        msMsg = sMsg
        msBtnYes = sBtnYes
        msBtnNo = sBtnNo
    }

    fun setListener(listener: CustomDialogCompleteListener) {
        mListerner = listener
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(mnLayoutResId)
        setCanceledOnTouchOutside(false)
        window!!.setBackgroundDrawableResource(android.R.color.transparent)
        window!!.setLayout(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)


        ivClose = findViewById(R.id.btn_close) as ImageView
        mivIcon = findViewById(R.id.btn_icon) as ImageView
        mtvMessage = findViewById(R.id.tv_message) as TextView
        btnYes = findViewById(R.id.btn_yes) as Button
        btnNo = findViewById(R.id.btn_no) as Button

        btnYes!!.text = msBtnYes
        btnNo!!.text = msBtnNo
        mtvMessage!!.text = msMsg


        if (mLogodrawable != null) mivIcon!!.setImageDrawable(mLogodrawable)

        ivClose!!.setOnClickListener {
            if (null != mListerner)
                mListerner!!.onComplete(BTN_CLOSE)
            dismiss()
        }

        btnYes!!.setOnClickListener {
            if (null != mListerner)
                mListerner!!.onComplete(BTN_YES)
            dismiss()
        }

        btnNo!!.setOnClickListener {
            if (null != mListerner)
                mListerner!!.onComplete(BTN_NO)
            dismiss()
        }
    }

    fun setImage(nId: Int) {
        if (null == mivIcon)
            mLogodrawable = mActivity.resources.getDrawable(nId)
        else
            mivIcon!!.setImageResource(R.drawable.ic_menu_gallery)
    }

    fun setImage(drawable: Drawable) {
        if (null == mivIcon)
            mLogodrawable = drawable
        else
            mivIcon!!.setImageDrawable(drawable)
    }

    fun setPosition(y: Int, x: Int) {

        val params = LayoutParams()
        params.y = y
        params.x = x
        params.gravity = Gravity.CENTER
        window!!.attributes = params

    }

    companion object {


        var BTN_CLOSE = 1
        var BTN_NO = 3
        var BTN_YES = 4
    }
}
