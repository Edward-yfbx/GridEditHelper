package com.yfbx.edit.helpter

import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.widget.EditText

/**
 * @Author Edward
 * @Date 2019/4/13 0013
 * @Description:密码格
 */
class GridEditHelper {


    private lateinit var listener: (String) -> Unit
    private lateinit var views: Array<EditText>
    private val buffer = StringBuilder()


    fun addViews(views: Array<EditText>): GridEditHelper {
        this.views = views

        views.forEach {
            onKeyListener(it)
            afterTextChanged(it)
            onFocusChange(it)
        }

        return this
    }


    fun onInputResult(listener: (String) -> Unit) {
        this.listener = listener
    }


    /**
     * OnKeyListener
     */
    private fun onKeyListener(view: EditText) {
        view.setOnKeyListener { v, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_DEL && event.action == KeyEvent.ACTION_UP) {
                focusOnPre(view)
            }
            return@setOnKeyListener false
        }
    }


    /**
     * OnFocusChange
     */
    private fun onFocusChange(view: EditText) {
        view.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus) {
                view.text.clear()
            }
        }
    }


    /**
     * AfterTextChanged
     */
    private fun afterTextChanged(view: EditText) {
        view.addTextChangedListener(object : TextWatcher {

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(it: Editable?) {
                if (it != null && it.isNotEmpty()) {
                    focusOnNext(view)
                    buffer.append(it)
                    if (buffer.length == views.size) {
                        listener.invoke(buffer.toString())
                    }
                }
            }
        })
    }


    /**
     * 下一个获得焦点
     */
    private fun focusOnNext(view: EditText) {
        val index = views.indexOf(view)
        if (index in 0 until views.size - 1) {
            views[index + 1].requestFocus()
        }
    }

    /**
     * 上一个获得焦点
     */
    private fun focusOnPre(view: EditText) {
        val index = views.indexOf(view)
        //删除最后一格，焦点在最后一格
        if (index == views.size - 1 && buffer.length == views.size) {
            buffer.deleteCharAt(index)
            return
        }

        //焦点在最后一格，删除，焦点前移一格
        if (index == views.size - 1 && buffer.length == views.size - 1) {
            buffer.deleteCharAt(index - 1)
            views[index - 1].requestFocus()
            return
        }

        if (index in 1 until views.size - 1) {
            buffer.deleteCharAt(index - 1)
            views[index - 1].requestFocus()
        }
    }

}