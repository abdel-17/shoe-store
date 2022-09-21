package com.example.shoestore

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.shoestore.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(
            this, R.layout.activity_main
        )
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host) as NavHostFragment
        navController = navHostFragment.navController
        // Log changes to the backstack.
        navController.addOnDestinationChangedListener { controller, _, _ ->
            Log.d("Backstack Changed", controller.backQueue.toString())
        }
        // Setup the action bar for navigation.
        val appBarConfiguration = AppBarConfiguration.Builder(
            // Hide the navigate-up button in the login,
            // welcome, and shoe list fragments.
            topLevelDestinationIds = setOf(
                R.id.loginFragment,
                R.id.welcomeFragment,
                R.id.shoeListFragment
            )
        ).build()
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    override fun onSupportNavigateUp(): Boolean {
        // Setup the navigate-up button.
        return navController.navigateUp()
    }
}