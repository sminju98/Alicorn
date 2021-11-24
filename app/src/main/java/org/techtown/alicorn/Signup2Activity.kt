package org.techtown.alicorn

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import org.techtown.alicorn.data.remote.response.user.data.Data
import org.techtown.alicorn.databinding.ActivityLoginBinding
import org.techtown.alicorn.databinding.ActivitySignup2Binding

class Signup2Activity : AppCompatActivity() {
    private lateinit var binding: ActivitySignup2Binding

    var firestore: FirebaseFirestore? = null
    var auth: FirebaseAuth? = null
    val db = Firebase.firestore
    val currentUser = auth?.currentUser

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignup2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.SignupButton.setOnClickListener {
            registerInfoEmail()
        }
    }



    fun registerInfoEmail() {
        auth = FirebaseAuth.getInstance()
        firestore = FirebaseFirestore.getInstance()

        db.collection("user").document(currentUser!!.uid)
            .set("아무")
            .addOnSuccessListener { }
            .addOnFailureListener { }

    }

}