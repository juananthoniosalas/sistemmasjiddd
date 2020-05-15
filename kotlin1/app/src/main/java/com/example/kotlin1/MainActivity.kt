package com.example.kotlin1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    val context = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        jadwal.setOnClickListener {
            val intent = Intent(context,com.example.kotlin1.jadwal::class.java)
            startActivity(intent)
        }

        identitas.setOnClickListener {
            val intent = Intent(context,com.example.kotlin1.identitas::class.java)
            startActivity(intent)
        }
            pengumuman.setOnClickListener {
                val intent = Intent(context,com.example.kotlin1.pengumuman::class.java)
                startActivity(intent)
            }
            marquee.setOnClickListener {
                val intent = Intent(context,com.example.kotlin1.marquee::class.java)
                startActivity(intent)
            }
            tagline.setOnClickListener {
                val intent = Intent(context,com.example.kotlin1.tagline::class.java)
                startActivity(intent)
            }
            slideshow.setOnClickListener {
                val intent = Intent(context,com.example.kotlin1.slideshow::class.java)
                startActivity(intent)
            }
    }
}