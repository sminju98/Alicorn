package org.techtown.alicorn.navigation

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import org.techtown.alicorn.LoginActivity
import org.techtown.alicorn.ProfileActivity
import org.techtown.alicorn.R
import org.techtown.alicorn.databinding.FragmentMypageBinding

class MyPageFragment : Fragment(),View.OnClickListener {
private lateinit var binding: FragmentMypageBinding
private val auth = Firebase.auth
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMypageBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.button.setOnClickListener(this)
        binding.logoutButton.setOnClickListener(this)
        }

    override fun onClick(v: View?) {
        when(v){
            binding.button -> {
                startActivity(Intent(requireContext(),ProfileActivity::class.java))

            }
            binding.logoutButton -> {
                auth.signOut()
                startActivity(Intent(requireContext(),LoginActivity::class.java))
                activity?.finish()

            }
    }
  }
}