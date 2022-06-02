package com.appjuego.contando

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main10.*
import kotlinx.android.synthetic.main.activity_main11.*

class MainActivity11 : AppCompatActivity() {

    var ventas : ArrayList<String> = ArrayList()
    var compras : ArrayList<String> = ArrayList()
    var utilidades : ArrayList <String> = ArrayList()
    var listaGeneral : ArrayList <ArrayList<String>> = ArrayList()
    var generado : Boolean = false
    var valoresIt : ArrayList<ArrayList<String>> = ArrayList()
    var listaRec : ArrayList <String> = ArrayList()
    var listaAporteSalario : ArrayList<ArrayList<String>> = ArrayList()
    var comprasActivo : ArrayList <String> = ArrayList()
    var gastosOperativos : ArrayList <String> = ArrayList()
    var iva : ArrayList <String> = ArrayList()
    var iue : ArrayList <String> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main11)
        val bundleVentas = intent.extras
        val bundleCompra = intent.extras
        val bundleIt = intent.extras
        val bundleRec = intent.extras
        val bundleAportesSalario = intent.extras
        val bundleCompraActivo = intent.extras
        val bundleGastoOperativo = intent.extras
        val bundleIva = intent.extras
        iva = bundleIva?.getStringArrayList("keyIva")!!
        gastosOperativos = bundleGastoOperativo?.getStringArrayList("keyGastosOperativos")!!
        comprasActivo = bundleCompraActivo?.getStringArrayList("keyComprasActivo")!!
        listaAporteSalario = bundleAportesSalario?.getSerializable("keyAporteSalario") as ArrayList<ArrayList<String>>
        listaRec = bundleRec?.getStringArrayList("keyRecuperacion")!!
        valoresIt = (bundleIt?.getSerializable("keyIt") as ArrayList<ArrayList<String>>?)!!
        ventas  = bundleVentas?.getStringArrayList("keyVenta")!!
        compras = bundleCompra?.getStringArrayList("keyCompra")!!
        obtenerUtilidades()
        mostrarTabla()
        siguiente()
    }

    private fun mostrarTabla() {
        boton_generar_tabla.setOnClickListener {
            if (valido()){
                generado = true
                encabezado()
                utilidades()
                gastosDeduciles()
                ingresosNoImponibles()
                utilidadImpositiva()
                iuePagar()
                utilidadDespues()
                var tablaIUE : TableDinamicaIUE = TableDinamicaIUE (tabla_iue,applicationContext)
                tablaIUE.addData(listaGeneral)
            }else{

            }
        }
    }

    private fun valido(): Boolean {
        var valido = true
        if (gastos_deducibles.text.toString().isEmpty()){
            gastos_deducibles.setError("Introducir Gasto Deducible")
            valido = false
        }
        if (ingresos_no_imponibles.text.toString().isEmpty()){
            ingresos_no_imponibles.setError("Introducir Ingreso No Imponible")
            valido = false
        }
        return valido
    }

    private fun siguiente() {
        boton_it.setOnClickListener {
            if (generado){
                val bundleIt = Bundle()
                val bundleRec = Bundle()
                val bundleAportesSalarios = Bundle()
                val bundleComprasActivos = Bundle()
                val bundleGastosOperativos = Bundle()
                val bundleIva = Bundle()
                val bundleIue = Bundle()
                bundleIue.putStringArrayList("keuIue",iue)
                bundleIva.putStringArrayList("keyIva", iva)
                bundleGastosOperativos.putStringArrayList("keyGastosOperativos", gastosOperativos)
                bundleComprasActivos.putStringArrayList("keyComprasActivo",comprasActivo)
                bundleAportesSalarios.putSerializable("keyAporteSalario",listaAporteSalario)
                bundleRec.putStringArrayList("keyRecuperacion",listaRec)
                bundleIt.putSerializable("keyIt",valoresIt)
                val ventana = Intent(this, MainActivity12:: class.java)
                ventana.putExtras(bundleIue)
                ventana.putExtras(bundleIva)
                ventana.putExtras(bundleGastosOperativos)
                ventana.putExtras(bundleComprasActivos)
                ventana.putExtras(bundleAportesSalarios)
                ventana.putExtras(bundleRec)
                ventana.putExtras(bundleIt)
                startActivity(ventana)
            }else{
                Toast.makeText(this, "GENERAR TABLA IUE", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun utilidadDespues (){
        var utilidadDes : ArrayList<String> = ArrayList()
        utilidadDes.add("   UTILIDAD DESPUES DE IMPUESTOS  ")
        var cont = 1
        while (cont < 7){
            var valor : Double = listaGeneral.get(4).get(cont).toDouble() * 0.75
            utilidadDes.add(redondear(valor))
            cont++
        }
        listaGeneral.add(utilidadDes)
    }

    private fun iuePagar (){
        var iuePagar : ArrayList<String> = ArrayList()
        iuePagar.add("   IUE POR PAGAR (25%)  ")
        var cont = 1
        while (cont < 7){
            var valor : Double = listaGeneral.get(4).get(cont).toDouble() * 0.25
            iuePagar.add(redondear(valor))
            cont++
        }
        iue = iuePagar
        listaGeneral.add(iuePagar)
    }

    private fun utilidadImpositiva (){
        var utilidadImp : ArrayList<String> = ArrayList()
        utilidadImp.add("   UTILIDAD IMPOSITIVA  ")
        var cont = 1
        while (cont < 7){
            var valor: Double = listaGeneral.get(1).get(cont).toDouble() + listaGeneral.get(2).get(cont).toDouble() - listaGeneral.get(3).get(cont).toDouble()
            utilidadImp.add(redondear(valor))
            cont++
        }
        listaGeneral.add(utilidadImp)
    }

    private fun ingresosNoImponibles (){
        var ingresosNo : ArrayList<String> = ArrayList()
        ingresosNo.add("   INGRESOS NO IMPONIBLES  ")
        ingresosNo.add("0")
        var cont = 0
        while (cont < 5){
            ingresosNo.add(ingresos_no_imponibles.text.toString())
            cont++
        }
        listaGeneral.add(ingresosNo)
    }

    private fun gastosDeduciles (){
        var gastosDeducibles : ArrayList<String> = ArrayList()
        gastosDeducibles.add("   GASTOS DEDUCIBLES  ")
        gastosDeducibles.add("0")
        var cont = 0
        while (cont < 5){
            gastosDeducibles.add(gastos_deducibles.text.toString())
            cont++
        }
        listaGeneral.add(gastosDeducibles)
    }

    private fun utilidades(){
        var utilidadesAntes : ArrayList<String> = ArrayList()
        utilidadesAntes.add("   UTILIDAD ANTES DE IMPUESTO  ")
        utilidadesAntes.add("40000")
        utilidadesAntes.add(utilidades.get(0))
        utilidadesAntes.add(utilidades.get(1))
        utilidadesAntes.add(utilidades.get(2))
        utilidadesAntes.add(utilidades.get(3))
        utilidadesAntes.add(utilidades.get(4))
        listaGeneral.add(utilidadesAntes)
    }

    private fun encabezado (){
        var encabezado : ArrayList<String> = ArrayList()
        encabezado.add("   AÑO                   ")
        encabezado.add("2020                  ")
        encabezado.add("2021                  ")
        encabezado.add("2022                  ")
        encabezado.add("2023                  ")
        encabezado.add("2024                  ")
        encabezado.add("2025                  ")
        listaGeneral.add(encabezado)
    }

    private fun obtenerUtilidades (){
        var cont = 0
        var utilidadVenta = 0.0
        var utilidadCompra = 0.0
        while(cont < 12){
            utilidadVenta = utilidadVenta + ventas.get(cont).toDouble()
            utilidadCompra = utilidadCompra + compras.get(cont).toDouble()
            cont++
        }
        utilidades.add((utilidadVenta - utilidadCompra).toString())
        utilidadVenta = 0.0
        utilidadCompra = 0.0
        while(cont < 24){
            utilidadVenta = utilidadVenta + ventas.get(cont).toDouble()
            utilidadCompra = utilidadCompra + compras.get(cont).toDouble()
            cont++
        }
        utilidades.add((utilidadVenta - utilidadCompra).toString())
        utilidadVenta = 0.0
        utilidadCompra = 0.0
        while(cont < 36){
            utilidadVenta = utilidadVenta + ventas.get(cont).toDouble()
            utilidadCompra = utilidadCompra + compras.get(cont).toDouble()
            cont++
        }
        utilidades.add((utilidadVenta - utilidadCompra).toString())
        utilidadVenta = 0.0
        utilidadCompra = 0.0
        while(cont < 48){
            utilidadVenta = utilidadVenta + ventas.get(cont).toDouble()
            utilidadCompra = utilidadCompra + compras.get(cont).toDouble()
            cont++
        }
        utilidades.add((utilidadVenta - utilidadCompra).toString())
        utilidadVenta = 0.0
        utilidadCompra = 0.0
        while(cont < 60){
            utilidadVenta = utilidadVenta + ventas.get(cont).toDouble()
            utilidadCompra = utilidadCompra + compras.get(cont).toDouble()
            cont++
        }
        utilidades.add((utilidadVenta - utilidadCompra).toString())
    }

    private fun redondear (valor: Double) : String{
        var valorEntero= valor.toInt()
        if ((valor - valorEntero)>= 0.5){
            valorEntero =  valorEntero + 1
        }
        return valorEntero.toString()
    }
}