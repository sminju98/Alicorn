package org.techtown.alicorn

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.QuerySnapshot
import org.techtown.alicorn.databinding.ActivitySearchBinding
import org.techtown.alicorn.databinding.ItemDoctorBinding
import org.techtown.alicorn.navigation.model.DoctorDTO

var firestore: FirebaseFirestore? = null

class SearchActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySearchBinding
    var i = 3

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firestore = FirebaseFirestore.getInstance()

        binding.searchRecyclerView.adapter = SearchAdapter()
        binding.searchRecyclerView.layoutManager = LinearLayoutManager(this)

        binding.backButton.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
        binding.searchButton.setOnClickListener {
        }

    }


    inner class SearchAdapter : RecyclerView.Adapter<SearchAdapter.ViewHolder>() {
        var DoctorDTOs: ArrayList<DoctorDTO> = arrayListOf()

        init {
            firestore?.collection("doctor")
                ?.addSnapshotListener { value: QuerySnapshot?, error: FirebaseFirestoreException? ->
                    // ArrayList 비워줌
                    DoctorDTOs.clear()
                    for (snapshot in value!!.documents) {
                        var item = snapshot.toObject(DoctorDTO::class.java)
                        DoctorDTOs.add(item!!)
                    }

                    notifyDataSetChanged()
                }
        }

        inner class ViewHolder(val binding: ItemDoctorBinding) :
            RecyclerView.ViewHolder(binding.root)


        override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
            return ViewHolder(
                ItemDoctorBinding.inflate(
                    LayoutInflater.from(viewGroup.context),
                    viewGroup,
                    false
                )
            )
        }

        override fun getItemCount(): Int {
            return DoctorDTOs.size
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            var viewHolder = (holder as ViewHolder).itemView

            holder.binding.doctorNameTextView.text = DoctorDTOs[position].doctorName
            holder.binding.clinicNameTextView.text = DoctorDTOs[position].clinicName
            Glide.with(viewHolder.context).load(DoctorDTOs!![position].profileImageUrl)
                .into(holder.binding.doctorImage)

            holder.itemView.setOnClickListener {
                var intent = Intent(viewHolder.context, DoctorActivity::class.java).apply {
                    //putExtra("data", position)
                }.run { startActivity(this) }

            }

        }
    }
}

