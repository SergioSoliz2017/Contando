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
    var listaRec : ArrayList <String> = ArrayList()
    var listaAporteSalario : ArrayList<ArrayList<String>> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main9)
        val bundleCompras = intent.extras
        val bundleGasto = intent.extras
        val bundleAlquiler = intent.extras
        val bundleProyeccion = intent.extras
        val bundleVentas = intent.extras
        val bundleMesIni = intent.extras
        val bundleRec = intent.extras
        val bundleAportesSalario = intent.extras
        listaAporteSalario = bundleAportesSalario?.getSerializable("keyAporteSalario") as ArrayList<ArrayList<String>>
        listaRec = bundleRec?.getStringArrayList("keyRecuperacion")!!
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
            val bundleRec = Bundle()
            val bundleAportesSalarios = Bundle()
            bundleAportesSalarios.putSerializable("keyAporteSalario",listaAporteSalario)
            bundleRec.putStringArrayList("keyRecuperacion",listaRec)
            bundleTotal.putStringArrayList("keyValoresTotalGastos",listaTotal)
            bundleCompraMensual.putStringArrayList("keyValoresCompraMensual",listaComprasMensuales)
            bundleListaIngresoBruto.putStringArrayList("keyValoresListaIngresoBruto",listaIngresoBruto)
            bundleMesIni.putInt("keyMesInicial",mesInicial)
            bundleProyeccion.putString("keyValorProyeccion",proyeccion)
            val ventana = Intent(this, MainActivity7:: class.java)
            ventana.putExtras(bundleAportesSalarios)
            ventana.putExtras(bundleRec)
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
        var a??oInicio = 2021
        while (a??oInicio < 2026) {
            encabezadoTotal.add("  ${" ABRIL  "} $a??oInicio  ")
            encabezadoTotal.add("  ${" MAYO  "} $a??oInicio  ")
            encabezadoTotal.add("  ${" JUNIO  "} $a??oInicio  ")
            encabezadoTotal.add("  ${" JULIO  "} $a??oInicio  ")
            encabezadoTotal.add("  ${" AGOSTO  "} $a??oInicio  ")
            encabezadoTotal.add("  ${" SEPTIEMBRE  "} $a??oInicio  ")
            encabezadoTotal.add("  ${" OCTUBRE  "} $a??oInicio  ")
            encabezadoTotal.add("  ${" NOVIEMBRE  "} $a??oInicio  ")
            encabezadoTotal.add("  ${" DICIEMBRE  "} $a??oInicio  ")
            a??oInicio++
            encabezadoTotal.add("  ${" ENERO  "} $a??oInicio  ")
            encabezadoTotal.add("  ${" FEBRERO  "} $a??oInicio  ")
            encabezadoTotal.add("  ${" MARZO  "} $a??oInicio  ")
        }
    }

    private fun crearEncabezadoAlquiler() {
        var a??oInicio = 2021
        while (a??oInicio < 2026) {
            encabezadoAlquiler.add("  ${" ABRIL  "} $a??oInicio  ")
            encabezadoAlquiler.add("  ${" MAYO  "} $a??oInicio  ")
            encabezadoAlquiler.add("  ${" JUNIO  "} $a??oInicio  ")
            encabezadoAlquiler.add("  ${" JULIO  "} $a??oInicio  ")
            encabezadoAlquiler.add("  ${" AGOSTO  "} $a??oInicio  ")
            encabezadoAlquiler.add("  ${" SEPTIEMBRE  "} $a??oInicio  ")
            encabezadoAlquiler.add("  ${" OCTUBRE  "} $a??oInicio  ")
            encabezadoAlquiler.add("  ${" NOVIEMBRE  "} $a??oInicio  ")
            encabezadoAlquiler.add("  ${" DICIEMBRE  "} $a??oInicio  ")
            a??oInicio++
            encabezadoAlquiler.add("  ${" ENERO  "} $a??oInicio  ")
            encabezadoAlquiler.add("  ${" FEBRERO  "} $a??oInicio  ")
            encabezadoAlquiler.add("  ${" MARZO  "} $a??oInicio  ")
        }
    }

    private fun crearEncabezadoGastos() {
        var a??oInicio = 2021
        while (a??oInicio < 2026) {
            encabezadoGastos.add("  ${" ABRIL  "} $a??oInicio  ")
            encabezadoGastos.add("  ${" MAYO  "} $a??oInicio  ")
            encabezadoGastos.add("  ${" JUNIO  "} $a??oInicio  ")
            encabezadoGastos.add("  ${" JULIO  "} $a??oInicio  ")
            encabezadoGastos.add("  ${" AGOSTO  "} $a??oInicio  ")
            encabezadoGastos.add("  ${" SEPTIEMBRE  "} $a??oInicio  ")
            encabezadoGastos.add("  ${" OCTUBRE  "} $a??oInicio  ")
            encabezadoGastos.add("  ${" NOVIEMBRE  "} $a??oInicio  ")
            encabezadoGastos.add("  ${" DICIEMBRE  "} $a??oInicio  ")
            a??oInicio++
            encabezadoGastos.add("  ${" ENERO  "} $a??oInicio  ")
            encabezadoGastos.add("  ${" FEBRERO  "} $a??oInicio  ")
            encabezadoGastos.add("  ${" MARZO  "} $a??oInicio  ")
        }
    }

    private fun crearEncabezadoCompras() {
        var a??oInicio = 2021
        while (a??oInicio < 2026){
            encabezadoCompras.add("  ${" ABRIL  "} $a??oInicio  ")
            encabezadoCompras.add("  ${" MAYO  "} $a??oInicio  ")
            encabezadoCompras.add("  ${" JUNIO  "} $a??oInicio  ")
            encabezadoCompras.add("  ${" JULIO  "} $a??oInicio  ")
            encabezadoCompras.add("  ${" AGOSTO  "} $a??oInicio  ")
            encabezadoCompras.add("  ${" SEPTIEMBRE  "} $a??oInicio  ")
            encabezadoCompras.add("  ${" OCTUBRE  "} $a??oInicio  ")
            encabezadoCompras.add("  ${" NOVIEMBRE  "} $a??oInicio  ")
            encabezadoCompras.add("  ${" DICIEMBRE  "} $a??oInicio  ")
            a??oInicio ++
            encabezadoCompras.add("  ${" ENERO  "} $a??oInicio  ")
            encabezadoCompras.add("  ${" FEBRERO  "} $a??oInicio  ")
            encabezadoCompras.add("  ${" MARZO  "} $a??oInicio  ")

        }
    }

    private fun redondear(numero: Double ): String {
        val num = numero
        val df = DecimalFormat("#.##")
        return df.format(num)
    }
}