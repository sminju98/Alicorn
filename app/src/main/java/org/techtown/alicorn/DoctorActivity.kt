package org.techtown.alicorn

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import org.techtown.alicorn.databinding.ActivityDoctorBinding
import org.techtown.alicorn.databinding.ItemDoctorBinding
import org.techtown.alicorn.navigation.model.DoctorDTO

class DoctorActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDoctorBinding
    private val doctorId: String by lazy {intent.getStringExtra("DOCTOR_ID")?:"0"}
    private val db = Firebase.firestore
    private var doctor:DoctorDTO?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDoctorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Toast.makeText(this,doctorId,Toast.LENGTH_SHORT).show()

        db.collection("doctor").document(doctorId).get().addOnSuccessListener{
            doctor = it.toObject(DoctorDTO::class.java)
            setView()
        }

        binding.backButton.setOnClickListener {
            finish()
        }
    }

    private fun setView() {
        binding.doctorNameTextView.text=doctor?.doctorName
        binding.clinicNameTextView.text=doctor?.clinicName
        binding.explainTitle.text=doctor?.info
        Glide.with(this).load(doctor?.profileImageUrl).into(binding.doctorImage)
    }

}