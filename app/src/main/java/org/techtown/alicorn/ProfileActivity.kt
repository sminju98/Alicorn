package org.techtown.alicorn

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import gun0912.tedimagepicker.builder.TedImagePicker
import jp.wasabeef.glide.transformations.CropCircleWithBorderTransformation
import org.techtown.alicorn.databinding.ActivityProfileBinding

class ProfileActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityProfileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        supportActionBar?.hide()
        setContentView(binding.root)

        val option = CropCircleWithBorderTransformation()
        Glide.with(this).load(R.drawable.doctor).circleCrop().into(binding.profileImage)
        binding.profileImage.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v) {
            binding.profileImage -> {
                TedImagePicker.with(this)
                    .start { uri ->
                        showSingleImage(uri)

                    }
            }
        }
    }

    private fun showSingleImage(uri: Uri) {

        Glide.with(this).load(uri).circleCrop().into(binding.profileImage)
    }
}
