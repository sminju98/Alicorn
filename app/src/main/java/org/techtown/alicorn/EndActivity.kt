package org.techtown.alicorn

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.techtown.alicorn.databinding.ActivityEndBinding
import org.techtown.alicorn.databinding.ActivityReceiptBinding

class EndActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEndBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEndBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.homeButton.setOnClickListener{
            finish()
            startActivity(Intent(this, MainActivity::class.java))
        }

        binding.back.setOnClickListener{
            finish()
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}