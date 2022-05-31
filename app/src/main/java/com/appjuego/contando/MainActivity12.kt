package com.appjuego.contando

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main11.*
import kotlinx.android.synthetic.main.activity_main12.*
import java.util.ArrayList

class MainActivity12 : AppCompatActivity() {

    var valoresIt : ArrayList<ArrayList<String>> = ArrayList()
    var listaIt : ArrayList<ArrayList<String>> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main12)
        val bundleIt = intent.extras
        valoresIt = (bundleIt?.getSerializable("keyIt") as ArrayList<ArrayList<String>>?)!!
        encabezado()
        listaIt.add(valoresIt.get(0))
        listaIt.add(valoresIt.get(1))
        listaIt.add(valoresIt.get(2))
        listaIt.add(valoresIt.get(3))
        totalVentaBienes()
        crearTabla()
        siguiente()
    }

    private fun siguiente() {
        boton_presupuesto_caja.setOnClickListener {
            val ventana = Intent(this, MainActivity13:: class.java)
            startActivity(ventana)

        }
    }

    private fun crearTabla() {
        var tablaIT : TableDinamicaIva = TableDinamicaIva(tabla_it,applicationContext , 61)
        tablaIT.addData(listaIt)
    }

    private fun encabezado() {
        var encabezado : ArrayList<String> = ArrayList()
        encabezado.add("IT              ")
        var añoInicio = 2021
        while (añoInicio < 2026) {
            encabezado.add("  ${" ABRIL  "} $añoInicio  ")
            encabezado.add("  ${" MAYO  "} $añoInicio  ")
            encabezado.add("  ${" JUNIO  "} $añoInicio  ")
            encabezado.add("  ${" JULIO  "} $añoInicio  ")
            encabezado.add("  ${" AGOSTO  "} $añoInicio  ")
            encabezado.add("  ${" SEPTIEMBRE  "} $añoInicio  ")
            encabezado.add("  ${" OCTUBRE  "} $añoInicio  ")
            encabezado.add("  ${" NOVIEMBRE  "} $añoInicio  ")
            encabezado.add("  ${" DICIEMBRE  "} $añoInicio  ")
            añoInicio++
            encabezado.add("  ${" ENERO  "} $añoInicio  ")
            encabezado.add("  ${" FEBRERO  "} $añoInicio  ")
            encabezado.add("  ${" MARZO  "} $añoInicio  ")
        }
        listaIt.add(encabezado)
    }

    private fun totalVentaBienes() {
        var total : ArrayList <String> = ArrayList()
        var iT : ArrayList <String> = ArrayList()
        total.add("TOTAL VENTA DE BIENES Y SERVICIOS FACTURADOS ")
        iT.add("IT DEL PERIODO")
        var cont = 1
        while (cont < 61){
            var valor : Double = listaIt.get(1).get(cont).toDouble()  + listaIt.get(2).get(cont).toDouble() + listaIt.get(3).get(cont).toDouble() + listaIt.get(4).get(cont).toDouble()
            cont ++
            total.add(redondear(valor))
            iT.add(redondear(valor * 0.03))
        }
        listaIt.add(total)
        listaIt.add(iT)
    }

    private fun redondear (valor: Double) : String{
        var valorEntero= valor.toInt()
        if ((valor - valorEntero)>= 0.5){
            valorEntero =  valorEntero + 1
        }
        return valorEntero.toString()
    }
}