package org.techtown.alicorn.navigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.techtown.alicorn.R
import org.techtown.alicorn.databinding.FragmentMypageBinding

class MyPageFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = FragmentMypageBinding.inflate(inflater, container, false)

        return view.root
    }
}