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
        CargarMeses ()
        CargarAños ()
        siguiente ()

    }

    private fun siguiente () {
        var mesInicioSelec: String = "error"
        var mesFinalSelec: String = "error"
        var añoInicioSelec: String = "error"
        var añoFinalSelec: String = "error"
        var posMesIni: Int = 0
        var posMesFin: Int = 0
        listaMesInicio.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                mesInicioSelec = listaMes[position]
                posMesIni = position
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
        listaMesFinal.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                mesFinalSelec = listaMes[position]
                posMesFin = position
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
        listaAñoInicio.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                añoInicioSelec = listaAño[position]
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
        listaAñoFinal.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                añoFinalSelec = listaAño[position]
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
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
        if (añoFinalSelec>=añoInicioSelec){
            if (añoInicioSelec.toInt() == 2021){
                if (posMesIni < 3){
                    Toast.makeText(this, "Mes Inicio Invalido", Toast.LENGTH_SHORT).show()
                    valido = false
                }else{
                    if (añoFinalSelec == añoInicioSelec){
                        if (posMesFin<=posMesIni){
                          Toast.makeText(this, "Mes Final Invalido", Toast.LENGTH_SHORT).show()
                          valido = false
                        }
                    }
                }
            }else{
                if (añoFinalSelec == añoInicioSelec){
                    if (posMesFin<=posMesIni){
                        Toast.makeText(this, "Mes Final Invalido", Toast.LENGTH_SHORT).show()
                        valido = false
                    }
                }
            }
        }else{
            valido = false
            Toast.makeText(this, "Año Final Invalido", Toast.LENGTH_SHORT).show()
        }
        return valido
    }

    private fun CargarMeses (){
        listaMes = listOf("ENERO","FEBRERO","MARZO","ABRIL","MAYO","JUNIO","JULIO","AGOSTO","SEPTIEMBRE","OCTUBRE","NOVIEMBRE","DICIEMBRE")
        val adaptador = ArrayAdapter(this,android.R.layout.simple_spinner_item,listaMes)
        listaMesInicio.adapter = adaptador
        listaMesFinal.adapter = adaptador
    }

    private fun CargarAños (){
        listaAño = listOf("2021","2022","2023","2024","2025","2026","2027","2028","2029","2030","2031","2032")
        val adaptador = ArrayAdapter(this,android.R.layout.simple_spinner_item,listaAño)
        listaAñoInicio.adapter = adaptador
        listaAñoFinal.adapter = adaptador
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

