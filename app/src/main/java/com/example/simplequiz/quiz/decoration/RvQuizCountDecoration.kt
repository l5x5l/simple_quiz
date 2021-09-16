package com.example.simplequiz.quiz.decoration

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.simplequiz.util.dpToPx

class RvQuizCountDecoration(private val context: Context) : RecyclerView.ItemDecoration() {
    private val size6 = dpToPx(context, 6)

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)

        val position = parent.getChildLayoutPosition(view)

        if (position != 0)
            outRect.left = size6
    }
}