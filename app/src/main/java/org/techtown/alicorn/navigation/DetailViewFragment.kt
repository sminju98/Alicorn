package org.techtown.alicorn.navigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.QuerySnapshot
import org.techtown.alicorn.R
import org.techtown.alicorn.databinding.FragmentDetailBinding
import org.techtown.alicorn.databinding.ItemDetailBinding
import org.techtown.alicorn.navigation.model.ContentDTO

class DetailViewFragment : Fragment(){

    var firestore : FirebaseFirestore? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = FragmentDetailBinding.inflate(inflater,container,false)
        firestore = FirebaseFirestore.getInstance()

        view.detailviewfragmentRecyclerview.adapter = DetailViewRecyclerViewAdapter()
        view.detailviewfragmentRecyclerview.layoutManager = LinearLayoutManager(activity)
        return view.root
    }

    inner class DetailViewRecyclerViewAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>(){
        var contentDTOs : ArrayList<ContentDTO> = arrayListOf()
        var contentUidList : ArrayList<String> = arrayListOf()

        init{

            firestore?.collection("images")?.orderBy("timestamp")?.addSnapshotListener{value: QuerySnapshot?, error: FirebaseFirestoreException? ->
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
            //UserId

            holder.binding.detailviewitemProfileTextview.text = contentDTOs!![position].userId

            //Image
            Glide.with(holder.itemView.context).load(contentDTOs!![position].imageUrl).into(holder.binding.detailviewitemImageviewContent)

            //내용 설명
            holder.binding.detailviewitemExplainTextview.text = contentDTOs!![position].explain

            //좋아요
            holder.binding.detailviewitemFavoritecounterTextview.text = "좋아요 " + contentDTOs!![position].favoriteCount

            //ProfileImage
            Glide.with(holder.itemView.context).load(contentDTOs!![position].imageUrl).into(holder.binding.detailviewitemProfileImage)
1
        }

    }

}