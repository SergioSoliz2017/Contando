package com.appjuego.contando

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main6.*

class MainActivity6 : AppCompatActivity() {

    var arregloMesAño: Array<String>? = null
    var arregloSalario:Array<String>? = null
    var listaEncabezadosAportesPatronales : ArrayList<String>? = ArrayList()
    var listaValoresAportesPatronales : ArrayList<String>? = ArrayList()
    var listaEncabezadosRetroactivoSalarial : ArrayList<String>? = ArrayList()
    var listaValoresRetroactivoSalarial : ArrayList<String>? = ArrayList()
    var listaEncabezadosRetroactivoPatronales : ArrayList<String>? = ArrayList()
    var listaValoresRetroactivoPatronal : ArrayList<String>? = ArrayList()
    var proyeccion : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main6)
        val bundleMesAño = intent.extras
        val bundleSalario = intent.extras
        val bundleProyeccion = intent.extras
        proyeccion = bundleProyeccion!!.getString("keyValorProyeccion")!!
        arregloMesAño = bundleMesAño?.getStringArray("keyValoresMesAño")!!
        arregloSalario = bundleSalario?.getStringArray("keyValoresSalarios")!!
        crearEncabezadoAportePatronal()
        val tableDinamicAportePatronales : TableDinamicaSalario = TableDinamicaSalario(tabla_aportes_patronales, applicationContext)
        tableDinamicAportePatronales.addHeader(listaEncabezadosAportesPatronales)
        crearValoresAportePatronal()
        tableDinamicAportePatronales.addData(listaValoresAportesPatronales)
        crearEncabezadoRetroactivoSalaria()
        val tableDinamicRetroactivoSalarial : TableDinamicaSalario = TableDinamicaSalario(tabla_retroactivo_salarial, applicationContext)
        tableDinamicRetroactivoSalarial.addHeader(listaEncabezadosRetroactivoSalarial)
        crearValoresRetroactivoSalarial()
        tableDinamicRetroactivoSalarial.addData(listaValoresRetroactivoSalarial)
        crearEncabezadoRetroactivoPatronal()
        val tableDinamicRetroactivoPatronales : TableDinamicaSalario = TableDinamicaSalario(retroactivo_patronales, applicationContext)
        tableDinamicRetroactivoPatronales.addHeader(listaEncabezadosRetroactivoPatronales)
        crearValoresRetroactivoPatronal()
        tableDinamicRetroactivoPatronales.addData(listaValoresRetroactivoPatronal)
        siguiente()
    }

    private fun siguiente (){
        boton_flujo_salida.setOnClickListener {
            val bundleProyeccion = Bundle()
            bundleProyeccion.putString("keyValorProyeccion",proyeccion)
            val ventana = Intent(this, MainActivity8:: class.java)
            ventana.putExtras(bundleProyeccion)
            startActivity(ventana)
        }
    }

    private fun crearEncabezadoRetroactivoPatronal() {
        var cont : Int = 0;
        var aum : Int = 0;
        var añoInicial : Int = arregloMesAño!![4].toInt()
        while (cont < 5){
            añoInicial  = añoInicial + aum
            listaEncabezadosRetroactivoPatronales!!.add("RETROACTIVO $añoInicial   ")
            listaEncabezadosRetroactivoPatronales!!.add("   NUMERO DE MESES $añoInicial   ")
            listaEncabezadosRetroactivoPatronales!!.add("   RETROACTIVOS APORTES PATRONALES POR MES $añoInicial   ")
            cont++
            aum = 1
        }
    }

    private fun crearEncabezadoRetroactivoSalaria() {
        var cont : Int = 0;
        var aum : Int = 0;
        var añoInicial : Int = arregloMesAño!![4].toInt()
        while (cont < 5){
            añoInicial  = añoInicial + aum
            listaEncabezadosRetroactivoSalarial!!.add("RETROACTIVO $añoInicial   ")
            listaEncabezadosRetroactivoSalarial!!.add("   NUMERO DE MESES $añoInicial   ")
            listaEncabezadosRetroactivoSalarial!!.add("   RETROACTIVOS SUELDOS POR MES $añoInicial   ")
            cont++
            aum = 1
        }
    }

    private fun crearEncabezadoAportePatronal() {
        var cont : Int = 0;
        var aum : Int = 0;
        var añoInicial : Int = arregloMesAño!![4].toInt()
        while (cont < 5){
            añoInicial  = añoInicial + aum
            listaEncabezadosAportesPatronales!!.add("ABRIL $añoInicial   ")
            listaEncabezadosAportesPatronales!!.add("   MAYO $añoInicial   ")
            listaEncabezadosAportesPatronales!!.add("   JUNIO $añoInicial   ")
            cont++
            aum = 1
        }
    }

    private fun crearValoresAportePatronal () {
        val aportePatronal: Double = obtenerProbabilidad(arregloSalario!![0].toDouble())
        val incrementoSalarial: Double = obtenerProbabilidad(arregloSalario!![1].toDouble())
        val numeroTrabajadores: Double = arregloSalario!![2].toDouble()
        var sueldoIndividualSinIncremento: Double = arregloSalario!![3].toDouble()
        var sueldosSalariosSinIncremento: Double =
            sueldoIndividualSinIncremento * numeroTrabajadores
        var sueldoIndividualIncrementado: Double =
            (sueldoIndividualSinIncremento * incrementoSalarial) + sueldoIndividualSinIncremento
        var sueldosSalariosConIncremento: Double = sueldoIndividualIncrementado * numeroTrabajadores
        var aportePatronal1: Double = sueldosSalariosSinIncremento * aportePatronal
        var aportePatronal2: Double = sueldosSalariosConIncremento * aportePatronal
        //val aportePatronal3 : Double = sueldosSalariosConIncremento * aportePatronal
        listaValoresAportesPatronales!!.add(aportePatronal1.toInt().toString())
        listaValoresAportesPatronales!!.add(aportePatronal2.toInt().toString())
        listaValoresAportesPatronales!!.add(aportePatronal2.toInt().toString())
        var i: Int = 3
        while (i < listaEncabezadosAportesPatronales!!.size) {
            aportePatronal1 = aportePatronal2
            sueldoIndividualSinIncremento = sueldoIndividualIncrementado
            sueldoIndividualIncrementado = (sueldoIndividualSinIncremento * incrementoSalarial) + sueldoIndividualSinIncremento
            sueldosSalariosConIncremento = sueldoIndividualIncrementado * numeroTrabajadores
            aportePatronal2 = sueldosSalariosConIncremento * aportePatronal
            listaValoresAportesPatronales!!.add(aportePatronal1.toInt().toString())
            listaValoresAportesPatronales!!.add(aportePatronal2.toInt().toString())
            listaValoresAportesPatronales!!.add(aportePatronal2.toInt().toString())
            i = i + 3
        }

    }

    private fun crearValoresRetroactivoSalarial() {
        val incrementoSalarial: Double = obtenerProbabilidad(arregloSalario!![1].toDouble())
        val numeroTrabajadores: Double = arregloSalario!![2].toDouble()
        var sueldoIndividualSinIncremento: Double = arregloSalario!![3].toDouble()
        var sueldosSalariosSinIncremento: Double =
            sueldoIndividualSinIncremento * numeroTrabajadores
        var retroactivo : Double = sueldosSalariosSinIncremento * incrementoSalarial
        var numeroMeses : Int = 4
        var retroactivoMeses : Double = retroactivo * numeroMeses
        listaValoresRetroactivoSalarial!!.add(retroactivo.toInt().toString())
        listaValoresRetroactivoSalarial!!.add(numeroMeses.toString())
        listaValoresRetroactivoSalarial!!.add(retroactivoMeses.toInt().toString())
        var sueldoSalarial: Double = ((sueldoIndividualSinIncremento * incrementoSalarial) + sueldoIndividualSinIncremento)*numeroTrabajadores
        var i : Int = 3
        while (i < listaEncabezadosRetroactivoSalarial!!.size){
            retroactivo = sueldoSalarial * incrementoSalarial
            retroactivoMeses = retroactivo * numeroMeses
            listaValoresRetroactivoSalarial!!.add(retroactivo.toInt().toString())
            listaValoresRetroactivoSalarial!!.add(numeroMeses.toString())
            listaValoresRetroactivoSalarial!!.add(retroactivoMeses.toInt().toString())
            sueldoSalarial = (sueldoSalarial*incrementoSalarial)+sueldoSalarial
            i = i + 3
        }
    }

    private fun crearValoresRetroactivoPatronal() {
        val aportePatronal: Double = obtenerProbabilidad(arregloSalario!![0].toDouble())
        var i : Int = 0
        while (i < listaEncabezadosRetroactivoPatronales!!.size){
            val reactivoSalarial : Double = listaValoresRetroactivoSalarial!!.get(i).toDouble()
            var retroactivo : Double = reactivoSalarial * aportePatronal
            val numeroMeses : Int = 4
            val aportesPatronalesMes : Int = redondear((retroactivo * numeroMeses).toDouble())
            retroactivo = redondear(retroactivo).toDouble()
            listaValoresRetroactivoPatronal!!.add(retroactivo.toInt().toString())
            listaValoresRetroactivoPatronal!!.add(numeroMeses.toString())
            listaValoresRetroactivoPatronal!!.add(aportesPatronalesMes.toString())
            i = i + 3
        }

    }

    private fun obtenerProbabilidad (valor : Double) : Double{
        var probabilidad : Double = 0.0
        if (valor >= 1.0){
            probabilidad = valor / 100
        }else{
            probabilidad = valor
        }
        return probabilidad
    }

    private fun redondear (valor: Double) : Int{
        var valorEntero= valor.toInt()
        if ((valor - valorEntero)>= 0.5){
            valorEntero =  valorEntero + 1
        }
        return valorEntero
    }

}