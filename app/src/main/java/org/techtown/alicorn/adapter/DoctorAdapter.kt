package org.techtown.alicorn.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.techtown.alicorn.databinding.ItemDoctorBinding
import org.techtown.alicorn.navigation.model.DoctorDTO


class DoctorAdapter: ListAdapter<DoctorDTO, DoctorAdapter.ViewHolder>(diffUtil) {


    inner class ViewHolder(private  val binding: ItemDoctorBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(doctorModel : DoctorDTO){
            binding.clinicNameTextView.text = doctorModel.clinicName
            binding.doctorNameTextView.text = doctorModel.doctorName
            Glide.with(binding.doctorImage.context).load(doctorModel.doctorImg).into(binding.doctorImage)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemDoctorBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false))

    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<DoctorDTO>(){
            override fun areItemsTheSame(oldItem: DoctorDTO, newItem: DoctorDTO): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: DoctorDTO, newItem: DoctorDTO): Boolean {
                return oldItem.doctorName == newItem.doctorName
            }

        }
    }

}