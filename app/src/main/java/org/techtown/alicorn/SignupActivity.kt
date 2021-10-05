package org.techtown.alicorn

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import org.techtown.alicorn.databinding.ActivityLoginBinding
import org.techtown.alicorn.databinding.ActivitySignupBinding

class SignupActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignupBinding
    var auth: FirebaseAuth? = null
    var googleSignInClient : GoogleSignInClient?= null
    var GOOGLE_LOGIN_CODE = 9001
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        binding.backButton.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
        binding.emailSignupButton.setOnClickListener {
            signupEmail()
        }
    }

        fun signupEmail() {
            auth?.createUserWithEmailAndPassword(
                binding.emailEditText.text.toString(),
                binding.passwordEditText.text.toString()
            )
                ?.addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this, "회원가입에 성공하셨습니다.", Toast.LENGTH_LONG).show()
                        startActivity(Intent(this, LoginActivity::class.java))
                        //계정 생성
                    } else if (task.exception?.message.isNullOrEmpty()) {
                        //실패, 에러메세지

                        Toast.makeText(this, "회원가입 실패.", Toast.LENGTH_LONG).show()
                    }
                }
        }
}