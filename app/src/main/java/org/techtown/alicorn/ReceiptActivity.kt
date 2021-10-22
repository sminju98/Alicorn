package org.techtown.alicorn

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.techtown.alicorn.databinding.ActivityReceiptBinding
import android.R
import android.view.View


class ReceiptActivity : AppCompatActivity() {
    private lateinit var binding: ActivityReceiptBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityReceiptBinding.inflate(layoutInflater)
        setContentView(binding.root)


        setCheckboxListener()
    }


    private fun setCheckboxListener() {
        binding.checkBox.setOnClickListener {
            if (binding.checkBox.isChecked) {
                binding.checkBox1.isChecked = true
                binding.checkBox2.isChecked = true
                binding.checkBox3.isChecked = true
                binding.checkBox4.isChecked = true
            } else {
                binding.checkBox1.isChecked = false
                binding.checkBox2.isChecked = false
                binding.checkBox3.isChecked = false
                binding.checkBox4.isChecked = false

            }
        }

        binding.checkBox1.setOnCheckedChangeListener { buttonView, isChecked ->
            if (!isChecked) {
                binding.checkBox.isChecked = false
            }
            checkChecked()

        }
        binding.checkBox2.setOnCheckedChangeListener { buttonView, isChecked ->
            if (!isChecked) {
                binding.checkBox.isChecked = false
            }
            checkChecked()

        }
        binding.checkBox3.setOnCheckedChangeListener { buttonView, isChecked ->
            if (!isChecked) {
                binding.checkBox.isChecked = false
            }
            checkChecked()
        }
        binding.checkBox4.setOnCheckedChangeListener { buttonView, isChecked ->
            if (!isChecked) {
                binding.checkBox.isChecked = false
            }
            checkChecked()

        }

    }

    private fun checkChecked() {
        binding.checkBox.isChecked = binding.checkBox1.isChecked &&
                binding.checkBox2.isChecked &&
                binding.checkBox3.isChecked &&
                binding.checkBox4.isChecked

        val color = if (binding.checkBox1.isChecked &&
            binding.checkBox2.isChecked && binding.checkBox3.isChecked && binding.checkBox4.isChecked
        ) {
            binding.nextBtn.isEnabled = true
            "#6782b7"
        } else {
            "#aaaaaa"
        }

        binding.nextBtn.backgroundTintList = ColorStateList.valueOf(Color.parseColor(color))
    }

    fun onClick(view: View) {
        when (view) {

            binding.arrow1 -> {
                val intent = Intent(this, WebViewActivity::class.java)
                intent.putExtra("TYPE", "AGREE")
                intent.putExtra("NUMBER", 1)
                startActivity(intent)
            }
            binding.arrow2 -> {
                val intent = Intent(this, WebViewActivity::class.java)
                intent.putExtra("TYPE", "AGREE")
                intent.putExtra("NUMBER", 2)
                startActivity(intent)
            }

            binding.arrow3 -> {
                val intent = Intent(this, WebViewActivity::class.java)
                intent.putExtra("TYPE", "AGREE")
                intent.putExtra("NUMBER", 3)
                startActivity(intent)
            }
            binding.arrow4 -> {
                val intent = Intent(this, WebViewActivity::class.java)
                intent.putExtra("TYPE", "AGREE")
                intent.putExtra("NUMBER", 4)
                startActivity(intent)
            }

            binding.nextBtn -> {
                val email = Intent(Intent.ACTION_SEND)
                email.type = "plain/Text"
                email.putExtra(Intent.EXTRA_EMAIL, "sminju98@gmail.com")
                email.putExtra(Intent.EXTRA_SUBJECT, "진료신청")

                
                email.putExtra(Intent.EXTRA_TEXT, "진료신청합니다")
                email.setType("message/rfc822");
                startActivity(email);
            }
        }
    }

}