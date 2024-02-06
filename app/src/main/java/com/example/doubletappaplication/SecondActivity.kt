package com.example.doubletappaplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.doubletappaplication.databinding.SecondActivityBinding
import java.io.File
import java.io.FileWriter

class SecondActivity : AppCompatActivity() {
    private val logFileName = "activity_lifecycle_log.txt"
    private lateinit var binding: SecondActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SecondActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val countFromMainActivity = intent.getIntExtra("countFromMainActivity", 0)
        displaySquare(countFromMainActivity)

        binding.backButton.setOnClickListener {
            finish()
        }

        logLifecycle("SecondActivity onCreate")
    }

    private fun displaySquare(number: Int) {
        val square = number * number
        binding.squareTextView.text = square.toString()
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
        logLifecycle("SecondActivity onStart")
    }

    override fun onResume() {
        super.onResume()
        logLifecycle("SecondActivity onResume")
    }

    override fun onPause() {
        super.onPause()
        logLifecycle("SecondActivity onPause")
    }

    override fun onStop() {
        super.onStop()
        logLifecycle("SecondActivity onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        logLifecycle("SecondActivity onDestroy")
    }
}