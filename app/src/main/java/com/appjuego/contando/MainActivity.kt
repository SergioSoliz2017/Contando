package com.appjuego.contando

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        siguiente()
    }

    private fun siguiente () {
        botonSiguiente.setOnClickListener{
            if(validar()){
                if(controlarPorcentaje()){
                    if(filtrador(recup60abril.text.toString().toDouble(),recup30abril.text.toString().toDouble())){
                        if(filtrador(recup60mayo.text.toString().toDouble(),recup30mayo.text.toString().toDouble())){
                            if(filtrador(recup60junio.text.toString().toDouble(),recup30junio.text.toString().toDouble())){
                                porfinlogrado()
                            }else{
                                Toast.makeText(applicationContext, "Solo ponga un porcentaje en junio", Toast.LENGTH_SHORT).show()
                            }
                        }else{
                            Toast.makeText(applicationContext, "Solo ponga un porcentaje en mayo", Toast.LENGTH_SHORT).show()
                        }
                    }else{
                        Toast.makeText(applicationContext, "Solo ponga un porcentaje en abril", Toast.LENGTH_SHORT).show()
                    }
                }else{
                    Toast.makeText(applicationContext, "Porcentajes incorrectos", Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(applicationContext, "Llene los campos", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun validar() :Boolean {
        var valido = true
        if(mesPrimer.text.isEmpty()){
            mesPrimer.setError("Llene el primer mes")
            valido = false
        }
        if(mesSegu.text.isEmpty()){
            mesSegu.setError("Llene el segundo mes")
            valido = false;
        }
        if(mesTerce.text.isEmpty()){
            mesTerce.setError("Llene el tercer mes")
        }
        if(campoPrecioUnitario.text.isEmpty()){
            campoPrecioUnitario.setError("Debe poner precio unitario")
            valido = false;
        }
        if(recup60abril.text.isEmpty()){
            recup60abril.setError("Este campo no debe estar vacio")
            valido = false;
        }
        if(recup60mayo.text.isEmpty()){
            recup60mayo.setError("Este campo no debe estar vacio")
            valido = false;
        }
        if(recup60junio.text.isEmpty()){
            recup60junio.setError("Este campo no debe estar vacio")
            valido = false;
        }
        if(recup30abril.text.isEmpty()){
            recup30abril.setError("Este campo no debe estar vacio")
            valido = false;
        }
        if(recup30mayo.text.isEmpty()){
            recup30mayo.setError("Este campo no debe estar vacio")
            valido = false;
        }
        if(recup30junio.text.isEmpty()){
            recup30junio.setError("Este campo no debe estar vacio")
            valido = false;
        }
        return valido
    }

    private fun controlarPorcentaje(): Boolean{
        var validado=true
        var num1: Double? = recup60abril.text.toString().toDouble()
        var num2: Double? = recup60mayo.text.toString().toDouble()
        var num3: Double? = recup60junio.text.toString().toDouble()
        var num4: Double? = recup30abril.text.toString().toDouble()
        var num5: Double? = recup30mayo.text.toString().toDouble()
        var num6: Double? = recup30junio.text.toString().toDouble()

        if(num1!! >100.0){
            recup60abril.setError("Porcentaje incorrecto")
            validado = false;
        }
        if(num2!!>100.0){
            recup60mayo.setError("Porcentaje incorrecto")
            validado = false;
        }
        if(num3!!>100.0){
            recup60junio.setError("Porcentaje incorrecto")
            validado = false;
        }
        if(num4!!>100.0){
            recup30abril.setError("Porcentaje incorrecto")
            validado = false;
        }
        if(num5!!>100.0){
            recup30mayo.setError("Porcentaje incorrecto")
            validado = false;
        }
        if(num6!!>100.0){
            recup30junio.setError("Porcentaje incorrecto")
            validado = false;
        }
        return validado
    }

    private fun filtrador(a: Double, b: Double): Boolean{
        var sePudo = true
        //if((a.equals(0)) && (b.equals(0)) || ((!a.equals(0)) && (!b.equals(0)))){
        //if((a==0.0 && b==0.0) || (a!=0.0 && b!=0.0)){
        if(a!=0.0 && b!=0.0){
            sePudo = false
        }
        return sePudo
    }

    private fun porfinlogrado(){
        val ventana = Intent(this, MainActivity2:: class.java)
        val mesprimero = mesPrimer.text.toString()
        val messegundo = mesSegu.text.toString()
        val mestercero = mesTerce.text.toString()
        val preciouni = campoPrecioUnitario.text.toString()
        val recup60abr = recup60abril.text.toString()
        val recup60may = recup60mayo.text.toString()
        val recup60jun = recup60junio.text.toString()
        val recup30abr = recup30abril.text.toString()
        val recup30may = recup30mayo.text.toString()
        val recup30jun = recup30junio.text.toString()
        var arreglo = arrayOf(mesprimero,messegundo,mestercero,preciouni,recup60abr,recup60may,recup60jun,recup30abr,recup30may,recup30jun)
        val bundle = Bundle()
        bundle.putStringArray("keyValores", arreglo)
        ventana.putExtras(bundle)
        startActivity(ventana)
    }

}