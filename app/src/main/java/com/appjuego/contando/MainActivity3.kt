package com.appjuego.contando

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main3.*

class MainActivity3 : AppCompatActivity() {

    var listaMes = listOf("")
    var listaAño = listOf("")
    var arregloAbril: Array<String>? = null
    var arregloMayo: Array<String>? = null
    var arregloJunio: Array<String>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)
        val bundleAbril = intent.extras
        val bundleMayo = intent.extras
        val bundleJunio = intent.extras
        arregloAbril= bundleAbril?.getStringArray("keyValoresAbril")!!
        arregloMayo = bundleMayo?.getStringArray("keyValoresMayo")!!
        arregloJunio = bundleJunio?.getStringArray("keyValoresJunio")!!
        siguiente ()

    }

    private fun siguiente () {
        var mesInicioSelec: String = "Abril"
        var mesFinalSelec: String = "Marzo"
        var añoInicioSelec: String = "2021"
        var añoFinalSelec: String = "2026"
        var posMesIni: Int = 3
        var posMesFin: Int = 2
        SiguienteProyeccion.setOnClickListener {
            if (porcentajeValido()) {
                if (validar(posMesIni, posMesFin, añoInicioSelec, añoFinalSelec)) {
                    val bundleAbril = Bundle()
                    val bundleMayo = Bundle()
                    val bundleJunio = Bundle()
                    val bundleMesAño = Bundle()
                    val bundleProyecTasa = Bundle()
                    val porcentajeProyec:Double = obtenerProbabilidad(porcentaje_proyeccion.text.toString().toDouble())
                    val tasaProyeccion : Double = obtenerProbabilidad(tasa_proyeccion_precio.text.toString().toDouble())
                    val arregloProyecTasa = arrayOf(porcentajeProyec.toString(),tasaProyeccion.toString())
                    val arregloMesAño = arrayOf(posMesIni.toString(),posMesFin.toString(),mesInicioSelec,mesFinalSelec,añoInicioSelec,añoFinalSelec)
                    bundleAbril.putStringArray("keyValoresAbril", arregloAbril)
                    bundleMayo.putStringArray("keyValoresMayo", arregloMayo)
                    bundleJunio.putStringArray("keyValoresJunio", arregloJunio)
                    bundleMesAño.putStringArray("keyValoresMesAño", arregloMesAño)
                    bundleProyecTasa.putStringArray("keyProyecTasa",arregloProyecTasa)
                    val ventana = Intent(this, MainActivity4:: class.java)
                    ventana.putExtras(bundleAbril)
                    ventana.putExtras(bundleMayo)
                    ventana.putExtras(bundleJunio)
                    ventana.putExtras(bundleMesAño)
                    ventana.putExtras(bundleProyecTasa)
                    startActivity(ventana)
                }
            }
        }
    }

    private fun porcentajeValido(): Boolean {
        var valido = true
        if (porcentaje_proyeccion.text.toString() == "") {
            porcentaje_proyeccion.setError("Introducir Porcentaje de Proyeccion")
            valido = false
        }else{
            if (porcentaje_proyeccion.text.toString().toDouble() > 100){
                porcentaje_proyeccion.setError("Porcentaje Invalido")
                valido = false
            }
        }
        if (tasa_proyeccion_precio.text.toString() == ""){
            tasa_proyeccion_precio.setError("Introducir Tasa de Proyeccion del Precio")
            valido = false
        }else{
            if (tasa_proyeccion_precio?.text.toString().toDouble() > 100){
                tasa_proyeccion_precio.setError("Tasa Proyeccion Invalido")
                valido = false
            }
        }
        return valido
    }

    private fun validar(posMesIni: Int, posMesFin: Int, añoInicioSelec: String, añoFinalSelec: String): Boolean {
        var valido = true
        if (añoFinalSelec > (añoInicioSelec)) {
            if (añoInicioSelec.toInt() == 2021) {
                if (posMesIni < 3) {
                    Toast.makeText(this, "Mes Inicio Invalido", Toast.LENGTH_SHORT).show()
                    valido = false
                } else {
                    if (añoFinalSelec == añoInicioSelec) {
                        if (posMesFin <= posMesIni) {
                            Toast.makeText(this, "Mes Final Invalido", Toast.LENGTH_SHORT).show()
                            valido = false
                        }
                    }
                }
            }
            if (añoFinalSelec.toInt() == 2022) {
                if (posMesFin < 2) {
                    Toast.makeText(this, "Mes Final Invalido", Toast.LENGTH_SHORT).show()
                    valido = false
                }
            }
        } else {
            Toast.makeText(this, "Año Final Invalido", Toast.LENGTH_SHORT).show()
            valido = false
            }
        return valido
    }

    private fun obtenerProbabilidad (valor : Double) : Double{
        var probabilidad : Double = 0.0
        if (valor >= 1.0){
            probabilidad = valor / 100
        }else{
            probabilidad = valor
        }
        return probabilidad
    }
}

