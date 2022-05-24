package com.appjuego.contando

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main5.*
import kotlinx.android.synthetic.main.activity_main8.*
import java.text.DecimalFormat

class MainActivity8 : AppCompatActivity() {

    var listaComprasMensuales : ArrayList<String> = ArrayList()
    var listaGastoMensuales : ArrayList<String> = ArrayList()
    var listaAlquileresMensuales : ArrayList<String> = ArrayList()
    var proyeccion : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main8)
        val bundleProyeccion = intent.extras
        proyeccion = bundleProyeccion!!.getString("keyValorProyeccion")!!
        siguiente ()
    }

    private fun siguiente() {
        siguiente_flujo_salida.setOnClickListener {
            if (esValido()){
                val ventana = Intent(this, MainActivity9:: class.java)
                listaComprasMensuales.add(redondear(compra_abril.text.toString().toDouble()).toString())
                listaComprasMensuales.add(redondear(compra_mayo.text.toString().toDouble()).toString())
                listaComprasMensuales.add(redondear(compra_junio.text.toString().toDouble()).toString())
                listaGastoMensuales.add(redondear(gasto_general_abril.text.toString().toDouble()).toString())
                listaGastoMensuales.add(redondear(gasto_general_mayo.text.toString().toDouble()).toString())
                listaGastoMensuales.add(redondear(gasto_general_junio.text.toString().toDouble()).toString())
                listaAlquileresMensuales.add(redondear(alquiler_abril.text.toString().toDouble()).toString())
                listaAlquileresMensuales.add(redondear(alquiler_mayo.text.toString().toDouble()).toString())
                listaAlquileresMensuales.add(redondear(alquiler_junio.text.toString().toDouble()).toString())
                val bundleCompras = Bundle()
                val bundleGasto = Bundle()
                val bundleAlquiler = Bundle()
                val bundleProyeccion = Bundle()
                bundleProyeccion.putString("keyValorProyeccion",proyeccion)
                bundleCompras.putStringArrayList("keyValoresCompras",listaComprasMensuales)
                bundleGasto.putStringArrayList("keyValoresGastos",listaGastoMensuales)
                bundleAlquiler.putStringArrayList("keyValoresAlquiler",listaAlquileresMensuales)
                bundleProyeccion.putString("keyValorProyeccion",proyeccion.toString())
                ventana.putExtras(bundleCompras)
                ventana.putExtras(bundleGasto)
                ventana.putExtras(bundleAlquiler)
                ventana.putExtras(bundleProyeccion)
                startActivity(ventana)
            }
        }
    }

    private fun esValido() : Boolean {
        var valido = true
        if (gasto_general_abril.text.toString().isEmpty()){
            gasto_general_abril.setError("Introducir Gasto General De Abril")
            valido = false
        }else{
            /*if (aporte_patronales.text.toString().toDouble() > 100){
                aporte_patronales.setError("Porcentaje Invalido")
                valido = false
            }*/
        }
        if (gasto_general_mayo.text.toString().isEmpty()){
            gasto_general_mayo.setError("Introducir Gasto General De Mayo")
            valido = false
        }else{
            /*if (aporte_patronales.text.toString().toDouble() > 100){
                aporte_patronales.setError("Porcentaje Invalido")
                valido = false
            }*/
        }
        if (gasto_general_junio.text.toString().isEmpty()){
            gasto_general_junio.setError("Introducir Gasto General De Junio")
            valido = false
        }else{
            /*if (aporte_patronales.text.toString().toDouble() > 100){
                aporte_patronales.setError("Porcentaje Invalido")
                valido = false
            }*/
        }
        if (compra_abril.text.toString().isEmpty()){
            compra_abril.setError("Introducir Compra General De Abril")
            valido = false
        }else{
            /*if (aporte_patronales.text.toString().toDouble() > 100){
                aporte_patronales.setError("Porcentaje Invalido")
                valido = false
            }*/
        }
        if (compra_mayo.text.toString().isEmpty()){
            compra_mayo.setError("Introducir Compra General De Mayo")
            valido = false
        }else{
            /*if (aporte_patronales.text.toString().toDouble() > 100){
                aporte_patronales.setError("Porcentaje Invalido")
                valido = false
            }*/
        }
        if (compra_junio.text.toString().isEmpty()){
            compra_junio.setError("Introducir Compra General De Junio")
            valido = false
        }else{
            /*if (aporte_patronales.text.toString().toDouble() > 100){
                aporte_patronales.setError("Porcentaje Invalido")
                valido = false
            }*/
        }
        if (alquiler_abril.text.toString().isEmpty()){
            compra_junio.setError("Introducir Alquiler De Abril")
            valido = false
        }else{
            /*if (aporte_patronales.text.toString().toDouble() > 100){
                aporte_patronales.setError("Porcentaje Invalido")
                valido = false
            }*/
        }
        if (alquiler_mayo.text.toString().isEmpty()){
            compra_junio.setError("Introducir Alquiler De Mayo")
            valido = false
        }else{
            /*if (aporte_patronales.text.toString().toDouble() > 100){
                aporte_patronales.setError("Porcentaje Invalido")
                valido = false
            }*/
        }
        if (alquiler_junio.text.toString().isEmpty()){
            compra_junio.setError("Introducir Compra General De Junio")
            valido = false
        }else{
            /*if (aporte_patronales.text.toString().toDouble() > 100){
                aporte_patronales.setError("Porcentaje Invalido")
                valido = false
            }*/
        }
        if (tasa_proyeccion_salida.text.toString().isEmpty()){
            tasa_proyeccion_salida.setError("Introducir Tasa Proyeccion")
            valido = false
        }else{
            if (tasa_proyeccion_salida.text.toString().toDouble() > 100){
                tasa_proyeccion_salida.setError("Tasa De Proyeccion Invalida")
                valido = false
            }
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

    private fun redondear(numero: Double ): String {
        val num = numero
        val df = DecimalFormat("#.##")
        return df.format(num)
    }

}