package org.techtown.alicorn

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.FirebaseFirestore
import org.techtown.alicorn.adapter.SearchAdapter
import org.techtown.alicorn.databinding.ActivitySearchBinding

var firestore: FirebaseFirestore? = null

class SearchActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySearchBinding
    private lateinit var searchAdapter: SearchAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firestore = FirebaseFirestore.getInstance()

        searchAdapter = SearchAdapter(onItemClicked = {
            val intent = Intent(this, DoctorActivity::class.java)
            intent.putExtra("DOCTOR_ID", it.id ?: "0")
            startActivity(intent)

        })
        binding.searchRecyclerView.adapter = searchAdapter
        binding.searchRecyclerView.layoutManager = LinearLayoutManager(this)

        binding.backButton.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

    }
}

