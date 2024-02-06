package com.example.doubletappaplication

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.doubletappaplication.databinding.ActivityMainBinding
import java.io.File
import java.io.FileWriter

class MainActivity : AppCompatActivity() {

    private var count = 0
    private val logFileName = "activity_lifecycle_log.txt"
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadCountFromSavedInstanceState(savedInstanceState)

        binding.increaseButton.setOnClickListener {
            count++
            displayCount()
        }

        binding.nextActivityButton.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            intent.putExtra("countFromMainActivity", count)
            startActivity(intent)
        }

        logLifecycle("MainActivity onCreate")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("count", count)
    }

    private fun loadCountFromSavedInstanceState(savedInstanceState: Bundle?) {
        if (savedInstanceState != null) {
            count = savedInstanceState.getInt("count") + 1
            displayCount()
        }
    }

    private fun displayCount() {
        binding.countTextView.text = count.toString()
    }

    private fun logLifecycle(message: String) {
        val logFile = File(filesDir, logFileName)
        val logText = "${System.currentTimeMillis()}: $message\n"
        FileWriter(logFile, true).use {
            it.append(logText)
        }
    }

    override fun onStart() {
        super.onStart()
        logLifecycle("MainActivity onStart")
    }

    override fun onResume() {
        super.onResume()
        logLifecycle("MainActivity onResume")
    }

    override fun onPause() {
        super.onPause()
        logLifecycle("MainActivity onPause")
    }

    override fun onStop() {
        super.onStop()
        logLifecycle("MainActivity onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        logLifecycle("MainActivity onDestroy")
    }
}