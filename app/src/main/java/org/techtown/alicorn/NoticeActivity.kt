package org.techtown.alicorn

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.techtown.alicorn.databinding.ActivityAgreeBinding
import org.techtown.alicorn.databinding.ActivityNoticeBinding

class NoticeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNoticeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNoticeBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
