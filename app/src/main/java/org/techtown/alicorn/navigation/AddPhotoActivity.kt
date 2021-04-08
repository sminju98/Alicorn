package org.techtown.alicorn.navigation

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import org.techtown.alicorn.R
import org.techtown.alicorn.databinding.ActivityAddPhotoBinding
import org.techtown.alicorn.navigation.model.ContentDTO
import java.text.SimpleDateFormat
import java.util.*

class AddPhotoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddPhotoBinding
    var PICK_IMAGE_FROM_ALBUM =0
    var storage : FirebaseStorage? = null
    var photoUri : Uri? = null
    var auth: FirebaseAuth? = null
    var firestore : FirebaseFirestore? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddPhotoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Initiate
        storage = FirebaseStorage.getInstance()
        auth = FirebaseAuth.getInstance()
        firestore = FirebaseFirestore.getInstance()

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

    fun contentUpload() {
        //파일명 만들기

        var timestamp = SimpleDateFormat("yyyyMmdd_HHmmss").format(Date())
        var imageFileName = "Image" + timestamp + "_.png"

        var storageRef = storage?.reference?.child("images")?.child(imageFileName)

        //promise method
        storageRef?.putFile(photoUri!!)?.continueWithTask {
            return@continueWithTask storageRef.downloadUrl
        }?.addOnSuccessListener { uri ->
            var contentDTO = ContentDTO()

            //이미지 다운로드 주소 삽입
            contentDTO.imageUrl = uri.toString()

            //유저 uid 삽입
            contentDTO.uid = auth?.currentUser?.uid

            //userId 삽입
            contentDTO.userId = auth?.currentUser?.email

            //내용 설명 삽입
            contentDTO.explain = binding.addphotoEditExplain.text.toString()

            //timestamp 삽입
            contentDTO.timestamp = System.currentTimeMillis()

            firestore?.collection("images")?.document()?.set(contentDTO)

            setResult(Activity.RESULT_OK)

            Toast.makeText(this, "사진 업로드 성공", Toast.LENGTH_LONG).show()

            finish()
        }
    }
}