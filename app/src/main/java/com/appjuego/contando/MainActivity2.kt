package com.appjuego.contando

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main2.*
import java.math.RoundingMode
import java.text.DecimalFormat

class MainActivity2 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val bundle = intent.extras
        val arreglo: Array<String> = bundle?.getStringArray("keyValores")!!
        val num1: Double? = arreglo[0].toDouble()
        val num2: Double? = arreglo[1].toDouble()
        val num3: Double? = arreglo[2].toDouble()
        val num4: Double? = arreglo[3].toDouble()

        val num5: Double? = arreglo[4].toDouble()
        val num6: Double? = arreglo[5].toDouble()
        val num7: Double? = arreglo[6].toDouble()
        val num8: Double? = arreglo[7].toDouble()
        val num9: Double? = arreglo[8].toDouble()
        val num10: Double? = arreglo[9].toDouble()

        val abril2:TextView = findViewById(R.id.textAbril)
        abril2.text=num1.toString()
        val mayo2:TextView = findViewById(R.id.textMayo)
        mayo2.text=num2.toString()
        val junio2:TextView = findViewById(R.id.textJunio)
        junio2.text=num3.toString()

        val precioUni21:TextView = findViewById(R.id.textPreUni21)
        precioUni21.text=num4.toString()
        val precioUni22:TextView = findViewById(R.id.textPreUni22)
        precioUni22.text=num4.toString()
        val precioUni23:TextView = findViewById(R.id.textPreUni23)
        precioUni23.text=num4.toString()

        val precioBru21:TextView = findViewById(R.id.textbruto21)
        val bruto21:Double? = num1!! * num4!!
        precioBru21.text= bruto21?.let { redondear(it) }
        val precioBru22:TextView = findViewById(R.id.textbruto22)
        val bruto22:Double? = num2!!* num4!!
        precioBru22.text= bruto22?.let { redondear(it) }
        val precioBru23:TextView = findViewById(R.id.textbruto23)
        val bruto23:Double? = num3!! * num4!!
        precioBru23.text= bruto23?.let { redondear(it) }
        val precioContAbr:Double? = bruto21?.let { calculador(it,num5!!/100,num8!!/100) }
        contadoabr.text= precioContAbr?.let { redondear(it) }
        val precioContMay:Double? = bruto22?.let { calculador(it, num6!!/100,num9!!/100) }
        contadomay.text= precioContMay?.let { redondear(it) }
        val precioContJun:Double? = bruto23?.let { calculador(it,num7!!/100, num10!!/100) }
        contadojun.text= precioContJun?.let { redondear(it) }

        var abril30rec:Double? = bruto21?.let { mostrador(it,num5!! /100) }
        rec30abril.text= abril30rec?.let { redondear(it) }
        var mayo30rec:Double? = bruto22?.let { mostrador(it,num6!!/100) }
        rec30mayo.text= mayo30rec?.let { redondear(it) }
        var junio30rec:Double? = bruto23?.let { mostrador(it,num7!!/100) }
        rec30junio.text= junio30rec?.let { redondear(it) }
        var abril60rec:Double? = bruto21?.let { mostrador(it,num8!!/100) }
        rec60abril.text= abril60rec?.let { redondear(it) }
        var mayo60rec:Double? = bruto22?.let { mostrador(it,num9!!/100) }
        rec60mayo.text= mayo60rec?.let { redondear(it) }
        var junio60rec:Double? = bruto23?.let { mostrador(it,num10!!/100) }
        rec60junio.text= junio60rec?.let { redondear(it) }
        siguiente()

    }

    private fun siguiente (){
        generar_proyeccion_tabla.setOnClickListener {
            val arregloAbril = arrayOf(textAbril.text.toString(),textPreUni21.text.toString(),
                textbruto21.text.toString(),contadoabr.text.toString(),
                rec30abril.text.toString(),rec60abril.text.toString())
            val arregloMayo = arrayOf(textMayo.text.toString(),textPreUni22.text.toString(),
                textbruto22.text.toString(),contadomay.text.toString(),
                rec30mayo.text.toString(),rec60mayo.text.toString())
            val arregloJunio = arrayOf(textJunio.text.toString(),textPreUni23.text.toString(),
                textbruto23.text.toString(),contadojun.text.toString(),
                rec30junio.text.toString(),rec60junio.text.toString())
            val bundleAbril = Bundle()
            val bundleMayo = Bundle()
            val bundleJunio = Bundle()
            bundleAbril.putStringArray("keyValoresAbril", arregloAbril)
            bundleMayo.putStringArray("keyValoresMayo", arregloMayo)
            bundleJunio.putStringArray("keyValoresJunio", arregloJunio)
            val ventana = Intent(this, MainActivity3:: class.java)
            ventana.putExtras(bundleAbril)
            ventana.putExtras(bundleMayo)
            ventana.putExtras(bundleJunio)
            startActivity(ventana)
        }
    }

    private fun redondear(numero: Double ): String {
        val num = numero

        val df = DecimalFormat("#.##")
        df.roundingMode = RoundingMode.CEILING

        return df.format(num)
    }

    private fun calculador(a: Double,b: Double,c:Double):Double{
        var res=0.0
        if(b==0.0 && c==0.0){
            res=a;
        }else{
            if(b==0.0){
                res=a*(1-c)
            }else{
                res=a*(1-b)
            }
        }
        return res
    }

    private fun mostrador(a:Double, b: Double):Double{
        var res=0.0
        if(a==0.0){
            res=0.0
        }else{
            res=a*b
        }
        return res;
    }
}