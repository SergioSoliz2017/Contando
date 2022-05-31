package com.appjuego.contando

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main6.*
import kotlinx.android.synthetic.main.activity_main9.*
import java.math.RoundingMode
import java.text.DecimalFormat

class MainActivity9 : AppCompatActivity() {

    var listaComprasMensuales : ArrayList<String> = ArrayList()
    var listaGastoMensuales : ArrayList<String> = ArrayList()
    var listaAlquileresMensuales : ArrayList<String> = ArrayList()
    var proyeccion = ""
    var encabezadoCompras : ArrayList<String> = ArrayList()
    var encabezadoGastos : ArrayList<String> = ArrayList()
    var encabezadoAlquiler : ArrayList<String> = ArrayList()
    var encabezadoTotal : ArrayList<String> = ArrayList()
    var listaTotal : ArrayList<String> = ArrayList()
    var listaIngresoBruto : ArrayList<String> = ArrayList()
    var mesInicial = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main9)
        val bundleCompras = intent.extras
        val bundleGasto = intent.extras
        val bundleAlquiler = intent.extras
        val bundleProyeccion = intent.extras
        val bundleVentas = intent.extras
        val bundleMesIni = intent.extras
        listaIngresoBruto = bundleVentas?.getStringArrayList ("keyValoresListaIngresoBruto")!!
        mesInicial = bundleMesIni?.getInt("keyMesInicial")!!
        listaComprasMensuales = bundleCompras?.getStringArrayList("keyValoresCompras")!!
        listaGastoMensuales = bundleGasto?.getStringArrayList("keyValoresGastos")!!
        listaAlquileresMensuales = bundleAlquiler?.getStringArrayList("keyValoresAlquiler")!!
        proyeccion = bundleProyeccion?.getString("keyValorProyeccion")!!
        crearEncabezadoCompras ()
        crearValoresCompra()
        val tableDinamicCompras : TableDinamicaSalario = TableDinamicaSalario(tabla_compra, applicationContext)
        tableDinamicCompras.addHeader(encabezadoCompras)
        tableDinamicCompras.addData(listaComprasMensuales)

        crearEncabezadoGastos ()
        crearValoresGasto()
        val tableDinamicGasto : TableDinamicaSalario = TableDinamicaSalario(table_gastos, applicationContext)
        tableDinamicGasto.addHeader(encabezadoGastos)
        tableDinamicGasto.addData(listaGastoMensuales)
        crearEncabezadoAlquiler()
        crearValoresAlquiler()
        val tableDinamicAlquiler : TableDinamicaSalario = TableDinamicaSalario(tabla_alquiler, applicationContext)
        tableDinamicAlquiler.addHeader(encabezadoAlquiler)
        tableDinamicAlquiler.addData(listaAlquileresMensuales)

        crearEncabezadoTotal()
        crearValoresTotal()
        val tableDinamicTotal : TableDinamicaSalario = TableDinamicaSalario(tabla_total, applicationContext)
        tableDinamicTotal.addHeader(encabezadoTotal)
        tableDinamicTotal.addData(listaTotal)
        siguiente()
    }

    private fun siguiente() {
        boton_iva.setOnClickListener {
            val bundleProyeccion = Bundle()
            val bundleListaIngresoBruto = Bundle()
            val bundleMesIni = Bundle()
            val bundleCompraMensual = Bundle()
            val bundleTotal = Bundle()
            bundleTotal.putStringArrayList("keyValoresTotalGastos",listaTotal)
            bundleCompraMensual.putStringArrayList("keyValoresCompraMensual",listaComprasMensuales)
            bundleListaIngresoBruto.putStringArrayList("keyValoresListaIngresoBruto",listaIngresoBruto)
            bundleMesIni.putInt("keyMesInicial",mesInicial)
            bundleProyeccion.putString("keyValorProyeccion",proyeccion)
            val ventana = Intent(this, MainActivity7:: class.java)
            ventana.putExtras(bundleTotal)
            ventana.putExtras(bundleCompraMensual)
            ventana.putExtras(bundleProyeccion)
            ventana.putExtras(bundleListaIngresoBruto)
            ventana.putExtras(bundleMesIni)
            startActivity(ventana)
        }
    }

    private fun crearValoresTotal() {
        var cont = 0;
        while (cont <= 59){
            var totalMes = listaAlquileresMensuales.get(cont).toDouble() + listaGastoMensuales.get(cont).toDouble()
            listaTotal.add(redondear(totalMes).toString())
            cont++
        }
    }

    private fun crearValoresAlquiler() {
        var cont = 3;
        while (cont <= 59){
            var alquilerMes = listaAlquileresMensuales.get(cont-3).toDouble() + ( listaAlquileresMensuales.get(cont-3).toDouble() * proyeccion.toDouble())
            listaAlquileresMensuales.add(redondear(alquilerMes).toString())
            cont++
        }
    }

    private fun crearValoresGasto() {
        var cont = 3;
        while (cont <= 59){
            var gastoMes = listaGastoMensuales.get(cont-3).toDouble() + ( listaGastoMensuales.get(cont-3).toDouble() * proyeccion.toDouble())
            listaGastoMensuales.add(redondear(gastoMes).toString())
            cont++
        }
    }

    private fun crearValoresCompra() {
        var cont = 3;
        while (cont <= 59){
            var compraMes = listaComprasMensuales.get(cont-3).toDouble() + ( listaComprasMensuales.get(cont-3).toDouble() * proyeccion.toDouble())
            listaComprasMensuales.add(redondear(compraMes).toString())
            cont++
        }
    }

    private fun crearEncabezadoTotal() {
        var añoInicio = 2021
        while (añoInicio < 2026) {
            encabezadoTotal.add("  ${" ABRIL  "} $añoInicio  ")
            encabezadoTotal.add("  ${" MAYO  "} $añoInicio  ")
            encabezadoTotal.add("  ${" JUNIO  "} $añoInicio  ")
            encabezadoTotal.add("  ${" JULIO  "} $añoInicio  ")
            encabezadoTotal.add("  ${" AGOSTO  "} $añoInicio  ")
            encabezadoTotal.add("  ${" SEPTIEMBRE  "} $añoInicio  ")
            encabezadoTotal.add("  ${" OCTUBRE  "} $añoInicio  ")
            encabezadoTotal.add("  ${" NOVIEMBRE  "} $añoInicio  ")
            encabezadoTotal.add("  ${" DICIEMBRE  "} $añoInicio  ")
            añoInicio++
            encabezadoTotal.add("  ${" ENERO  "} $añoInicio  ")
            encabezadoTotal.add("  ${" FEBRERO  "} $añoInicio  ")
            encabezadoTotal.add("  ${" MARZO  "} $añoInicio  ")
        }
    }

    private fun crearEncabezadoAlquiler() {
        var añoInicio = 2021
        while (añoInicio < 2026) {
            encabezadoAlquiler.add("  ${" ABRIL  "} $añoInicio  ")
            encabezadoAlquiler.add("  ${" MAYO  "} $añoInicio  ")
            encabezadoAlquiler.add("  ${" JUNIO  "} $añoInicio  ")
            encabezadoAlquiler.add("  ${" JULIO  "} $añoInicio  ")
            encabezadoAlquiler.add("  ${" AGOSTO  "} $añoInicio  ")
            encabezadoAlquiler.add("  ${" SEPTIEMBRE  "} $añoInicio  ")
            encabezadoAlquiler.add("  ${" OCTUBRE  "} $añoInicio  ")
            encabezadoAlquiler.add("  ${" NOVIEMBRE  "} $añoInicio  ")
            encabezadoAlquiler.add("  ${" DICIEMBRE  "} $añoInicio  ")
            añoInicio++
            encabezadoAlquiler.add("  ${" ENERO  "} $añoInicio  ")
            encabezadoAlquiler.add("  ${" FEBRERO  "} $añoInicio  ")
            encabezadoAlquiler.add("  ${" MARZO  "} $añoInicio  ")
        }
    }

    private fun crearEncabezadoGastos() {
        var añoInicio = 2021
        while (añoInicio < 2026) {
            encabezadoGastos.add("  ${" ABRIL  "} $añoInicio  ")
            encabezadoGastos.add("  ${" MAYO  "} $añoInicio  ")
            encabezadoGastos.add("  ${" JUNIO  "} $añoInicio  ")
            encabezadoGastos.add("  ${" JULIO  "} $añoInicio  ")
            encabezadoGastos.add("  ${" AGOSTO  "} $añoInicio  ")
            encabezadoGastos.add("  ${" SEPTIEMBRE  "} $añoInicio  ")
            encabezadoGastos.add("  ${" OCTUBRE  "} $añoInicio  ")
            encabezadoGastos.add("  ${" NOVIEMBRE  "} $añoInicio  ")
            encabezadoGastos.add("  ${" DICIEMBRE  "} $añoInicio  ")
            añoInicio++
            encabezadoGastos.add("  ${" ENERO  "} $añoInicio  ")
            encabezadoGastos.add("  ${" FEBRERO  "} $añoInicio  ")
            encabezadoGastos.add("  ${" MARZO  "} $añoInicio  ")
        }
    }

    private fun crearEncabezadoCompras() {
        var añoInicio = 2021
        while (añoInicio < 2026){
            encabezadoCompras.add("  ${" ABRIL  "} $añoInicio  ")
            encabezadoCompras.add("  ${" MAYO  "} $añoInicio  ")
            encabezadoCompras.add("  ${" JUNIO  "} $añoInicio  ")
            encabezadoCompras.add("  ${" JULIO  "} $añoInicio  ")
            encabezadoCompras.add("  ${" AGOSTO  "} $añoInicio  ")
            encabezadoCompras.add("  ${" SEPTIEMBRE  "} $añoInicio  ")
            encabezadoCompras.add("  ${" OCTUBRE  "} $añoInicio  ")
            encabezadoCompras.add("  ${" NOVIEMBRE  "} $añoInicio  ")
            encabezadoCompras.add("  ${" DICIEMBRE  "} $añoInicio  ")
            añoInicio ++
            encabezadoCompras.add("  ${" ENERO  "} $añoInicio  ")
            encabezadoCompras.add("  ${" FEBRERO  "} $añoInicio  ")
            encabezadoCompras.add("  ${" MARZO  "} $añoInicio  ")

        }
    }

    private fun redondear(numero: Double ): String {
        val num = numero
        val df = DecimalFormat("#.##")
        return df.format(num)
    }

}