package org.techtown.alicorn

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.techtown.alicorn.databinding.ActivityReceiptBinding

class ReceiptActivity : AppCompatActivity() {
    private lateinit var binding: ActivityReceiptBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityReceiptBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}