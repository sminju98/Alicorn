package org.techtown.alicorn

import android.Manifest
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.techtown.alicorn.databinding.ActivityReceiptBinding
import android.net.Uri
import android.telephony.SmsManager
import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide

import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.normal.TedPermission
import gun0912.tedimagepicker.builder.TedImagePicker


class ReceiptActivity : AppCompatActivity() {
    private lateinit var binding: ActivityReceiptBinding
    private val phoneNumber: String by lazy { intent.getStringExtra(PHONE_NUMBER)?:"" }
    private val price: String by lazy { intent.getStringExtra(PRICE)?:"" }
    private val doctorName: String by lazy { intent.getStringExtra(DOCTOR_NAME)?:"" }
    private val photoList: ArrayList<Uri> = ArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityReceiptBinding.inflate(layoutInflater)
        setContentView(binding.root)


        setCheckboxListener()
    }

    var permissionlistener: PermissionListener = object : PermissionListener {
        override fun onPermissionGranted() {
            val sms: SmsManager = SmsManager.getDefault()
            sms.sendTextMessage(
                phoneNumber,
                null,
                "주민등록번호 앞자리 "+binding.birthdayText.text.toString()+"\n"+binding.name.text.toString()+"님께서 진료를 신청하셨습니다. " + "증상은 다음과 같습니다.\n"+binding.editContextText.text.toString()
                ,
                null,
                null
            )
            Toast.makeText(this@ReceiptActivity, "진료신청이 완료되었습니다.", Toast.LENGTH_SHORT).show()

        }

        override fun onPermissionDenied(deniedPermissions: List<String>) {
        }
    }

    private fun setCheckboxListener() {
        binding.checkBox.setOnClickListener {
            if (binding.checkBox.isChecked) {
                binding.checkBox1.isChecked = true
                binding.checkBox2.isChecked = true
                binding.checkBox3.isChecked = true
                binding.checkBox4.isChecked = true
            } else {
                binding.checkBox1.isChecked = false
                binding.checkBox2.isChecked = false
                binding.checkBox3.isChecked = false
                binding.checkBox4.isChecked = false

            }
        }

        binding.checkBox1.setOnCheckedChangeListener { buttonView, isChecked ->
            if (!isChecked) {
                binding.checkBox.isChecked = false
            }
            checkChecked()

        }
        binding.checkBox2.setOnCheckedChangeListener { buttonView, isChecked ->
            if (!isChecked) {
                binding.checkBox.isChecked = false
            }
            checkChecked()

        }
        binding.checkBox3.setOnCheckedChangeListener { buttonView, isChecked ->
            if (!isChecked) {
                binding.checkBox.isChecked = false
            }
            checkChecked()
        }
        binding.checkBox4.setOnCheckedChangeListener { buttonView, isChecked ->
            if (!isChecked) {
                binding.checkBox.isChecked = false
            }
            checkChecked()

        }

    }

    private fun checkChecked() {
        binding.checkBox.isChecked = binding.checkBox1.isChecked &&
                binding.checkBox2.isChecked &&
                binding.checkBox3.isChecked &&
                binding.checkBox4.isChecked

        val color = if (binding.checkBox1.isChecked &&
            binding.checkBox2.isChecked && binding.checkBox3.isChecked && binding.checkBox4.isChecked
        ) {
            binding.nextBtn.isEnabled = true
            "#6782b7"
        } else {
            "#aaaaaa"
        }

        binding.nextBtn.backgroundTintList = ColorStateList.valueOf(Color.parseColor(color))
    }

    fun onClick(view: View) {
        when (view) {

            binding.addPhotoCV -> {

                TedImagePicker.with(this)
                    .max(3 - photoList.size,"최대 이미지 개수는 3장 입니다.")
                    .startMultiImage { uriList ->
                        photoList.addAll(uriList)
                        showMultiImage(photoList)
                    }


            }


            binding.photo1RemoveIV -> {


            }


            binding.photo2RemoveIV -> {


            }


            binding.photo3RemoveIV -> {


            }


            binding.photo1CV -> {


            }

            binding.photo2CV -> {


            }

            binding.photo3CV -> {


            }
            binding.arrow1 -> {
                val intent = Intent(this, WebViewActivity::class.java)
                intent.putExtra("TYPE", "AGREE")
                intent.putExtra("NUMBER", 1)
                startActivity(intent)
            }
            binding.arrow2 -> {
                val intent = Intent(this, WebViewActivity::class.java)
                intent.putExtra("TYPE", "AGREE")
                intent.putExtra("NUMBER", 2)
                startActivity(intent)
            }

            binding.arrow3 -> {
                val intent = Intent(this, WebViewActivity::class.java)
                intent.putExtra("TYPE", "AGREE")
                intent.putExtra("NUMBER", 3)
                startActivity(intent)
            }
            binding.arrow4 -> {
                val intent = Intent(this, WebViewActivity::class.java)
                intent.putExtra("TYPE", "AGREE")
                intent.putExtra("NUMBER", 4)
                startActivity(intent)
            }

            binding.nextBtn -> {

                TedPermission.create()
                    .setPermissionListener(permissionlistener)
                    .setDeniedMessage("권한 설정에 동의하지 않으시면 예약 문자를 보내실 수 없습니다.")
                    .setPermissions(Manifest.permission.SEND_SMS)
                    .check();


                val intent = Intent(this, PayActivity::class.java)
                intent.putExtra(PRICE, price)
                intent.putExtra(PATIENT, binding.name.text.toString())
                intent.putExtra(DOCTOR_NAME, doctorName)
                startActivity(intent)

                finish()



            }
        }
    }

    private fun showMultiImage(photoList: ArrayList<Uri>) {

        if(photoList?.get(0)!=null){
            Glide.with(this).load(photoList[0]).into(binding.photo1CV)
            binding.photo1CV.visibility=View.VISIBLE
        }
        else{
            binding.photo1CV.visibility=View.GONE
        }
        if(photoList?.get(1)!=null){
            Glide.with(this).load(photoList[1]).into(binding.photo2CV)
            binding.photo2CV.visibility=View.VISIBLE
        }
        else{
            binding.photo2CV.visibility=View.GONE
        }



        if(photoList?.get(2)!=null){
            Glide.with(this).load(photoList[2]).into(binding.photo3CV)
            binding.photo3CV.visibility=View.VISIBLE
        }
        else{
            binding.photo3CV.visibility=View.GONE
        }
    }

    companion object {

        const val PRICE = "PRICE"
        const val PHONE_NUMBER = "PHONE_NUMBER"
        const val DOCTOR_NAME = "DOCTOR_NAME"
        const val PATIENT = "PATIENT"
    }
}