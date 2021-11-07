package org.techtown.alicorn

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import org.techtown.alicorn.databinding.ActivityPhotoViewBinding

class PhotoViewActivity : AppCompatActivity() {

    companion object {
        const val IMAGEURL: String = "IMAGEURL"
    }

    private val imageUrl : String by lazy{ intent.getStringExtra(IMAGEURL)?:""}


    private  lateinit var binding : ActivityPhotoViewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPhotoViewBinding.inflate(layoutInflater)
        supportActionBar?.hide()
        setContentView(binding.root)

        Glide.with(this).load(imageUrl).into(binding.photoView)

    }
}