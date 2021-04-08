package org.techtown.alicorn

import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import org.techtown.alicorn.databinding.ActivityMainBinding
import org.techtown.alicorn.navigation.AddPhotoActivity
import org.techtown.alicorn.navigation.DetailViewFragment
import org.techtown.alicorn.navigation.GridFragment
import org.techtown.alicorn.navigation.UserFragment
import java.util.jar.Manifest

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {
    private lateinit var binding : ActivityMainBinding
    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.actionHome -> {
                supportFragmentManager.beginTransaction().replace(R.id.mainContent,DetailViewFragment()).commit()
                return true
            }
        }
        when(item.itemId){
            R.id.actionSearch -> {
                supportFragmentManager.beginTransaction().replace(R.id.mainContent,GridFragment()).commit()
                return true
            }
        }
        when(item.itemId){
            R.id.actionAddPhoto -> {
                if(ContextCompat.checkSelfPermission(this,android.Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
                    startActivity(Intent(this,AddPhotoActivity::class.java))
                }
                return true
            }
        }
        when(item.itemId){
            R.id.actionAccount -> {
                supportFragmentManager.beginTransaction().replace(R.id.mainContent, UserFragment()).commit()
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
        ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),1)
    }
}