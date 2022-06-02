package com.appjuego.contando

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main10.*
import java.text.DecimalFormat

class MainActivity10 : AppCompatActivity() {
    var proyeccion : String= ""
    var listaGeneral : ArrayList<ArrayList<String>> = ArrayList()
    var listaVentas : ArrayList<String> = ArrayList()
    var mesInicial = 0
    var datosNecesarios : ArrayList<String> = ArrayList()
    var tamTabla: Int = 0
    var listaTotal : ArrayList<String> = ArrayList()
    var listaComprasMensuales : ArrayList<String> = ArrayList()
    var ventasFinales : ArrayList<String> = ArrayList()
    var comprasFinales : ArrayList<String> = ArrayList()
    var valoresIt : ArrayList<ArrayList<String>> = ArrayList()
    var listaRec : ArrayList <String> = ArrayList()
    var listaAporteSalario : ArrayList<ArrayList<String>> = ArrayList()
    var compras : ArrayList <String> = ArrayList()
    var gastosOperativos : ArrayList <String> = ArrayList()
    var iva : ArrayList <String> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main10)
        val bundleProyeccion = intent.extras
        val bundleVentas = intent.extras
        val bundleMesIni = intent.extras
        val bundleDatos = intent.extras
        val bundleTotal = intent.extras
        val bundleCompraMensual = intent.extras
        val bundleRec = intent.extras
        val bundleAportesSalario = intent.extras
        listaAporteSalario = bundleAportesSalario?.getSerializable("keyAporteSalario") as ArrayList<ArrayList<String>>
        listaRec = bundleRec?.getStringArrayList("keyRecuperacion")!!
        listaTotal = bundleTotal?.getStringArrayList ("keyValoresTotalGastos")!!
        listaComprasMensuales = bundleCompraMensual?.getStringArrayList("keyValoresCompraMensual")!!
        datosNecesarios = bundleDatos?.getStringArrayList ("keyValorDatosNecesarios")!!
        proyeccion = bundleProyeccion?.getString("keyValorProyeccion")!!
        listaVentas = bundleVentas?.getStringArrayList ("keyValoresListaIngresoBruto")!!
        mesInicial = bundleMesIni?.getInt("keyMesInicial")!!
        tamTabla = listaComprasMensuales.size // modificar aqui
        crearTabla()
        siguiente()
    }

    private fun siguiente() {
        boton_IUE.setOnClickListener {
            var bundlesCompras = Bundle()
            var bundlesVenta = Bundle()
            var bundleIt = Bundle()
            val bundleRec = Bundle()
            val bundleAportesSalarios = Bundle()
            val bundleComprasActivos = Bundle()
            val bundleGastosOperativos = Bundle()
            val bundleIva = Bundle()
            bundleIva.putStringArrayList("keyIva", iva)
            bundleGastosOperativos.putStringArrayList("keyGastosOperativos", gastosOperativos)
            bundleComprasActivos.putStringArrayList("keyComprasActivo",compras)
            bundleAportesSalarios.putSerializable("keyAporteSalario",listaAporteSalario)
            bundleRec.putStringArrayList("keyRecuperacion",listaRec)
            bundleIt.putSerializable("keyIt",valoresIt)
            bundlesVenta.putStringArrayList("keyVenta",ventasFinales)
            bundlesCompras.putStringArrayList("keyCompra",comprasFinales)
            val ventana = Intent(this, MainActivity11:: class.java)
            ventana.putExtras(bundleIva)
            ventana.putExtras(bundleGastosOperativos)
            ventana.putExtras(bundleComprasActivos)
            ventana.putExtras(bundleAportesSalarios)
            ventana.putExtras(bundleRec)
            ventana.putExtras(bundleIt)
            ventana.putExtras(bundlesCompras)
            ventana.putExtras(bundlesVenta)
            startActivity(ventana)
        }
    }

    private fun crearTabla (){
        encabezado();
        listaGeneral.add(listaVentas)
        valoresIt.add(listaVentas)
        interesesComerciales()
        ventaActivosFijos()
        alquileres()
        totalVentaBienes()
        debitoFiscalPeriodo()
        var tableIva : TableDinamicaIva = TableDinamicaIva(tabla_proyeccion_iva,applicationContext,61)
        comprasMercaderia()
        interesesComersiales2()
        compraActivosFijos()
        subsidios()
        costosGastosOperativos()
        totalCompraVienes()
        creditoFiscalPeriodo()
        saldoFavorFisco()
        tableIva.addData(listaGeneral)
    }

    private fun saldoFavorFisco() {
        val saldoFisco : ArrayList <String> = ArrayList()
        saldoFisco.add("SALDO A FAVOR FISCO")
        var cont = 1
        while (cont <= tamTabla){
            var valor = listaGeneral.get(6).get(cont).toDouble() - listaGeneral.get(13).get(cont).toDouble()
            saldoFisco.add(redondear(valor))
            cont ++
        }
        iva = saldoFisco
        listaGeneral.add(saldoFisco)
    }

    private fun creditoFiscalPeriodo() {
        val creditoFiscal : ArrayList <String> = ArrayList()
        creditoFiscal.add("CREDITO FISCAL DEL PERIODO (13%)    ")
        var cont = 1;
        while (cont <= tamTabla){
            var valor = listaGeneral.get(12).get(cont).toDouble() * 0.13
            creditoFiscal.add(redondear(valor))
            cont++
        }
        listaGeneral.add(creditoFiscal)
    }

    private fun totalCompraVienes() {
        var totalComrpas : ArrayList <String> = ArrayList()
        totalComrpas.add ("TOTAL COMPRAS DE BIENES Y SERVICIOS  ")
        var a1 : ArrayList<String> = listaGeneral.get(7)
        var a2 : ArrayList<String> = listaGeneral.get(8)
        var a3 : ArrayList<String> = listaGeneral.get(9)
        var a4 : ArrayList<String> = listaGeneral.get(10)
        var a5 : ArrayList<String> = listaGeneral.get(11)
        var cont = 1
        while (cont <= tamTabla){
            var suma : Double = a1.get(cont).toDouble() + a2.get(cont).toDouble() + a3.get(cont).toDouble() + a4.get(cont).toDouble() + a5.get(cont).toDouble()
            totalComrpas.add(redondear(suma))
            comprasFinales.add(suma.toString())
            cont++
        }
        listaGeneral.add(totalComrpas)
    }

    private fun costosGastosOperativos() {
        var compraMercaderia : ArrayList <String> = ArrayList()
        compraMercaderia.add("COSTOS GASTOS OPERATIVOS")
        var cont = 0
        while (cont < tamTabla){
            var valor : Double = listaTotal.get(cont).toDouble()
            compraMercaderia.add(redondear(valor))
            cont++
        }
        gastosOperativos = compraMercaderia
        listaGeneral.add(compraMercaderia)
    }

    private fun subsidios() {
        var subsidios : ArrayList <String> = ArrayList()
        subsidios.add("SUBSIDIOS")
        subsidios.add(redondear(datosNecesarios.get(15).toDouble()))
        subsidios.add(redondear(datosNecesarios.get(16).toDouble()))
        subsidios.add(redondear(datosNecesarios.get(17).toDouble()))
        var cont = 4
        while (cont <= tamTabla){
            var intCom : Double = subsidios.get(cont - 3 ).toDouble() + (subsidios.get(cont - 3 ).toDouble() * proyeccion.toDouble())
            subsidios.add(redondear(intCom))
            cont++
        }
        listaGeneral.add(subsidios)
    }

    private fun compraActivosFijos() {
        var compraActivosFijos : ArrayList <String> = ArrayList()
        compraActivosFijos.add("COMPRA ACTIVOS FIJOS")
        compraActivosFijos.add(redondear(datosNecesarios.get(12).toDouble()))
        compraActivosFijos.add(redondear(datosNecesarios.get(13).toDouble()))
        compraActivosFijos.add(redondear(datosNecesarios.get(14).toDouble()))
        var cont = 4
        while (cont <= tamTabla){
            var intCom : Double = compraActivosFijos.get(cont - 3 ).toDouble() + (compraActivosFijos.get(cont - 3 ).toDouble() * proyeccion.toDouble())
            compraActivosFijos.add(redondear(intCom))
            cont++
        }
        listaGeneral.add(compraActivosFijos)
    }

    private fun interesesComersiales2() {
        var interesesComerciales : ArrayList <String> = ArrayList()
        interesesComerciales.add("INTERESES COMERCIALES              ")
        interesesComerciales.add(redondear(datosNecesarios.get(9).toDouble()))
        interesesComerciales.add(redondear(datosNecesarios.get(10).toDouble()))
        interesesComerciales.add(redondear(datosNecesarios.get(11).toDouble()))
        var cont = 4
        while (cont <= tamTabla){
            var intCom : Double = interesesComerciales.get(cont - 3 ).toDouble() + (interesesComerciales.get(cont - 3 ).toDouble() * proyeccion.toDouble())
            interesesComerciales.add(redondear(intCom))
            cont++
        }
        listaGeneral.add(interesesComerciales)
    }

    private fun comprasMercaderia() {
        var compraMercaderia : ArrayList <String> = ArrayList()
        compraMercaderia.add("COMPRA DE MARCADERIA, MATERIAS PRIMAS Y MATERIALES   ")
        var cont = 0
        while (cont < tamTabla ){
            var valor : Double = listaComprasMensuales.get(cont).toDouble()
            compraMercaderia.add(redondear(valor))
            cont++
        }
        compras = compraMercaderia
        listaGeneral.add(compraMercaderia)
    }

    private fun debitoFiscalPeriodo() {
        val debitoFiscal : ArrayList <String> = ArrayList()
        debitoFiscal.add("DEBITO FISCAL DEL PERIODO (13%)    ")
        var cont = 1;
        while (cont <= tamTabla){
            var valor = listaGeneral.get(5).get(cont).toDouble() * 0.13
            debitoFiscal.add(redondear(valor))
            cont++
        }
        listaGeneral.add(debitoFiscal)
    }

    private fun totalVentaBienes() {
        var totalVentas : ArrayList <String> = ArrayList()
        totalVentas.add ("TOTAL VENTA DE BIENES Y SERVICIOS  ")
        var a1 : ArrayList<String> = listaGeneral.get(1)
        var a2 : ArrayList<String> = listaGeneral.get(2)
        var a3 : ArrayList<String> = listaGeneral.get(3)
        var a4 : ArrayList<String> = listaGeneral.get(4)
        var cont = 1
        while (cont <= tamTabla){
            var suma : Double = a1.get(cont).toDouble() + a2.get(cont).toDouble() + a3.get(cont).toDouble() + a4.get(cont).toDouble()
            totalVentas.add(redondear(suma))
            ventasFinales.add(suma.toString())
            cont++
        }
        listaGeneral.add(totalVentas)
    }

    private fun alquileres() {
        var alquileres : ArrayList <String> = ArrayList()
        alquileres.add("ALQUILERES                         ")
        alquileres.add(redondear(datosNecesarios.get(6).toDouble()))
        alquileres.add(redondear(datosNecesarios.get(7).toDouble()))
        alquileres.add(redondear(datosNecesarios.get(8).toDouble()))
        var cont = 4
        while (cont <= tamTabla){
            var valor : Double = alquileres.get(cont - 3 ).toDouble() + (alquileres.get(cont - 3 ).toDouble() * proyeccion.toDouble())
            alquileres.add(redondear(valor))
            cont++
        }
        valoresIt.add(alquileres)
        listaGeneral.add(alquileres)
    }

    private fun ventaActivosFijos() {
        var ventaActivos : ArrayList <String> = ArrayList()
        ventaActivos.add("VENTA ACTIVOS FIJOS                ")
        ventaActivos.add(redondear(datosNecesarios.get(3).toDouble()))
        ventaActivos.add(redondear(datosNecesarios.get(4).toDouble()))
        ventaActivos.add(redondear(datosNecesarios.get(5).toDouble()))
        var cont = 4
        while (cont <= tamTabla){
            var valor : Double = ventaActivos.get(cont - 3 ).toDouble() + (ventaActivos.get(cont - 3 ).toDouble() * proyeccion.toDouble())
            ventaActivos.add(redondear(valor))
            cont++
        }
        valoresIt.add(ventaActivos)
        listaGeneral.add(ventaActivos)
    }

    private fun interesesComerciales() {
        var interesesComerciales : ArrayList <String> = ArrayList()
        interesesComerciales.add("INTERESES COMERCIALES              ")
        interesesComerciales.add(redondear(datosNecesarios.get(0).toDouble()))
        interesesComerciales.add(redondear(datosNecesarios.get(1).toDouble()))
        interesesComerciales.add(redondear(datosNecesarios.get(2).toDouble()))
        var cont = 4
        while (cont <= tamTabla){
            var intCom : Double = interesesComerciales.get(cont - 3 ).toDouble() + (interesesComerciales.get(cont - 3 ).toDouble() * proyeccion.toDouble())
            interesesComerciales.add(redondear(intCom))
            cont++
        }
        valoresIt.add(interesesComerciales)
        listaGeneral.add(interesesComerciales)
    }

    private fun encabezado() {
        var encabezado : ArrayList<String> = ArrayList()
        encabezado.add("IVA                                ")
        var cont= 1
        while (cont <= tamTabla){
            encabezado.add(escogerMes())
            cont++
        }
        listaGeneral.add(encabezado)
    }

    private fun redondear (valor: Double) : String{
        var valorEntero= valor.toInt()
        if ((valor - valorEntero)>= 0.5){
            valorEntero =  valorEntero + 1
        }
        return valorEntero.toString()
    }

    private fun escogerMes () : String{
        var mesEscogido = ""
        if (mesInicial == 0){
            mesEscogido = "ENERO       "
            mesInicial ++
        }else{
            if(mesInicial == 1){
                mesEscogido = "FEBRERO     "
                mesInicial ++
            }else{
                if (mesInicial == 2){
                    mesEscogido = "MARZO       "
                    mesInicial ++
                }else{
                    if(mesInicial == 3){
                        mesEscogido = "ABRIL       "
                        mesInicial ++
                    }else{
                        if (mesInicial == 4){
                            mesEscogido = "MAYO        "
                            mesInicial ++
                        }else{
                            if(mesInicial == 5){
                                mesEscogido = "JUNIO       "
                                mesInicial ++
                            }else{
                                if (mesInicial == 6){
                                    mesEscogido = "JULIO       "
                                    mesInicial ++
                                }else{
                                    if(mesInicial == 7){
                                        mesEscogido = "AGOSTO      "
                                        mesInicial ++
                                    }else{
                                        if (mesInicial == 8){
                                            mesEscogido = "SEPTIEMBRE  "
                                            mesInicial ++
                                        }else{
                                            if(mesInicial == 9){
                                                mesEscogido = "OCTUBRE     "
                                                mesInicial ++
                                            }else{
                                                if (mesInicial == 10){
                                                    mesEscogido = "NOVIEMBRE   "
                                                    mesInicial ++
                                                }else{
                                                    if(mesInicial == 11){
                                                        mesEscogido = "DICIEMBRE   "
                                                        mesInicial ++
                                                    }else{
                                                        mesInicial = 0
                                                        mesEscogido = escogerMes()
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return mesEscogido
    }
}

