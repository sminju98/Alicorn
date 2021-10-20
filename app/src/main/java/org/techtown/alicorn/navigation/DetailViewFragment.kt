package org.techtown.alicorn.navigation

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.ktx.Firebase
import org.techtown.alicorn.AddPhotoActivity
import org.techtown.alicorn.databinding.FragmentDetailBinding
import org.techtown.alicorn.databinding.ItemDetailBinding
import org.techtown.alicorn.navigation.model.ContentDTO

class DetailViewFragment : Fragment(){
    var firestore : FirebaseFirestore? = null
    val auth = Firebase.auth
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = FragmentDetailBinding.inflate(inflater,container,false)
        firestore = FirebaseFirestore.getInstance()
        //val currentUid =FirebaseAuth.getInstance().currentUser.uid

        view.photoBtn.setOnClickListener {
            startActivity(Intent(getActivity(), AddPhotoActivity::class.java))
        }

        view.detailviewfragmentRecyclerview.adapter = DetailViewRecyclerViewAdapter()
        view.detailviewfragmentRecyclerview.layoutManager = GridLayoutManager(requireContext(),2)
        return view.root


    }

    inner class DetailViewRecyclerViewAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>(){
        var contentDTOs : ArrayList<ContentDTO> = arrayListOf()
        var contentUidList : ArrayList<String> = arrayListOf()

        init{

            firestore?.collection("images")?.whereEqualTo("uid", auth.uid)?.addSnapshotListener{ value: QuerySnapshot?, error: FirebaseFirestoreException? ->
                contentDTOs.clear()
                contentUidList.clear()
                for(snapshot in value!!.documents){
                    var item = snapshot.toObject(ContentDTO::class.java)
                    contentDTOs.add(item!!)
                    contentUidList.add(snapshot.id)
                }
                notifyDataSetChanged()
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
            return CustomViewHolder(ItemDetailBinding.inflate(LayoutInflater.from(parent.context),parent,false))

        }

        inner class CustomViewHolder(val binding: ItemDetailBinding) : RecyclerView.ViewHolder(binding.root)

        override fun getItemCount(): Int {
            return contentDTOs.size
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            var viewholder = (holder as CustomViewHolder).itemView

            //Image
            Glide.with(holder.itemView.context).load(contentDTOs!![position].imageUrl).into(holder.binding.detailviewitemImageviewContent)

            //내용 설명
            holder.binding.detailviewitemExplainTextview.text = contentDTOs!![position].explain

            //ProfileImage
           // Glide.with(holder.itemView.context).load(contentDTOs!![position].imageUrl).into(holder.binding.detailviewitemProfileImage)

        }

    }

}