package com.example.sanskriti

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.ui.AppBarConfiguration
import com.example.sanskriti.databinding.ActivityWelcomeBinding
import com.google.android.material.navigation.NavigationView
import androidx.appcompat.app.ActionBarDrawerToggle

class WelcomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWelcomeBinding
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        
        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.nav_leaderboard, R.id.nav_privacy, R.id.nav_disclaimer, R.id.nav_faq, R.id.nav_settings),
            drawerLayout
        )

        val toggle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            binding.toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        navView.setNavigationItemSelectedListener { menuItem ->
            when(menuItem.itemId) {
                R.id.nav_leaderboard -> { /* Handle leaderboard */ }
                R.id.nav_privacy -> { /* Handle privacy */ }
                R.id.nav_disclaimer -> { /* Handle disclaimer */ }
                R.id.nav_faq -> { /* Handle FAQ */ }
                R.id.nav_settings -> { /* Handle settings */ }
            }
            drawerLayout.closeDrawer(GravityCompat.START)
            true
        }

        // Initialize UI elements
        binding.appName.text = getString(R.string.app_name)
        binding.appSlogan.text = getString(R.string.app_slogan)
        binding.versionText.text = getString(R.string.version)

        // Handle get started button click
        binding.getStartedButton.setOnClickListener {
            startActivity(Intent(this, AuthActivity::class.java))
            finish()
        }

        // Auto-navigate after 2 seconds if user doesn't click
        Handler(Looper.getMainLooper()).postDelayed({
            if (!isFinishing) {
                startActivity(Intent(this, AuthActivity::class.java))
                finish()
            }
        }, 2000)
    }
}