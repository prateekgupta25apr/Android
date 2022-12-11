package prateek_gupta.advancednavigationdrawer

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import prateek_gupta.advancednavigationdrawer.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    private var actionBarDrawerToggle: ActionBarDrawerToggle? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        if (supportActionBar != null)
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        actionBarDrawerToggle = object : ActionBarDrawerToggle(
            this, binding.drawerLayout,
            0, 0
        ) {
            override fun onDrawerSlide(drawerView: View, slideOffset: Float) {
                super.onDrawerSlide(drawerView, slideOffset)
                binding.mainView.translationX = slideOffset * 400
                binding.mainView.translationY = slideOffset * 10
                binding.mainView.scaleX = 1 - slideOffset * 0.27f
                binding.mainView.scaleY = 1 - slideOffset * 0.27f
                binding.mainView.requestLayout()
                ViewCompat.setElevation(binding.mainView, 27f)
                ViewCompat.setElevation(binding.navigationView.root, 27f)
            }
        }
        (actionBarDrawerToggle as ActionBarDrawerToggle).drawerArrowDrawable.color =
            ContextCompat.getColor(this, R.color.white)
        binding.drawerLayout.addDrawerListener(
            actionBarDrawerToggle as ActionBarDrawerToggle)
        (actionBarDrawerToggle as ActionBarDrawerToggle).syncState()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (item.itemId == android.R.id.home) {
            // Syncing onOptionsItemSelected() methods of the Activity and
            // ActionBarDrawerToggle
            if (actionBarDrawerToggle!!.onOptionsItemSelected(item))
                true
            else super.onOptionsItemSelected(
                item
            )
        } else false
    }
}