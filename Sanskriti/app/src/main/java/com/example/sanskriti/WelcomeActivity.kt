package com.example.sanskriti

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.sanskriti.databinding.ActivityWelcomeBinding

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

        // Show app name and slogan
        binding.appName.text = getString(R.string.app_name)
        binding.appSlogan.text = getString(R.string.app_slogan)

        // Navigate to AuthActivity after 2 seconds
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, AuthActivity::class.java))
            finish()
        }, 2000)
    }
}