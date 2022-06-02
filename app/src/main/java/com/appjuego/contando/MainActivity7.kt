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
    var datosNecesarios : ArrayList<String> = ArrayList()
    var listaIngresoBruto : ArrayList<String> = ArrayList()
    var mesInicial = 0
    var listaTotal : ArrayList<String> = ArrayList()
    var listaComprasMensuales : ArrayList<String> = ArrayList()
    var listaRec : ArrayList <String> = ArrayList()
    var listaAporteSalario : ArrayList<ArrayList<String>> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main7)
        val bundleProyeccion = intent.extras
        val bundleVentas = intent.extras
        val bundleMesIni = intent.extras
        val bundleTotal = intent.extras
        val bundleCompraMensual = intent.extras
        val bundleRec = intent.extras
        val bundleAportesSalario = intent.extras
        listaAporteSalario = bundleAportesSalario?.getSerializable("keyAporteSalario") as ArrayList<ArrayList<String>>
        listaRec = bundleRec?.getStringArrayList("keyRecuperacion")!!
        listaTotal = bundleTotal?.getStringArrayList ("keyValoresTotalGastos")!!
        listaComprasMensuales = bundleCompraMensual?.getStringArrayList("keyValoresCompraMensual")!!
        listaIngresoBruto = bundleVentas?.getStringArrayList ("keyValoresListaIngresoBruto")!!
        mesInicial = bundleMesIni?.getInt("keyMesInicial")!!
        proyeccion = bundleProyeccion?.getString("keyValorProyeccion")!!
        siguiente()
    }

    private fun obtenerDatos() {
        datosNecesarios.add(intereses_comercial_abril.text.toString())
        datosNecesarios.add(intereses_comercial_mayo.text.toString())
        datosNecesarios.add(intereses_comercial_junio.text.toString())
        datosNecesarios.add(venta_activo_fijo_abril.text.toString())
        datosNecesarios.add(venta_activo_fijo_mayo.text.toString())
        datosNecesarios.add(venta_activo_fijo_junio.text.toString())
        datosNecesarios.add(alquiler_abril_iva.text.toString())
        datosNecesarios.add(alquiler_mayo_iva.text.toString())
        datosNecesarios.add(alquiler_junio_iva.text.toString())
        datosNecesarios.add(intereses_comercial_abril_salida.text.toString())
        datosNecesarios.add(intereses_comercial_mayo_salida.text.toString())
        datosNecesarios.add(intereses_comercial_junio_salida.text.toString())
        datosNecesarios.add(compra_activo_fijo_abril.text.toString())
        datosNecesarios.add(compra_activo_fijo_mayo.text.toString())
        datosNecesarios.add(compra_activo_fijo_junio.text.toString())
        datosNecesarios.add(subsidio_abril.text.toString())
        datosNecesarios.add(subsidio_mayo.text.toString())
        datosNecesarios.add(subsidio_junio.text.toString())
    }

    private fun siguiente() {
        siguiente_iva.setOnClickListener {
            if (esValido()){
                obtenerDatos()
                val bundleProyeccion = Bundle()
                val bundleDatosNecesarios = Bundle()
                val bundleListaIngresoBruto = Bundle()
                val bundleMesIni = Bundle()
                val bundleCompraMensual = Bundle()
                val bundleTotal = Bundle()
                val bundleRec = Bundle()
                val bundleAportesSalarios = Bundle()
                bundleAportesSalarios.putSerializable("keyAporteSalario",listaAporteSalario)
                bundleRec.putStringArrayList("keyRecuperacion",listaRec)
                bundleTotal.putStringArrayList("keyValoresTotalGastos",listaTotal)
                bundleCompraMensual.putStringArrayList("keyValoresCompraMensual",listaComprasMensuales)
                bundleListaIngresoBruto.putStringArrayList("keyValoresListaIngresoBruto",listaIngresoBruto)
                bundleMesIni.putInt("keyMesInicial",mesInicial)
                bundleDatosNecesarios.putStringArrayList("keyValorDatosNecesarios",datosNecesarios)
                bundleProyeccion.putString("keyValorProyeccion",proyeccion)
                val ventana = Intent(this, MainActivity10:: class.java)
                ventana.putExtras(bundleAportesSalarios)
                ventana.putExtras(bundleRec)
                ventana.putExtras(bundleTotal)
                ventana.putExtras(bundleCompraMensual)
                ventana.putExtras(bundleDatosNecesarios)
                ventana.putExtras(bundleProyeccion)
                ventana.putExtras(bundleListaIngresoBruto)
                ventana.putExtras(bundleMesIni)
                startActivity(ventana)
            }

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
            intereses_comercial_junio_salida.setError("Introducir Interes Comercial Junio")
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
            compra_activo_fijo_abril.setError("Introducir Activo Fijo Abril")
            valido = false
        }
        if (compra_activo_fijo_mayo.text.toString().isEmpty()){
            compra_activo_fijo_mayo.setError("Introducir Activo Fijo Mayo")
            valido = false
        }
        if (compra_activo_fijo_junio.text.toString().isEmpty()){
            compra_activo_fijo_junio.setError("Introducir Activo Fijo Junio")
            valido = false
        }
        if (subsidio_abril.text.toString().isEmpty()){
            subsidio_abril.setError("Introducir Subsidio Abril")
            valido = false
        }
        if (subsidio_mayo.text.toString().isEmpty()){
            subsidio_mayo.setError("Introducir Subsidio Mayo")
            valido = false
        }
        if (subsidio_junio.text.toString().isEmpty()){
            subsidio_junio.setError("Introducir Subsidio Junio")
            valido = false
        }
        return valido
    }
}

