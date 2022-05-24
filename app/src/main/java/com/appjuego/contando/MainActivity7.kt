package com.appjuego.contando

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main7.*
import kotlinx.android.synthetic.main.activity_main9.*

class MainActivity7 : AppCompatActivity() {
    var proyeccion:String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main7)
        val bundleProyeccion = intent.extras
        proyeccion = bundleProyeccion?.getString("keyValorProyeccion")!!
        Toast.makeText(this, proyeccion, Toast.LENGTH_SHORT).show()
        CargarDatos()
        siguiente()
    }

    private fun siguiente() {
        siguiente_iva.setOnClickListener {
            val bundleProyeccion = Bundle()
            bundleProyeccion.putString("keyValorProyeccion",proyeccion)
            val ventana = Intent(this, MainActivity10:: class.java)
            ventana.putExtras(bundleProyeccion)
            startActivity(ventana)
        }
    }

    private fun esValido () : Boolean{
        var valido = true
        if (intereses_comercial_abril.text.toString().isEmpty()){
            intereses_comercial_abril.setError("Introducir Interes Comercial Abril")
            valido = false
        }
        if (intereses_comercial_mayo.text.toString().isEmpty()){
            intereses_comercial_mayo.setError("Introducir Interes Comercial Mayo")
            valido = false
        }
        if (intereses_comercial_junio.text.toString().isEmpty()){
            intereses_comercial_junio.setError("Introducir Interes Comercial Junio")
            valido = false
        }
        if (intereses_comercial_abril_salida.text.toString().isEmpty()){
            intereses_comercial_abril_salida.setError("Introducir Interes Comercial Abril")
            valido = false
        }
        if (intereses_comercial_mayo_salida.text.toString().isEmpty()){
            intereses_comercial_mayo_salida.setError("Introducir Interes Comercial Mayo")
            valido = false
        }
        if (intereses_comercial_junio_salida.text.toString().isEmpty()){
            intereses_comercial_junio.setError("Introducir Interes Comercial Junio")
            valido = false
        }
        if (venta_activo_fijo_abril.text.toString().isEmpty()){
            venta_activo_fijo_abril.setError("Introducir Venta Activo Fijo Abril")
            valido = false
        }
        if (venta_activo_fijo_mayo.text.toString().isEmpty()){
            venta_activo_fijo_mayo.setError("Introducir Venta Activo Fijo Mayo")
            valido = false
        }
        if (venta_activo_fijo_junio.text.toString().isEmpty()){
            venta_activo_fijo_junio.setError("Introducir Venta Activo Fijo Junio")
            valido = false
        }
        if (alquiler_abril_iva.text.toString().isEmpty()){
            alquiler_abril_iva.setError("Introducir Alquiler Abril")
            valido = false
        }
        if (alquiler_mayo_iva.text.toString().isEmpty()){
            alquiler_mayo_iva.setError("Introducir Alquiler Mayo")
            valido = false
        }
        if (alquiler_junio_iva.text.toString().isEmpty()){
            alquiler_junio_iva.setError("Introducir Alquiler Junio")
            valido = false
        }
        if (compra_activo_fijo_abril.text.toString().isEmpty()){
            alquiler_abril_iva.setError("Introducir Activo Fijo Abril")
            valido = false
        }
        if (compra_activo_fijo_mayo.text.toString().isEmpty()){
            alquiler_mayo_iva.setError("Introducir Activo Fijo Mayo")
            valido = false
        }
        if (compra_activo_fijo_junio.text.toString().isEmpty()){
            alquiler_junio_iva.setError("Introducir Activo Fijo Junio")
            valido = false
        }
        if (subsidio_abril.text.toString().isEmpty()){
            alquiler_abril_iva.setError("Introducir Subsidio Abril")
            valido = false
        }
        if (subsidio_mayo.text.toString().isEmpty()){
            alquiler_mayo_iva.setError("Introducir Subsidio Mayo")
            valido = false
        }
        if (subsidio_junio.text.toString().isEmpty()){
            alquiler_junio_iva.setError("Introducir Subsidio Junio")
            valido = false
        }
        return valido
    }

    private fun CargarDatos (){
        var listaMes = listOf("ENERO","FEBRERO","MARZO","ABRIL","MAYO","JUNIO","JULIO","AGOSTO","SEPTIEMBRE","OCTUBRE","NOVIEMBRE","DICIEMBRE")
        val adaptador = ArrayAdapter(this,android.R.layout.simple_spinner_item,listaMes)
        lista_mes_salida.adapter = adaptador
        lista_mes_entrada.adapter = adaptador
        var listaAño = listOf("2021","2022","2023","2024","2025" ,"2026")
        val adapterAÑo = ArrayAdapter(this,android.R.layout.simple_spinner_item,listaAño)
        lista_año_entrada.adapter = adapterAÑo
        lista_año_salida.adapter = adapterAÑo
    }
}

