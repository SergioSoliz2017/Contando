package com.appjuego.contando

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main4.*

import java.math.RoundingMode
import java.text.DecimalFormat


//aqui esta la lista que se tiene que mandar
class MainActivity4 : AppCompatActivity() {

    var arregloAbril: Array<String>? = null
    var arregloMayo: Array<String>? = null
    var arregloJunio: Array<String>? = null
    var arregloMesAño: Array<String>? = null
    var mesInicial : Int = 0
    var listaFila : ArrayList<ArrayList<String>> = ArrayList()
    var porcentajeProyeccion : Double = 0.0
    var tasaProyeccion : Double = 0.0
    var listaIngresoBruto : ArrayList <String> = ArrayList()
    var listaRec : ArrayList <String> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main4)
        val bundleAbril = intent.extras
        val bundleMayo = intent.extras
        val bundleJunio = intent.extras
        val bundleMesAño = intent.extras
        val bundleProyecTasa = intent.extras
        arregloAbril= bundleAbril?.getStringArray("keyValoresAbril")!!
        arregloMayo = bundleMayo?.getStringArray("keyValoresMayo")!!
        arregloJunio = bundleJunio?.getStringArray("keyValoresJunio")!!
        arregloMesAño = bundleMesAño?.getStringArray("keyValoresMesAño")!!
        var arregloProyecTasa : Array <String> = bundleProyecTasa?.getStringArray("keyProyecTasa")!!
        porcentajeProyeccion = arregloProyecTasa [0].toDouble()
        tasaProyeccion = arregloProyecTasa [1].toDouble()
        mesInicial  = arregloMesAño!![0].toInt()
        var distancia : Int = calcularDistanciaMeses ()
        listaIngresoBruto.add("VENTAS")
        cargarDatosMes1 ()
        cargarDatosMes2()
        cargarDatoMes3()
        crearTabla (distancia)
        siguiente ()
    }

    private fun siguiente(){
        boton_suedos_salarios.setOnClickListener {
            val bundleMesAño = Bundle()
            val bundleListaIngresoBruto = Bundle()
            val bundleProyeccion = Bundle()
            val bundleMesIni = Bundle()
            val bundleRec = Bundle()
            bundleRec.putStringArrayList("keyRecuperacion",listaRec)
            bundleListaIngresoBruto.putStringArrayList("keyValoresListaIngresoBruto",listaIngresoBruto)
            bundleMesAño.putStringArray("keyValoresMesAño", arregloMesAño)
            bundleProyeccion.putString ("keyValorProyeccion",porcentajeProyeccion.toString())
            bundleMesIni.putInt("keyMesInicial",arregloMesAño!![0].toInt())
            val ventana = Intent(this, MainActivity5:: class.java)
            ventana.putExtras(bundleRec)
            ventana.putExtras(bundleListaIngresoBruto)
            ventana.putExtras(bundleMesIni)
            ventana.putExtras(bundleProyeccion)
            ventana.putExtras(bundleMesAño)
            startActivity(ventana)
        }
    }

    private fun crearTabla(distancia: Int) {
        var cont : Int = 3
        while (cont <= distancia){
            var listaCol : ArrayList<String> = ArrayList()
            listaCol.add (escogerMes())
            var ventaUnidades = listaFila.get(cont-3).get(1).toDouble()+ (listaFila.get(cont-3).get(1).toDouble()*porcentajeProyeccion)
            var precioUnidades = listaFila.get(cont-3).get(2).toDouble()+ (listaFila.get(cont-3).get(2).toDouble()*tasaProyeccion)
            listaCol.add ((ventaUnidades).toInt().toString())
            listaCol.add (redondear(precioUnidades))
            listaCol.add ((precioUnidades*ventaUnidades).toInt().toString())
            listaIngresoBruto.add((precioUnidades*ventaUnidades).toInt().toString())
            listaCol.add ((precioUnidades*ventaUnidades).toInt().toString())
            listaCol.add (0.toString())
            listaCol.add (0.toString())
            listaFila.add(listaCol)
            cont ++
        }
        var encabezado = listOf<String>("MES         ","VENTA UNIDADES (Q)  ","PRECIO UNITARIO (BS)  ","INGRESO BRUTO (BS)  ","VENTAS AL CONTADO  ","RECUPERACION C*C* 30 DIAS"," RECUPERACION C*C 60 DIAS")
        var tableDinamic : TableDinamica = TableDinamica(tabla_proyeccion, applicationContext)
        tableDinamic.addHeader(encabezado.toTypedArray())
        tableDinamic.addData(listaFila)
    }

    private fun calcularDistanciaMeses() :Int {
        var añoInicio : Int = arregloMesAño!![4]!!.toInt()
        var añoFin : Int = arregloMesAño!![5]!!.toInt()
        var difAños : Int = (añoFin - añoInicio) * 12
        var mesInicio : Int = arregloMesAño!![0].toInt()
        var mesFin : Int = arregloMesAño!![1].toInt()
        var difMeses : Int = mesFin - mesInicio
        return difAños + difMeses
    }

    private fun cargarDatosMes1 (){
        var listaCol : ArrayList<String> = ArrayList()
        listaCol.add (escogerMes())
        listaCol.add (arregloAbril!![0])
        listaCol.add (arregloAbril!![1])
        listaCol.add (arregloAbril!![2])
        listaCol.add (arregloAbril!![2])
        listaCol.add (arregloAbril!![5])
        listaCol.add (arregloAbril!![4])

        listaRec.add(arregloAbril!![5])
        listaRec.add(arregloAbril!![4])
        listaFila.add(listaCol)
        listaIngresoBruto.add(arregloAbril!![2])
    }

    private fun cargarDatosMes2 (){
        var listaCol : ArrayList<String> = ArrayList()
        listaCol.add (escogerMes())
        listaCol.add (arregloMayo!![0])
        listaCol.add (arregloMayo!![1])
        listaCol.add (arregloMayo!![2])
        listaCol.add (arregloMayo!![2])
        listaCol.add (arregloMayo!![5])
        listaCol.add (arregloMayo!![4])

        listaRec.add(arregloMayo!![5])
        listaRec.add(arregloMayo!![4])
        listaFila.add(listaCol)
        listaIngresoBruto.add(arregloMayo!![2])
    }

    private fun cargarDatoMes3 (){
        var listaCol : ArrayList<String> = ArrayList()
        listaCol.add (escogerMes())
        listaCol.add (arregloJunio!![0])
        listaCol.add (arregloJunio!![1])
        listaCol.add (arregloJunio!![2])
        listaCol.add (arregloJunio!![2])
        listaCol.add (arregloJunio!![5])
        listaCol.add (arregloJunio!![4])

        listaRec.add(arregloJunio!![5])
        listaRec.add(arregloJunio!![4])
        listaFila.add(listaCol)
        listaIngresoBruto.add(arregloJunio!![2])
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

    private fun redondear(numero: Double): String {
        var num = numero
        val df = DecimalFormat("#.##")
        return df.format(num)
    }
}
