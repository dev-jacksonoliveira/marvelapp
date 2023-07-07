package com.jacksonoliveira.marvelapp.presentation.components

import android.content.Context
import android.graphics.Bitmap
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.MultiTransformation
import com.bumptech.glide.load.Transformation
import com.bumptech.glide.load.resource.bitmap.BitmapTransitionOptions.withCrossFade
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.RequestOptions.bitmapTransform
import jp.wasabeef.glide.transformations.BlurTransformation

class BlurredImageView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AppCompatImageView(context, attrs, defStyleAttr) {

    fun loadImageWithBlur(url: String) {
        Glide.with(this)
            .asBitmap()
            .load(url)
            .transition(withCrossFade())
            .apply(blurTransformation())
            .into(this)
    }

    private fun blurTransformation(): RequestOptions {
        val transformations: MutableList<Transformation<Bitmap>> = ArrayList()
        transformations.add(BlurTransformation(RADIUS, SAMPLING))

        return bitmapTransform(MultiTransformation(transformations))
    }

    companion object {
        private const val RADIUS = 25
        private const val SAMPLING = 1
    }
}