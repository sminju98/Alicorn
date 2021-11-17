package org.techtown.alicorn.navigation

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import org.techtown.alicorn.AddPhotoActivity
import org.techtown.alicorn.PhotoViewActivity
import org.techtown.alicorn.R
import org.techtown.alicorn.databinding.FragmentDetailBinding
import org.techtown.alicorn.databinding.ItemDetailBinding
import org.techtown.alicorn.navigation.model.ContentDTO
import java.util.*

class DetailViewFragment : Fragment() {
    var firestore: FirebaseFirestore? = null
    val auth = Firebase.auth
    var PICK_IMAGE_FROM_ALBUM = 0
    var storage: FirebaseStorage? = null
    var photoUri: Uri? = null
    private lateinit var binding: FragmentDetailBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        firestore = FirebaseFirestore.getInstance()
        //val currentUid =FirebaseAuth.getInstance().currentUser.uid

        binding.detailviewfragmentRecyclerview.adapter = DetailViewRecyclerViewAdapter()
        binding.detailviewfragmentRecyclerview.layoutManager =
            GridLayoutManager(requireContext(), 2)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.photoBtn.setOnClickListener {
            startActivity(
                Intent(
                    getActivity(),
                    AddPhotoActivity::class.java
                )
            )
        }
        binding.back.setOnClickListener {
            activity?.let {
                it.supportFragmentManager?.beginTransaction()
                    .replace(R.id.mainContent, HomeFragment()).commit()
            }
        }
    }

    inner class DetailViewRecyclerViewAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
        var contentDTOs: ArrayList<ContentDTO> = ArrayList()
        var contentUidList: ArrayList<String> = ArrayList()

        init {

            firestore?.collection("images")?.whereEqualTo("uid", auth.uid)
                ?.addSnapshotListener { value: QuerySnapshot?, error: FirebaseFirestoreException? ->
                    contentDTOs.clear()
                    contentUidList.clear()
                    for (snapshot in value!!.documents) {
                        var item = snapshot.toObject(ContentDTO::class.java)
                        contentDTOs.add(item!!)
                        contentUidList.add(snapshot.id)
                    }
                    notifyDataSetChanged()
                }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
            return CustomViewHolder(
                ItemDetailBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )

        }

        inner class CustomViewHolder(val binding: ItemDetailBinding) :
            RecyclerView.ViewHolder(binding.root)

        override fun getItemCount(): Int {
            return contentDTOs.size
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            var viewholder = (holder as CustomViewHolder).itemView

            //Image
            Glide.with(holder.itemView.context).load(contentDTOs!![position].imageUrl)
                .into(holder.binding.detailviewitemImageviewContent)

            //내용 설명
            holder.binding.detailviewitemExplainTextview.text = contentDTOs!![position].explain

            holder.binding.root.setOnClickListener {
                Toast.makeText(context, "click", Toast.LENGTH_SHORT).show()
                val intent = Intent(context, PhotoViewActivity::class.java)
                intent.putExtra(PhotoViewActivity.IMAGEURL, contentDTOs!![position].imageUrl)
                startActivity(intent)
            }
        }
        //ProfileImage
        // Glide.with(holder.itemView.context).load(contentDTOs!![position].imageUrl).into(holder.binding.detailviewitemProfileImage)

    }

}