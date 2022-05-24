package com.appjuego.contando

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class MainActivity10 : AppCompatActivity() {
    var proyeccion : String= ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main10)
        val bundleProyeccion = intent.extras
        proyeccion = bundleProyeccion?.getString("keyValorProyeccion")!!
        Toast.makeText(this, proyeccion, Toast.LENGTH_SHORT).show()
    }

}