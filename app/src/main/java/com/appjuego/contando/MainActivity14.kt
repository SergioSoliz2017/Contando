package com.appjuego.contando

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main13.*
import kotlinx.android.synthetic.main.activity_main14.*

class MainActivity14 : AppCompatActivity() {
    var ingreso : String = ""
    var gasto : String = ""
    var listaGeneral : ArrayList<ArrayList<String>> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main14)
        var bundleIngreso = intent.extras
        var bundleGasto = intent.extras
        ingreso = bundleIngreso?.getString("keyIngreso")!!
        gasto = bundleGasto?.getString("keyGasto")!!
        actividadesOperacion()
        actividadesInversion()
        actividadesFinanciamiento()
        saldoFinal()
        crearTabla()
    }

    private fun crearTabla() {
        var tablaFlujo : TableDinamicaIva = TableDinamicaIva(tabla_flujo_caja,applicationContext , 3)
        tablaFlujo.addData(listaGeneral)
    }

    private fun saldoFinal() {
        var saldoFinal : ArrayList<String> = ArrayList()
        var incremento : ArrayList<String> = ArrayList()
        var inicio : ArrayList<String> = ArrayList()
        incremento.add("INCREMENTO PROYECTADA DEL EFECTIVO DEL PERIODO ")
        incremento.add("")
        incremento.add((ingreso.toDouble() - gasto.toDouble()).toInt().toString())
        inicio.add("EFECTIVO AL INICIO DEL PERIODO")
        inicio.add("")
        inicio.add("16000")
        saldoFinal.add("SALDO EFECTIVO FINAL PROYECTADO ")
        saldoFinal.add("")
        saldoFinal.add(((ingreso.toDouble() - gasto.toDouble()) + 16000).toInt().toString())
        listaGeneral.add(incremento)
        listaGeneral.add(inicio)
        listaGeneral.add(saldoFinal)
    }

    private fun actividadesOperacion() {
        var actividadOperacion : ArrayList<String> = ArrayList()
        var Listaingreso : ArrayList<String> = ArrayList()
        var Listagasto : ArrayList<String> = ArrayList()
        actividadOperacion.add("FLUJO DE EFECTIVO PROYECTADO POR ACTIVIDADES DE OPERACION ")
        actividadOperacion.add("")
        actividadOperacion.add((ingreso.toDouble() - gasto.toDouble()).toInt().toString())
        Listaingreso.add("INGRESOS DE OPERACION")
        Listagasto.add("GASTOS DE OPERACION")
        Listaingreso.add(ingreso)
        Listagasto.add(gasto)
        Listaingreso.add("")
        Listagasto.add("")
        listaGeneral.add(actividadOperacion)
        listaGeneral.add(Listaingreso)
        listaGeneral.add(Listagasto)
    }

    private fun actividadesInversion() {
        var actividadOperacion : ArrayList<String> = ArrayList()
        var Listaingreso : ArrayList<String> = ArrayList()
        var Listagasto : ArrayList<String> = ArrayList()
        actividadOperacion.add("FLUJO DE EFECTIVO PROYECTADO POR ACTIVIDADES DE INVERSION ")
        actividadOperacion.add("")
        actividadOperacion.add("0")
        Listaingreso.add("INGRESOS DE CAPITAL")
        Listagasto.add("GASTOS DE CAPITAL")
        Listaingreso.add("0")
        Listagasto.add("0")
        Listaingreso.add("")
        Listagasto.add("")
        listaGeneral.add(actividadOperacion)
        listaGeneral.add(Listaingreso)
        listaGeneral.add(Listagasto)
    }

    private fun actividadesFinanciamiento() {
        var actividadOperacion : ArrayList<String> = ArrayList()
        var Listaingreso : ArrayList<String> = ArrayList()
        var Listagasto : ArrayList<String> = ArrayList()
        actividadOperacion.add("FLUJO DE EFECTIVO PROYECTADO POR ACTIVIDADES DE FINANCIAMIENTO ")
        actividadOperacion.add("")
        actividadOperacion.add("0")
        Listaingreso.add("FUENTES")
        Listagasto.add("USOS")
        Listaingreso.add("0")
        Listagasto.add("0")
        Listaingreso.add("")
        Listagasto.add("")
        listaGeneral.add(actividadOperacion)
        listaGeneral.add(Listaingreso)
        listaGeneral.add(Listagasto)
    }
}