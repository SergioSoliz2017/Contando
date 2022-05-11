package com.appjuego.contando

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        siguiente()
    }

    private fun siguiente () {
        botonSiguiente.setOnClickListener{
            val ventana = Intent(this, MainActivity2:: class.java)
            startActivity(ventana)
        }
    }
}
