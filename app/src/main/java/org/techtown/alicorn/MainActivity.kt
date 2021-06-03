package org.techtown.alicorn

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.core.app.ActivityCompat
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.techtown.alicorn.databinding.ActivityMainBinding
import org.techtown.alicorn.navigation.*


class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {
    private lateinit var binding: ActivityMainBinding
    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.actionHome -> {
                supportFragmentManager.beginTransaction().replace(R.id.mainContent, HomeFragment()).commit()
                return true
            }
        }
        when (item.itemId) {
            R.id.actionChat -> {
                supportFragmentManager.beginTransaction().replace(R.id.mainContent, ChatFragment()).commit()
                return true
            }
        }
        when (item.itemId) {
            R.id.actionRecord -> {
                supportFragmentManager.beginTransaction().replace(R.id.mainContent, DetailViewFragment()).commit()
                return true
            }
        }
        when (item.itemId) {
            R.id.actionAccount -> {
                supportFragmentManager.beginTransaction().replace(R.id.mainContent, MyPageFragment()).commit()
                return true
            }
        }
        return false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.bottomNavigation.setOnNavigationItemSelectedListener(this)
        ActivityCompat.requestPermissions(
            this,
            arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),
            1
        )

        //디폴트 화면
        binding.bottomNavigation.selectedItemId = R.id.actionHome
    }
}