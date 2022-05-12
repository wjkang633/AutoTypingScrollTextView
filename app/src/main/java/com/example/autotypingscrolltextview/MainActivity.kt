package com.example.autotypingscrolltextview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.method.ScrollingMovementMethod
import android.widget.TextView
import com.example.autotypingscrolltextview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()
    }

    private fun initViews() {
        val arr = resources.getString(R.string.log_sample).toCharArray()
        var i = 0

        binding.textView.movementMethod = ScrollingMovementMethod()

        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed(object : Runnable {
            override fun run() {
                if (i == arr.size) return
                binding.textView.append(arr[i++].toString())
                scrollBottom(binding.textView)
                handler.postDelayed(this, 1)
            }
        }, 0)
    }

    private fun scrollBottom(textView: TextView) {
        if (binding.textView.onPreDraw()) {
            val lineTop = binding.textView.layout.getLineTop(textView.lineCount)
            val scrollY = lineTop - textView.height
            if (scrollY > 0) {
                textView.scrollTo(0, scrollY)
            } else {
                textView.scrollTo(0, 0)
            }
        } else {
            return
        }
    }
}