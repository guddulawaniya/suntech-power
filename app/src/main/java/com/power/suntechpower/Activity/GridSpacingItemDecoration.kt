package com.power.suntechpower.Activity

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class GridSpacingItemDecoration(
    private val spanCount: Int, // Number of columns in the grid
    private val spacing: Int, // Space between items
    private val includeEdge: Boolean // Whether to include spacing at the edges
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        val position = parent.getChildAdapterPosition(view) // Item position
        val column = position % spanCount // Column index

        if (includeEdge) {
            // Add spacing for left and right edges
            outRect.left = spacing - column * spacing / spanCount
            outRect.right = (column + 1) * spacing / spanCount

            // Add top spacing for the first row
            if (position < spanCount) {
                outRect.top = spacing
            }

            // Add bottom spacing for all rows
            outRect.bottom = spacing
        } else {
            // No edge spacing, just internal spacing
            outRect.left = column * spacing / spanCount
            outRect.right = spacing - (column + 1) * spacing / spanCount

            // Add top spacing for rows after the first
            if (position >= spanCount) {
                outRect.top = spacing
            }
        }
    }
}
