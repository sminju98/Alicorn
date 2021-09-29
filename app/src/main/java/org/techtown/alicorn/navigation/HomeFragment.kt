package org.techtown.alicorn.navigation

import androidx.fragment.app.Fragment
import android.view.View
import android.os.Bundle

import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import org.techtown.alicorn.adapter.DoctorAdapter
import org.techtown.alicorn.databinding.FragmentHomeBinding
import org.techtown.alicorn.navigation.model.DoctorDTO


class HomeFragment : Fragment() {
    private lateinit var doctorDB : DatabaseReference
    private lateinit var adapter : DoctorAdapter

    private val doctorList = mutableListOf<DoctorDTO>()
    private val listener = object : ChildEventListener{
        override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {

            val doctorDTO = snapshot.getValue(DoctorDTO::class.java)

            doctorDTO ?:return

            doctorList.add(doctorDTO)
            adapter.submitList(doctorList)
        }

        override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
        }

        override fun onChildRemoved(snapshot: DataSnapshot) {
        }

        override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {
        }

        override fun onCancelled(error: DatabaseError) {
        }


    }

    private var binding:FragmentHomeBinding? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view,savedInstanceState)

        val fragmentHomeBinding = FragmentHomeBinding.bind(view)
        binding = fragmentHomeBinding

        doctorDB = Firebase.database.reference.child("doctor")
        adapter = DoctorAdapter()

        adapter.submitList(mutableListOf<DoctorDTO>().apply{

        add(DoctorDTO("송민주","테스트병원","gs://alicorn-ff5a3.appspot.com/doctorProfileImages/증명사진.jpg"))

       })

        doctorDB.addChildEventListener(listener)


        fragmentHomeBinding.doctorRecyclerView.adapter = DoctorAdapter()
        fragmentHomeBinding.doctorRecyclerView.layoutManager = LinearLayoutManager(context)



    }

    override fun onResume() {
        doctorList.clear()
        super.onResume()

        adapter.notifyDataSetChanged()
    }
    override fun onDestroyView() {
        super.onDestroyView()
        doctorDB.removeEventListener(listener)
    }

}