package com.example.eko8757.footballclubmatchdatabase.main

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import com.example.eko8757.footballclubmatchdatabase.R
import com.example.eko8757.footballclubmatchdatabase.match.FavoritesMatch
import com.example.eko8757.footballclubmatchdatabase.match.NextMatch
import com.example.eko8757.footballclubmatchdatabase.match.PreviousMatch
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var fragment: Fragment
    private lateinit var fragmentManager: FragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottom_navigation.inflateMenu(R.menu.bottom_navigation_menu)
        fragmentManager = getSupportFragmentManager()

        fragmentManager.beginTransaction().replace(R.id.mainContainer, PreviousMatch()).commit()

        bottom_navigation.setOnNavigationItemSelectedListener { item ->
            val id = item.getItemId()
            when (id) {
                R.id.previous -> fragment = PreviousMatch()
                R.id.next -> fragment = NextMatch()
                R.id.favorites -> fragment = FavoritesMatch()
            }

            val transaction = fragmentManager.beginTransaction()
            transaction.replace(R.id.mainContainer, fragment).commit()
            true
        }
    }
}
