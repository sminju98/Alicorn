package org.techtown.alicorn

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import org.techtown.alicorn.databinding.ActivityDoctorBinding
import org.techtown.alicorn.navigation.model.DoctorDTO
import java.text.SimpleDateFormat
import java.util.*

class DoctorActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDoctorBinding
    private val doctorId: String by lazy {intent.getStringExtra("DOCTOR_ID")?:"0"}
    private val db = Firebase.firestore
    private var doctor:DoctorDTO?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDoctorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db.collection("doctor").document(doctorId).get().addOnSuccessListener{
            doctor = it.toObject(DoctorDTO::class.java)
            setView()
        }


        binding.receiptBtn.setOnClickListener {
            val intent = Intent(this,ReceiptActivity::class.java)
            intent.putExtra(ReceiptActivity.PHONE_NUMBER,doctor?.phoneNumber)
            intent.putExtra(ReceiptActivity.PRICE,doctor?.price)
            intent.putExtra(ReceiptActivity.DOCTOR_NAME,doctor?.doctorName)

            startActivity(intent)
        }
    }

    private fun setView() {
        binding.doctorNameTextView.text=doctor?.doctorName
        binding.clinicNameTextView.text=doctor?.clinicName
        binding.explainTitle.text=doctor?.info
        binding.explainContent.text=doctor?.context
       binding.majorTextView.text=doctor?.major+" 전문의"

        binding.tag.text=doctor?.tag?.toString()
        binding.telephoneTextView.text=doctor?.telephone
        binding.careerTextView.text=doctor?.careers.toString()
        binding.priceTextView.text=doctor?.price+"원"



//
//        val cal = Calendar.getInstance()
//
//        cal.set(Calendar.YEAR,2021)
//        cal.set(Calendar.MONTH,10)
//        cal.set(Calendar.DAY_OF_MONTH,24)
//
//
//        cal.set(Calendar.HOUR_OF_DAY,9)
//        cal.set(Calendar.MINUTE,0)
//        cal.set(Calendar.SECOND,0)
//        cal.timeZone = TimeZone.getTimeZone("GMT+9")
//
//        val sdf = SimpleDateFormat("HH:mm:ss")

        Glide.with(this).load(doctor?.profileImageUrl).into(binding.doctorImage)
    }

}