package org.techtown.alicorn

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import org.techtown.alicorn.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    var auth: FirebaseAuth? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = FirebaseAuth.getInstance()
        binding.emailLoginButton.setOnClickListener {
            signinEmail()
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
                    //계정 생성
                    moveMainpage(task.result?.user)
                } else if (task.exception?.message.isNullOrEmpty()) {
                    //실패, 에러메세지
                    Toast.makeText(this, "회원가입 실패.", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(this, "이미 가입된 회원입니다. 자동으로 로그인합니다.", Toast.LENGTH_LONG).show()
                    signinEmail()
                }
            }
    }

    fun signinEmail() {
        auth?.createUserWithEmailAndPassword(
            binding.emailEditText.text.toString(),
            binding.passwordEditText.text.toString()
        )
            ?.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    //로그인 성공
                    moveMainpage(task.result?.user)
                } else {
                    //로그인 실패, 에러메세지
                    Toast.makeText(this, "로그인 실패. 이메일과 비밀번호를 확인해주세요", Toast.LENGTH_LONG).show()
                }
            }

    }


    fun moveMainpage(user: FirebaseUser?) {
        if (user != null) {
            startActivity(Intent(this,MainActivity::class.java))


        }


    }

}