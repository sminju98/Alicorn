package org.techtown.alicorn

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.ActionCodeSettings
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import org.techtown.alicorn.databinding.ActivityLoginBinding
import org.techtown.alicorn.databinding.ActivitySignupBinding
import org.techtown.alicorn.navigation.model.UserDTO

class SignupActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignupBinding

    var auth: FirebaseAuth? = null
    val db = Firebase.firestore

    var googleSignInClient: GoogleSignInClient? = null
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
                    Toast.makeText(this, "회원가입에 성공하셨습니다. 이메일 인증을 받아주세요.", Toast.LENGTH_LONG).show()
                    updateUserData()
                    //계정 생성
                } else if (task.exception?.message.isNullOrEmpty()) {
                    //실패, 에러메세지

                    Toast.makeText(this, "회원가입 실패.", Toast.LENGTH_LONG).show()
                }
            }
    }

    private fun updateUserData() {
        val user = UserDTO().apply {
            uid = auth?.uid
            email = auth?.currentUser?.email
            activation = false
        }

        auth?.uid?.let {
            db.collection("users").document(it).set(user).addOnSuccessListener {
                sendVerificationEmail()
            }
        }
    }

    private fun sendVerificationEmail() {
        val user = auth?.currentUser!!

        val url = "https://us-central1-alicorn-ff5a3.cloudfunctions.net/activateUser?uid=" + user.uid
        Log.e("url", url)
        val actionCodeSettings = ActionCodeSettings.newBuilder()
            .setUrl(url)
            .build()

        user.sendEmailVerification(actionCodeSettings)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    finish()
                } else {
                    Log.e("fail", task.exception?.message.toString())
                }
            }
    }
}