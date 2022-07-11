package com.example.roboapplechiperror

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText

class SampleView : AppCompatEditText {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    private var listener: Listener? = null

    private val watcher = object: TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) { }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) { }

        override fun afterTextChanged(s: Editable) {
            postDelayed(
                {
                    listener?.changed()
                }, 200)
        }
    }

    init {
        addTextChangedListener(watcher)
    }

    fun setListener(listener: Listener) {
        this.listener = listener
    }

    interface Listener {
        fun changed()
    }
}
