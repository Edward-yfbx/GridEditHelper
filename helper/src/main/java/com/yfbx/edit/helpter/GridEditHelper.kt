package com.yfbx.edit.helpter

import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.widget.EditText

/**
 * @Author Edward
 * @Date 2019/4/13 0013
 * @Description:密码格
 *
 */
class GridEditHelper(private val views: Array<EditText>, private val listener: (String) -> Unit) {


    private val NULL = 'N'
    private val array = CharArray(views.size)


    init {
        for (i in 0 until views.size) {
            array[i] = NULL
            onKeyListener(i)
            afterTextChanged(i)
            onFocusChange(i)
        }

    }


    /**
     * OnKeyListener
     */
    private fun onKeyListener(position: Int) {
        views[position].setOnKeyListener { v, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_DEL && event.action == KeyEvent.ACTION_UP) {
                focusOnPre(position)
            }
            return@setOnKeyListener false
        }
    }


    /**
     * OnFocusChange
     */
    private fun onFocusChange(position: Int) {
        val view = views[position]
        view.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus) {
                view.text.clear()
                array[position] = NULL
            }
        }
    }


    /**
     * AfterTextChanged
     */
    private fun afterTextChanged(position: Int) {
        views[position].addTextChangedListener(object : TextWatcher {

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(it: Editable?) {
                if (it != null && it.isNotEmpty()) {
                    focusOnNext(position)
                    array[position] = it[0]
                    val result = String(array)
                    if (result.length == views.size && !result.contains(NULL)) {
                        listener.invoke(result)
                    }
                }
            }
        })
    }


    /**
     * 下一个获得焦点
     */
    private fun focusOnNext(position: Int) {
        if (position in 0 until views.size - 1) {
            views[position + 1].requestFocus()
        }
    }

    /**
     * 上一个获得焦点
     */
    private fun focusOnPre(position: Int) {
        if (array[position] != NULL) {
            array[position] = NULL
            return
        }

        if (array[position] == NULL && position > 0) {
            views[position - 1].requestFocus()
        }
    }

}