package com.example.shoestore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.example.shoestore.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(
            this, R.layout.activity_main
        )
        val navHostFragment = supportFragmentManager.findFragmentById(
            R.id.nav_host
        ) as NavHostFragment
        navController = navHostFragment.navController
        // Set the top level destinations to the login, welcome,
        // and shoe list fragments to hide the navigate-up button.
        val appBarConfiguration = AppBarConfiguration.Builder(
             topLevelDestinationIds = setOf(
                 R.id.loginFragment,
                 R.id.welcomeFragment,
                 R.id.shoeListFragment
             )
        ).build()
        // Setup the action bar for navigation.
        NavigationUI.setupActionBarWithNavController(
            this, navController, appBarConfiguration
        )
    }

    override fun onSupportNavigateUp(): Boolean {
        // Setup the navigate-up action.
        return navController.navigateUp()
    }
}