package org.techtown.alicorn

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import org.techtown.alicorn.databinding.ActivityFindPasswordBinding
import org.techtown.alicorn.dialog.LottieDialog

class FindPasswordActivity : AppCompatActivity() {

    private lateinit var binding :ActivityFindPasswordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFindPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.findPasswordButton.setOnClickListener{
            val lottieDialog = LottieDialog()
            lottieDialog.show(supportFragmentManager,null)

            val emailAddress = binding.emailEditText.text.toString()

            Firebase.auth.sendPasswordResetEmail(emailAddress)
                .addOnCompleteListener { task ->
                    lottieDialog.dismiss()
                    if (task.isSuccessful) {
                        finish()
                    }else{
                        //Toast 아이디 확인해주세요(아이디없
                    }
                }.addOnFailureListener {
                    //Toast 네트워크 오류
                    lottieDialog.dismiss()
                }
        }

    }
}