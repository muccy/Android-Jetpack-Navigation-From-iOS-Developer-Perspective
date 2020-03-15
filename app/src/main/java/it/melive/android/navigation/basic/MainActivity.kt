package it.melive.android.navigation.basic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main_content.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupTopBar()
        setupBottomBar()
        setupDrawer()
    }

    private fun setupTopBar() {
        val navController = findNavController(R.id.nav_host_fragment)
        val appBarConfiguration = AppBarConfiguration(topLevelDestinationIds = setOf(R.id.master_detail, R.id.image, R.id.infos), drawerLayout = drawerLayout)
        toolbar.setupWithNavController(navController, appBarConfiguration)
    }

    private fun setupBottomBar() {
        val navController = findNavController(R.id.nav_host_fragment)
        bottomNavigationView?.setupWithNavController(navController)
    }

    private fun setupDrawer() {
        val navController = findNavController(R.id.nav_host_fragment)
        drawerNavigationView?.setupWithNavController(navController)
    }
}
