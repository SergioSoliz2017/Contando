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
    var listaRec : ArrayList <String> = ArrayList()
    var ventas : ArrayList <String> = ArrayList()
    var listaAporteSalario : ArrayList<ArrayList<String>> = ArrayList()
    var comprasActivo : ArrayList <String> = ArrayList()
    var gastosOperativos : ArrayList <String> = ArrayList()
    var iva : ArrayList <String> = ArrayList()
    var listaItMandar : ArrayList <String> = ArrayList()
    var iue : ArrayList <String> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main12)
        val bundleIt = intent.extras
        val bundleRec = intent.extras
        val bundleAportesSalario = intent.extras
        val bundleCompraActivo = intent.extras
        val bundleGastoOperativo = intent.extras
        val bundleIva = intent.extras
        val bundleIue = intent.extras
        iue = bundleIue?.getStringArrayList("keuIue")!!
        iva = bundleIva?.getStringArrayList("keyIva")!!
        gastosOperativos = bundleGastoOperativo?.getStringArrayList("keyGastosOperativos")!!
        comprasActivo = bundleCompraActivo?.getStringArrayList("keyComprasActivo")!!
        listaAporteSalario = bundleAportesSalario?.getSerializable("keyAporteSalario") as ArrayList<ArrayList<String>>
        listaRec = bundleRec?.getStringArrayList("keyRecuperacion")!!
        valoresIt = (bundleIt?.getSerializable("keyIt") as ArrayList<ArrayList<String>>?)!!
        encabezado()
        ventas = valoresIt.get(0)
        listaIt.add(valoresIt.get(0))
        listaIt.add(valoresIt.get(1))
        listaIt.add(valoresIt.get(2))
        listaIt.add(valoresIt.get(3))
        totalVentaBienes()
        saldo()
        crearTabla()
        siguiente()
    }

    private fun saldo() {
        var saldoRecompensar : ArrayList<String> = ArrayList()
        var saldoDefinitivo : ArrayList<String> = ArrayList()
        saldoRecompensar.add("SALDO IUE POR COMPENSAR DEL MES ANTERIOR")
        saldoDefinitivo.add("SALDO DEFINITIVO A FAVOR DEL FISCO (CONTRIBUYENTE) ")
        saldoRecompensar.add("0")
        var valor : Double =  listaIt.get(6).get(1).toDouble() + saldoRecompensar.get(1).toDouble()
        saldoRecompensar.add("10000")
        saldoDefinitivo.add(valor.toInt().toString())
        var cont = 2
        while (cont < 61){
            if(saldoDefinitivo.get(cont -1).toInt() < 0){
                valor = listaIt.get(6).get(cont).toDouble() + saldoRecompensar.get(cont).toDouble()
            }else{
                valor = listaIt.get(6).get(cont).toDouble() - saldoRecompensar.get(cont).toDouble()
            }
            saldoRecompensar.add(valor.toInt().toString())
            saldoDefinitivo.add(valor.toInt().toString())
            cont ++
        }
        listaIt.add( saldoRecompensar)
        listaIt.add(saldoDefinitivo)
        listaItMandar = saldoDefinitivo
    }

    private fun siguiente() {
        boton_presupuesto_caja.setOnClickListener {
            val bundleRec = Bundle()
            val bundleVentas = Bundle()
            val bundleAportesSalarios = Bundle()
            val bundleComprasActivos = Bundle()
            val bundleGastosOperativos = Bundle()
            val bundleIva = Bundle()
            val bundleIt = Bundle()
            val bundleIue = Bundle()
            bundleIue.putStringArrayList("keuIue",iue)
            bundleIt.putStringArrayList("keyIt",listaItMandar)
            bundleIva.putStringArrayList("keyIva", iva)
            bundleGastosOperativos.putStringArrayList("keyGastosOperativos", gastosOperativos)
            bundleComprasActivos.putStringArrayList("keyComprasActivo",comprasActivo)
            bundleAportesSalarios.putSerializable("keyAporteSalario",listaAporteSalario)
            bundleVentas.putStringArrayList("keyVentas" , ventas)
            bundleRec.putStringArrayList("keyRecuperacion",listaRec)
            val ventana = Intent(this, MainActivity13:: class.java)
            ventana.putExtras(bundleIt)
            ventana.putExtras(bundleIue)
            ventana.putExtras(bundleIva)
            ventana.putExtras(bundleGastosOperativos)
            ventana.putExtras(bundleComprasActivos)
            ventana.putExtras(bundleAportesSalarios)
            ventana.putExtras(bundleVentas)
            ventana.putExtras(bundleRec)
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