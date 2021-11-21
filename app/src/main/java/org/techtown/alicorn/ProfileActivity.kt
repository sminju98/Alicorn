package org.techtown.alicorn

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide
import gun0912.tedimagepicker.builder.TedImagePicker
import jp.wasabeef.glide.transformations.CropCircleWithBorderTransformation
import org.techtown.alicorn.data.remote.api.CallApi
import org.techtown.alicorn.data.remote.response.user.data.UserDataResponse
import org.techtown.alicorn.databinding.ActivityProfileBinding
import java.util.*

class ProfileActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityProfileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        supportActionBar?.hide()
        setContentView(binding.root)

        loadUserData()
        Glide.with(this).load(R.drawable.doctor).circleCrop().into(binding.profileImage)
        binding.profileImage.setOnClickListener(this)
    }

    private fun loadUserData() {
        App.token?.let {
            CallApi().loadUserData(it) { b: Boolean, s: String, userDataResponse: UserDataResponse? ->
                Log.e("userLogin", "$b $s $userDataResponse")
                if (b && userDataResponse != null && userDataResponse.success) {
                    showData(userDataResponse)
                } else {
                    Toast.makeText(this, "유저 데이터 불러오기 실패", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun showData(data: UserDataResponse) {
        val todayCalendar = Calendar.getInstance()
        val birthDatCalendar = Calendar.getInstance()
        birthDatCalendar.set(Calendar.YEAR,data.data.birthday.substring(0,4).toInt())
        birthDatCalendar.set(Calendar.MONTH,data.data.birthday.substring(5,7).toInt()-1)
        birthDatCalendar.set(Calendar.DAY_OF_MONTH,data.data.birthday.substring(8,10).toInt())

        var isAfter = if(todayCalendar.get(Calendar.MONTH)>birthDatCalendar.get(Calendar.MONTH)){
            true
        }else todayCalendar.get(Calendar.MONTH)==birthDatCalendar.get(Calendar.MONTH)
                && (todayCalendar.get(Calendar.DAY_OF_MONTH)>= birthDatCalendar.get(Calendar.DAY_OF_MONTH))

        val age = todayCalendar.get(Calendar.YEAR) - birthDatCalendar.get(Calendar.YEAR) - (if(isAfter) 0 else 1)

        binding.name.text = data.data.name
        binding.ageSex.text = "만 ${age}세 ${if(data.data.sex==1)"남성" else "여셩"}"
    }

    override fun onClick(v: View?) {
        when (v) {
            binding.profileImage -> {
                TedImagePicker.with(this)
                    .start { uri -> showSingleImage(uri) }
            }
        }
    }

    private fun showSingleImage(uri: Uri) {
        Glide.with(this).load(uri).circleCrop().into(binding.profileImage)
    }
}