package org.techtown.alicorn

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.firebase.auth.FirebaseAuth
import org.techtown.alicorn.databinding.ActivityAgreeBinding
import org.techtown.alicorn.databinding.ActivitySignupBinding

class AgreeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAgreeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAgreeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backButton.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }



        binding.emailSignupButton.setOnClickListener {
            startActivity(Intent(this, SignupActivity::class.java))
        }



        setCheckboxListener()



    }

    private fun setCheckboxListener() {
        binding.checkBox.setOnClickListener{
            if(binding.checkBox.isChecked){
                binding.checkBox1.isChecked=true
                binding.checkBox2.isChecked=true
                binding.checkBox3.isChecked=true
                binding.checkBox4.isChecked=true
                binding.checkBox5.isChecked=true
            }
            else
            {
                binding.checkBox1.isChecked=false
                binding.checkBox2.isChecked=false
                binding.checkBox3.isChecked=false
                binding.checkBox4.isChecked=false
                binding.checkBox5.isChecked=false

            }
        }

        binding.checkBox1.setOnCheckedChangeListener{buttonView,isChecked->
            if(!isChecked){
                binding.checkBox.isChecked = false
            }
                checkChecked()

        }
        binding.checkBox2.setOnCheckedChangeListener{buttonView,isChecked->
            if(!isChecked){
                binding.checkBox.isChecked = false
            }
                checkChecked()

        }
        binding.checkBox3.setOnCheckedChangeListener{buttonView,isChecked->
            if(!isChecked){
                binding.checkBox.isChecked = false
            }
                checkChecked()
        }
        binding.checkBox4.setOnCheckedChangeListener{buttonView,isChecked->
            if(!isChecked){
                binding.checkBox.isChecked = false
            }
                checkChecked()
        }
        binding.checkBox5.setOnCheckedChangeListener{buttonView,isChecked->
            if(!isChecked){
                binding.checkBox.isChecked = false
            }
                checkChecked()

        }

    }

    private fun checkChecked() {
        binding.checkBox.isChecked = binding.checkBox1.isChecked&&
                binding.checkBox2.isChecked&&
                binding.checkBox3.isChecked&&
                binding.checkBox4.isChecked&&
                binding.checkBox5.isChecked

        val color = if(binding.checkBox1.isChecked&&
            binding.checkBox2.isChecked&&binding.checkBox3.isChecked&&binding.checkBox4.isChecked){
                binding.emailSignupButton.isEnabled = true
            "#6782b7"}
        else{
            binding.emailSignupButton.isEnabled = false
            "#aaaaaa"
        }

        binding.emailSignupButton.backgroundTintList = ColorStateList.valueOf(Color.parseColor(color))
    }

    fun onClick(view: android.view.View) {
        when(view){

            binding.arrow1->{
                val intent = Intent(this, WebViewActivity::class.java)
                intent.putExtra("TYPE","AGREE")
                intent.putExtra("NUMBER",1)
                startActivity(intent)
            }
            binding.arrow2->{
                val intent = Intent(this, WebViewActivity::class.java)
                intent.putExtra("TYPE","AGREE")
                intent.putExtra("NUMBER",2)
                startActivity(intent)
            }

            binding.arrow3->{
                val intent = Intent(this, WebViewActivity::class.java)
                intent.putExtra("TYPE","AGREE")
                intent.putExtra("NUMBER",3)
                startActivity(intent)
            }
            binding.arrow4->{
                val intent = Intent(this, WebViewActivity::class.java)
                intent.putExtra("TYPE","AGREE")
                intent.putExtra("NUMBER",4)
                startActivity(intent)
            }
            binding.arrow5-> {
                val intent = Intent(this, WebViewActivity::class.java)
                intent.putExtra("TYPE", "AGREE")
                intent.putExtra("NUMBER", 5)
                startActivity(intent)
            }

        }
    }

}