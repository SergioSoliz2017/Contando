package com.appjuego.contando

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main13.*
import java.util.ArrayList

class MainActivity13 : AppCompatActivity() {

    var listaRec : ArrayList<String> = ArrayList()
    var ventas : ArrayList <String> = ArrayList()
    val listaGeneral : ArrayList <ArrayList<String>> = ArrayList()
    var listaAporteSalario : ArrayList<ArrayList<String>> = ArrayList()
    var compras : ArrayList <String> = ArrayList()
    var gastosOperativos : ArrayList <String> = ArrayList()
    var iva : ArrayList <String> = ArrayList()
    var it : ArrayList <String> = ArrayList()
    var iue : ArrayList <String> = ArrayList()
    var ingreso : String = ""
    var gasto : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main13)
        val bundleRec = intent.extras
        val bundleVentas = intent.extras
        val bundleAportesSalario = intent.extras
        val bundleCompraActivo = intent.extras
        val bundleGastoOperativo = intent.extras
        val bundleIva = intent.extras
        val bundleIt = intent.extras
        val bundleIue = intent.extras
        iue = bundleIue?.getStringArrayList("keuIue")!!
        it = bundleIt?.getStringArrayList("keyIt")!!
        iva = bundleIva?.getStringArrayList("keyIva")!!
        gastosOperativos = bundleGastoOperativo?.getStringArrayList("keyGastosOperativos")!!
        compras = bundleCompraActivo?.getStringArrayList("keyComprasActivo")!!
        listaAporteSalario = bundleAportesSalario?.getSerializable("keyAporteSalario") as ArrayList<ArrayList<String>>
        ventas = bundleVentas?.getStringArrayList("keyVentas")!!
        listaRec = bundleRec?.getStringArrayList("keyRecuperacion")!!
        encabezado()
        ventas.set(0,"VENTAS AL CONTADO                       ")
        sumarVentas()
        listaGeneral.add(ventas)
        recuperacion()
        totalEntradasEfectivo()
        espacio()
        salidaEspacio("SALIDAS EFECTIVO", "")
        compras.set(0,"COMPRAS ")
        sumarCompras()
        listaGeneral.add(compras)
        sueldosSalarios()
        aportesPatronales()
        retroactivoSalarial()
        retroactivoAportes()
        sumarGastos()
        listaGeneral.add(gastosOperativos)
        iva.set(0,"IVA")
        sumarIva()
        listaGeneral.add(iva)
        it.set(0,"IT")
        verificaIt()
        sumarIt()
        listaGeneral.add(it)
        iue()
        totalSalidaEfectivo()
        espacio()
        flujoNeto()
        saldo()
        crearTabla()
        siguiente()
    }

    private fun siguiente() {
        boton_flujo_caja.setOnClickListener {
            var bundleIngreso = Bundle()
            var bundleGasto = Bundle()
            bundleIngreso.putString("keyIngreso",ingreso)
            bundleGasto.putString("keyGasto",gasto)
            val ventana = Intent(this, MainActivity14:: class.java)
            ventana.putExtras(bundleIngreso)
            ventana.putExtras(bundleGasto)
            startActivity(ventana)
        }
    }

    private fun espacio() {
        var espacio : ArrayList<String> = ArrayList()
        var cont = 0
        while (cont < 62){
            espacio.add("")
            cont++
        }
        listaGeneral.add(espacio)
    }

    private fun verificaIt (){
        var cont = 1
        while (cont < it.size){
            if (it.get(cont).toInt() < 0){
                it.set(cont,"0")
            }
            cont++
        }
    }

    private fun saldo() {
        var saldoAntes : ArrayList<String> = ArrayList()
        var saldoFinal : ArrayList<String> = ArrayList()
        saldoAntes.add("SALDO FINAL MES ANTERIOR")
        saldoFinal.add("SALDO FINAL DE EFECTIVO PROYECTADO SIN FINANCIAMIENTO  ")
        saldoAntes.add("16000")
        var valor : Double =  listaGeneral.get(18).get(1).toDouble() + saldoAntes.get(1).toDouble()
        saldoAntes.add(valor.toInt().toString())
        saldoFinal.add(valor.toInt().toString())
        var cont = 2
        while (cont < 62){
            valor = listaGeneral.get(18).get(cont).toDouble() + saldoAntes.get(cont).toDouble()
            saldoAntes.add(valor.toInt().toString())
            saldoFinal.add(valor.toInt().toString())
            cont ++
        }
        listaGeneral.add(saldoAntes)
        listaGeneral.add(saldoFinal)
        salidaEspacio("FINACIAMIENTO A CORTO PLAZO " , "0")
        salidaEspacio("AMORTIZACION FINANCIAMIENTO A CORTO PLAZO", "0")
        salidaEspacio("INTERESES FINANCIAMIENTO  A CORTO PLAZO", "0")
        listaGeneral.add(saldoFinal)
    }

    private fun flujoNeto() {
        var flujo : ArrayList<String> = ArrayList()
        flujo.add("FLUJO NETO PROYECTADO SIN FINANCIAMIENTO")
        var cont = 1
        while (cont < 62){
            var valor : Double = listaGeneral.get(4).get(cont).toDouble() - listaGeneral.get(16).get(cont).toDouble()
            flujo.add(valor.toInt().toString())
            cont ++
        }
        listaGeneral.add(flujo)
    }

    private fun sumarIt() {
        var suma : Double = 0.0
        var cont = 1
        while (cont < 61){
            suma = suma + it.get(cont).toDouble()
            cont++
        }
        it.add(suma.toInt().toString())
    }

    private fun sumarIva() {
        var suma : Double = 0.0
        var cont = 1
        while (cont < 61){
            suma = suma + iva.get(cont).toDouble()
            cont++
        }
        iva.add(suma.toInt().toString())
    }

    private fun sumarGastos() {
        var suma : Double = 0.0
        var cont = 1
        while (cont < 61){
            suma = suma + gastosOperativos.get(cont).toDouble()
            cont++
        }
        gastosOperativos.add(suma.toInt().toString())
    }

    private fun totalSalidaEfectivo() {
        var totalEfectivo : ArrayList<String> = ArrayList()
        totalEfectivo.add("TOTAL SALIDA DE EFECTIVO ")
        var suma = 0.0
        var cont = 1
        while (cont < 62){
            suma =  listaGeneral.get(7).get(cont).toDouble() +
                    listaGeneral.get(8).get(cont).toDouble() +
                    listaGeneral.get(9).get(cont).toDouble() +
                    listaGeneral.get(10).get(cont).toDouble() +
                    listaGeneral.get(11).get(cont).toDouble() +
                    listaGeneral.get(12).get(cont).toDouble() +
                    listaGeneral.get(13).get(cont).toDouble() +
                    listaGeneral.get(14).get(cont).toDouble() +
                    listaGeneral.get(15).get(cont).toDouble()
            cont++
            totalEfectivo.add(suma.toInt().toString())
        }
        gasto = totalEfectivo.get(61)
        listaGeneral.add(totalEfectivo)
    }

    private fun iue() {
        var iueTabla : ArrayList<String> = ArrayList()
        iueTabla.add("IUE")
        var cont = 1
        var control = 12
        var valido = 1
        while (valido < 61) {
            if (control == 12) {
                iueTabla.add(iue.get(cont))
                cont++
                control = 1
                valido++
            } else {
                iueTabla.add("0")
                control++
                valido++
            }
        }
        sumarSueldos(iueTabla)
        listaGeneral.add(iueTabla)
    }

    private fun retroactivoAportes() {
        var retroactivoAporte : ArrayList<String> = ArrayList()
        retroactivoAporte.add("RETROACTIVOS APORTES PATRONALES ")
        var cont = 0
        var control = 10
        var valido = 1
        while (valido < 61){
            if (control == 12){
                cont++
                retroactivoAporte.add(listaAporteSalario.get(3).get(cont))
                control = 1
                valido++
            }else{
                retroactivoAporte.add("0")
                control++
                valido++
            }
        }
        sumarSueldos(retroactivoAporte)
        listaGeneral.add(retroactivoAporte)
    }

    private fun retroactivoSalarial() {
        var retroactivoSalarial : ArrayList<String> = ArrayList()
        retroactivoSalarial.add("RETROACTIVOS SUELDOS Y SALARIOS ")
        var cont = 0
        var control = 10
        var valido = 1
        while (valido < 61){
            if (control == 12){
                cont++
                retroactivoSalarial.add(listaAporteSalario.get(2).get(cont))
                control = 1
                valido++
            }else{
                retroactivoSalarial.add("0")
                control++
                valido++
            }
        }
        sumarSueldos(retroactivoSalarial)
        listaGeneral.add(retroactivoSalarial)
    }

    private fun sueldosSalarios() {
        var sueldos : ArrayList<String> = ArrayList()
        sueldos.add("SUELDOS Y SALARIOS ")
        var cont = 0
        var control = 10
        var valido = 1
        while (valido < 61){
            if (control == 12){
                cont++
                sueldos.add(listaAporteSalario.get(0).get(cont))
                control = 1
                valido++
            }else{
                sueldos.add(listaAporteSalario.get(0).get(cont))
                control++
                valido++
            }
        }
        sumarSueldos(sueldos)
        listaGeneral.add(sueldos)
    }

    private fun sumarSueldos(sueldos: ArrayList<String>) {
        var suma : Double = 0.0
        var cont = 1
        while (cont < 61){
            suma = suma + sueldos.get(cont).toDouble()
            cont++
        }
        sueldos.add(suma.toInt().toString())
    }

    private fun aportesPatronales() {
        var aportes : ArrayList<String> = ArrayList()
        aportes.add("APORTES PATRONALES")
        var cont = 0
        var control = 10
        var valido = 1
        while (valido < 61){
            if (control == 12){
                cont++
                aportes.add(listaAporteSalario.get(1).get(cont))
                control = 1
                valido++
            }else{
                aportes.add(listaAporteSalario.get(1).get(cont))
                control++
                valido++
            }
        }
        sumarSueldos(aportes)
        listaGeneral.add(aportes)
    }

    private fun salidaEspacio(texto : String , valor : String) {
        var salida : ArrayList<String> = ArrayList()
        salida.add(texto)
        var cont = 1
        while (cont < 62){
            salida.add(valor)
            cont++
        }
        listaGeneral.add(salida)
    }

    private fun totalEntradasEfectivo() {
        var totalEfectivo : ArrayList<String> = ArrayList()
        totalEfectivo.add("TOTAL ENTRADAS DE EFECTIVO ")
        var suma = 0.0
        var cont = 1
        while (cont < 62){
            suma = listaGeneral.get(1).get(cont).toDouble() + listaGeneral.get(2).get(cont).toDouble() + listaGeneral.get(3).get(cont).toDouble()
            cont++
            totalEfectivo.add(suma.toString())
        }
        ingreso = totalEfectivo.get(61)
        listaGeneral.add(totalEfectivo)
    }

    private fun sumarVentas() {
        var suma : Double = 0.0
        var cont = 1
        while (cont < 61){
            suma = suma + ventas.get(cont).toDouble()
            cont++
        }
        ventas.add(suma.toInt().toString())
    }

    private fun sumarCompras() {
        var suma : Double = 0.0
        var cont = 1
        while (cont < 61){
            suma = suma + compras.get(cont).toDouble()
            cont++
        }
        compras.add(suma.toInt().toString())
    }

    private fun crearTabla() {
        var tablaPresupuesto : TableDinamicaIva = TableDinamicaIva(tabla_presupuesto_caja,applicationContext , 62)
        tablaPresupuesto.addData(listaGeneral)
    }

    private fun recuperacion() {
        val recu30 : ArrayList <String> = ArrayList()
        val recu60 : ArrayList <String> = ArrayList()
        recu60.add("RECUPERACION CUENTAS POR COBRAR 60 DIAS  ")
        recu30.add("RECUPERACION CUENTAS POR COBRAR 30 DIAS  ")
        recu30.add(listaRec.get(0))
        recu60.add(listaRec.get(1))
        recu30.add(listaRec.get(2))
        recu60.add(listaRec.get(3))
        recu30.add(listaRec.get(4))
        recu60.add(listaRec.get(5))
        var cont = 4
        while (cont < 61 ){
            recu30.add("0")
            recu60.add("0")
            cont++
        }
        sumarRecuperacion(recu30,recu60)
        listaGeneral.add(recu60)
        listaGeneral.add(recu30)
    }

    private fun sumarRecuperacion(recu30: ArrayList<String>, recu60: ArrayList<String>) {
        var suma1 : Double = 0.0
        var suma2 : Double = 0.0
        var cont = 1
        while (cont < 61){
            suma1 = suma1 + recu30.get(cont).toDouble()
            suma2 = suma2 + recu60.get(cont).toDouble()
            cont++
        }
        recu30.add(suma1.toString())
        recu60.add(suma2.toString())
    }

    private fun encabezado() {
        var encabezado : ArrayList<String> = ArrayList()
        encabezado.add("ENTRADAS EFECTIVO         ")
        var añoInicio = 2021
        while (añoInicio < 2026) {
            encabezado.add("  ${"ABRIL  "} $añoInicio  ")
            encabezado.add("  ${"MAYO  "} $añoInicio  ")
            encabezado.add("  ${"JUNIO  "} $añoInicio  ")
            encabezado.add("  ${"JULIO  "} $añoInicio  ")
            encabezado.add("  ${"AGOSTO  "} $añoInicio  ")
            encabezado.add("  ${"SEPTIEMBRE  "} $añoInicio  ")
            encabezado.add("  ${"OCTUBRE  "} $añoInicio  ")
            encabezado.add("  ${"NOVIEMBRE  "} $añoInicio  ")
            encabezado.add("  ${"DICIEMBRE  "} $añoInicio  ")
            añoInicio++
            encabezado.add("  ${"ENERO  "} $añoInicio  ")
            encabezado.add("  ${"FEBRERO  "} $añoInicio  ")
            encabezado.add("  ${"MARZO  "} $añoInicio  ")
        }
        encabezado.add("TOTAL")
        listaGeneral.add(encabezado)
    }
}