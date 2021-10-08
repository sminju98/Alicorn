package org.techtown.alicorn

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.firebase.auth.FirebaseAuth
import org.techtown.alicorn.databinding.ActivityAgreeBinding
import org.techtown.alicorn.databinding.ActivitySignupBinding

class AgreeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAgreeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAgreeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backButton.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }





        binding.emailSignupButton.setOnClickListener {
            startActivity(Intent(this, SignupActivity::class.java))
        }
    }

}