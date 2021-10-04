package org.techtown.alicorn.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.QuerySnapshot
import org.techtown.alicorn.DoctorActivity
import org.techtown.alicorn.MainActivity
import org.techtown.alicorn.databinding.ActivitySearchBinding
import org.techtown.alicorn.databinding.ItemDoctorBinding
import org.techtown.alicorn.firestore
import org.techtown.alicorn.navigation.model.DoctorDTO

class SearchAdapter(val onItemClicked: (DoctorDTO) -> Unit) :
    RecyclerView.Adapter<SearchAdapter.ViewHolder>() {

    private lateinit var binding: ActivitySearchBinding
    var DoctorDTOs: ArrayList<DoctorDTO> = arrayListOf()

    interface OnItemClickListener {
        fun onItemClick(v: ViewHolder, data: DoctorDTO, pos: Int)
    }

    private var listener: OnItemClickListener? = null
    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

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

    }

}
