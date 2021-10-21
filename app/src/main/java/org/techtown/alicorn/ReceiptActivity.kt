package org.techtown.alicorn

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.techtown.alicorn.databinding.ActivityReceiptBinding

class ReceiptActivity : AppCompatActivity() {
    private lateinit var binding: ActivityReceiptBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityReceiptBinding.inflate(layoutInflater)
        setContentView(binding.root)


        setCheckboxListener()
    }


    private fun setCheckboxListener() {
        binding.checkBox.setOnClickListener{
            if(binding.checkBox.isChecked){
                binding.checkBox1.isChecked=true
                binding.checkBox2.isChecked=true
                binding.checkBox3.isChecked=true
                binding.checkBox4.isChecked=true
            }
            else
            {
                binding.checkBox1.isChecked=false
                binding.checkBox2.isChecked=false
                binding.checkBox3.isChecked=false
                binding.checkBox4.isChecked=false

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

    }

    private fun checkChecked() {
        binding.checkBox.isChecked = binding.checkBox1.isChecked&&
                binding.checkBox2.isChecked&&
                binding.checkBox3.isChecked&&
                binding.checkBox4.isChecked

        val color = if(binding.checkBox1.isChecked&&
            binding.checkBox2.isChecked&&binding.checkBox3.isChecked&&binding.checkBox4.isChecked){
            binding.nextBtn.isEnabled = true
            "#6782b7"}
        else{
            "#aaaaaa"
        }

        binding.nextBtn.backgroundTintList = ColorStateList.valueOf(Color.parseColor(color))
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

            binding.nextBtn->{
                val apolloClient = ApolloClient.builder()
                    .serverUrl("https://rickandmortyapi.com/graphql/")
                    .build()

                apolloClient.query(ResultQuery())
                    .enqueue(object : ApolloCall.Callback<ResultQuery.Data>() {
                        override fun onResponse(response: Response<ResultQuery.Data>) {
                            ...
                        }

                        override fun onFailure(e: ApolloException) {
                            ...
                        }
                    })
            }
        }
    }

}