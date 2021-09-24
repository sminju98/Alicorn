package org.techtown.alicorn.navigation

import android.content.Intent
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
        var view = FragmentHomeBinding.inflate(inflater, container, false)

        view.SearchButton.setOnClickListener {
            startActivity(Intent(getActivity(), SearchActivity::class.java))
        }

        view.Guidebutton.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("http://drugunicorn.com"))
            startActivity(intent)
        }

        view.beautyButton.setOnClickListener {
            startActivity(Intent(getActivity(), SearchActivity::class.java))
        }

        view.skinButton.setOnClickListener {
            startActivity(Intent(getActivity(), SearchActivity::class.java))
        }

        view.dietButton.setOnClickListener {
            startActivity(Intent(getActivity(), SearchActivity::class.java))
        }
        view.lightButton.setOnClickListener {
            startActivity(Intent(getActivity(), SearchActivity::class.java))
        }

        view.oldButton.setOnClickListener {
            startActivity(Intent(getActivity(), SearchActivity::class.java))
        }

        view.mindButton.setOnClickListener {
            startActivity(Intent(getActivity(), SearchActivity::class.java))
        }

        view.infoButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=oFza9GkpIQc"))
            startActivity(intent)
        }

        return view.root
    }
}