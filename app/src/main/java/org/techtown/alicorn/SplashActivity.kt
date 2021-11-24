package org.techtown.alicorn

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import org.techtown.alicorn.databinding.ActivitySplashBinding
import android.widget.Toast

import com.gun0912.tedpermission.PermissionListener




class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySplashBinding.inflate(layoutInflater)
        supportActionBar?.hide()
        setContentView(binding.root)

        //startTimer()
    }

    private fun startTimer() {
        object : CountDownTimer(2000L,1000L){
            override fun onTick(millisUntilFinished: Long) {}

            override fun onFinish() {
                startActivity(Intent(this@SplashActivity,LoginActivity::class.java))
                finish()
            }
        }.start()

    }
}