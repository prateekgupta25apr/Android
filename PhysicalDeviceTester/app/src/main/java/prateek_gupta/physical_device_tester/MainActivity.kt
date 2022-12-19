package prateek_gupta.physical_device_tester

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import prateek_gupta.physical_device_tester.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.instagram.setOnClickListener { launchApp("http://instagram.com/_u/gupta__prateek") }
        binding.linkedIn.setOnClickListener { launchApp("https://www.linkedin.com/in/prateek-gupta-5a8122196/") }
        binding.github.setOnClickListener { launchApp("https://github.com/prateekgupta25apr") }
        binding.hackerrank.setOnClickListener { launchApp("https://www.hackerrank.com/prateekgupta2504?hr_r=1") }
        binding.stackoveflow.setOnClickListener { launchApp("https://stackoverflow.com/users/13329963/prateek-gupta") }
        binding.twitter.setOnClickListener { launchApp("https://twitter.com/GuPtA__PraTeeK") }
        binding.facebook.setOnClickListener { launchApp("fb://facewebmodal/f?href=https://www.facebook.com/prateek.gupta.12764/") }
    }

    private fun launchApp(url: String?) {
        val uri = Uri.parse(url)
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = uri
        startActivity(intent)
    }
}