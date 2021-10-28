package org.techtown.alicorn.navigation

import android.content.Intent
import com.iamport.sdk.domain.core.Iamport
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.techtown.alicorn.AddPhotoActivity
import org.techtown.alicorn.SearchActivity
import org.techtown.alicorn.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        Iamport.create(this)
        var view = FragmentHomeBinding.inflate(inflater, container, false)
        view.searchButton.setOnClickListener {
            startActivity(Intent(getActivity(), SearchActivity::class.java))
        }


        view.treatButton.setOnClickListener {
            startActivity(Intent(getActivity(), SearchActivity::class.java))
        }

        view.testButton.setOnClickListener {
        }
        return view.root
    }
}