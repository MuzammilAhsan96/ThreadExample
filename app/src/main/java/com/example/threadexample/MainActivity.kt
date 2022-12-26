package com.example.threadexample

import android.content.ContentValues.TAG
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val button = findViewById<Button>(R.id.btnLogin)
        button.setOnClickListener {
            buttonClicked(it)
        }

    }

    private fun buttonClicked(view: View?) {
        val handler = Handler()
        val runnable: Runnable = object : Runnable {
            override fun run() {
                Log.i(TAG, "Thread Name 2: " + Thread.currentThread().name)
                synchronized(this) {
                    try {
                        Thread.sleep(5000)
                    } catch (e: InterruptedException) {
                        e.printStackTrace()
                    }
                }
                handler.post(Runnable {
                    Toast.makeText(
                        this@MainActivity,
                        "Download finished...",
                        Toast.LENGTH_SHORT
                    ).show()
                })
                handler.postDelayed(Runnable {
                    Toast.makeText(
                        this@MainActivity,
                        "10 seconds passed since download was finished...",
                        Toast.LENGTH_SHORT
                    ).show()
                }, 10000)
                Log.i(TAG, "run: Download finished.")
            }
        }
        //        runnable.run();
        Log.i(TAG, "Thread Name 1: " + Thread.currentThread().name)
        val thread = Thread(runnable)
        thread.start()
    }
}