package com.appjuego.contando

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main4.*
import kotlinx.android.synthetic.main.activity_main5.*

class MainActivity5 : AppCompatActivity() {

    var arregloMesAño: Array<String>? = null
    var proyeccion : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main5)
        val bundleMesAño = intent.extras
        arregloMesAño = bundleMesAño?.getStringArray("keyValoresMesAño")!!
        val bundleProyeccion = intent.extras
        proyeccion = bundleProyeccion!!.getString("keyValorProyeccion")!!
        siguiente()
    }

    private fun siguiente(){
        siguiente_salarios.setOnClickListener {
            if (esValido()){
                val bundleMesAño = Bundle()
                val bundleSalarios = Bundle()
                val bundleProyeccion = Bundle()
                val arregloSalarios = arrayOf(aporte_patronales.text.toString(),incremento_salarial.text.toString(),nro_trabajadores.text.toString(),sueldo_individual.text.toString())
                bundleSalarios.putStringArray("keyValoresSalarios",arregloSalarios)
                bundleMesAño.putStringArray("keyValoresMesAño", arregloMesAño)
                bundleProyeccion.putString("keyValorProyeccion",proyeccion)
                val ventana = Intent(this, MainActivity6:: class.java)
                ventana.putExtras(bundleProyeccion)
                ventana.putExtras(bundleMesAño)
                ventana.putExtras(bundleSalarios)
                startActivity(ventana)
            }
        }

    }

    private fun esValido() : Boolean {
        var valido = true
        if (aporte_patronales.text.toString().isEmpty()){
            aporte_patronales.setError("Introducir Aportes Patronales")
            valido = false
        }else{
            if (aporte_patronales.text.toString().toDouble() > 100){
                aporte_patronales.setError("Porcentaje Invalido")
                valido = false
            }
        }
        if (incremento_salarial.text.toString().isEmpty()){
            incremento_salarial.setError("Introducir Incremento Salarial")
            valido = false
        }else{
            if (incremento_salarial.text.toString().toDouble() > 100){
                incremento_salarial.setError("Porcentaje Invalido")
                valido = false
            }
        }
        if (nro_trabajadores.text.toString().isEmpty()){
            nro_trabajadores.setError("Introducir Nro Trabajadores")
            valido = false
        }
        if (sueldo_individual.text.toString().isEmpty()){
            sueldo_individual.setError("Introducir Sueldo Individual")
            valido = false
        }
        return valido
    }
}