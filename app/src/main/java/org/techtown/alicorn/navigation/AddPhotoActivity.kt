package org.techtown.alicorn.navigation

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.storage.FirebaseStorage
import org.techtown.alicorn.R
import org.techtown.alicorn.databinding.ActivityAddPhotoBinding
import java.text.SimpleDateFormat
import java.util.*

class AddPhotoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddPhotoBinding
    var PICK_IMAGE_FROM_ALBUM =0
    var storage : FirebaseStorage? = null
    var photoUri : Uri? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddPhotoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Initiate storage
        storage = FirebaseStorage.getInstance()

        //앨범 열기
        var photoPickerIntent = Intent(Intent.ACTION_PICK)
        photoPickerIntent.type = "image/*"
        startActivityForResult(photoPickerIntent,PICK_IMAGE_FROM_ALBUM)

        //이미지 업로드 이벤트 추가
        binding.addphotoBtnUpload.setOnClickListener {
            contentUpload()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == PICK_IMAGE_FROM_ALBUM)
            if(resultCode == Activity.RESULT_OK){
                //선택된 이미지 경로
                photoUri = data?.data
                binding.addphotoImage.setImageURI(photoUri)
            } else{
                Toast.makeText(this, "사진 업로드 취소", Toast.LENGTH_LONG).show()
                finish()
            }
    }

    fun contentUpload(){
        //파일명 만들기

        var timestamp = SimpleDateFormat("yyyyMmdd_HHmmss").format(Date())
        var imageFileName = "Image" + timestamp + "_.png"

        var storageRef = storage?.reference?.child("images")?.child(imageFileName)

        //파일 업로드
        storageRef?. putFile(photoUri!!)?.addOnSuccessListener {
            Toast.makeText(this, getString(R.string.upload_success), Toast.LENGTH_LONG).show()
        }
    }
}