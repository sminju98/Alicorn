package org.techtown.alicorn

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import org.techtown.alicorn.databinding.ActivityAddPhotoBinding
import org.techtown.alicorn.databinding.ActivityDoctorBinding
import org.techtown.alicorn.navigation.model.ContentDTO
import java.text.SimpleDateFormat
import java.util.*

class DoctorActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDoctorBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDoctorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Initiate




    }

}