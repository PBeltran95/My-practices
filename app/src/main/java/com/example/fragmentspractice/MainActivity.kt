package com.example.fragmentspractice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.fragmentspractice.databinding.ActivityMainBinding
import com.example.fragmentspractice.databinding.ActivityMainBinding.inflate



class MainActivity : AppCompatActivity() {

    private lateinit var mBinding:ActivityMainBinding

    //Configurar el fragmento activo y el manager
    private lateinit var mActiveFragment: Fragment
    private lateinit var mFragmentManager: FragmentManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = inflate(layoutInflater)
        setContentView(mBinding.root)
        setupBottomNav()
    }

    private fun setupBottomNav(){
        mFragmentManager = supportFragmentManager
        // Instanciar cada fragment

        val homeFragment = HomeFragment()
        val addFragment = AddFragment()
        val profileFragment = ProfileFragment()

        mActiveFragment = homeFragment

        mFragmentManager.beginTransaction()
            .add(R.id.hostFragment, profileFragment, ProfileFragment::class.java.name)
            .hide(profileFragment).commit()

        mFragmentManager.beginTransaction()
            .add(R.id.hostFragment, addFragment, AddFragment::class.java.name)
            .hide(addFragment).commit()

        mFragmentManager.beginTransaction()
            .add(R.id.hostFragment, homeFragment, HomeFragment::class.java.name).commit()
        //Estan todos configurados pero ninguno se muestra, hay configurar los botones


        //Aca se configuran los botones de la barra
        mBinding.bottomNav.setOnItemSelectedListener {
            when(it.itemId){
                R.id.action_home -> {
                    mFragmentManager.beginTransaction().hide(mActiveFragment)
                        .show(homeFragment).commit()
                    mActiveFragment = homeFragment
                    true
                }

                R.id.action_add -> {
                    mFragmentManager.beginTransaction().hide(mActiveFragment)
                        .show(addFragment).commit()
                    mActiveFragment = addFragment
                    true
                }

                R.id.action_profile -> {
                    mFragmentManager.beginTransaction().hide(mActiveFragment)
                        .show(profileFragment).commit()
                    mActiveFragment = profileFragment
                    true
                }
                else -> false
            }
        }
    }
}