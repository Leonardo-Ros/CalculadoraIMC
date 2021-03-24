package com.leo.calculadoradeimc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.leo.calculadoradeimc.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bt_calcular = binding.btCalc
        val mensagem = binding.infpeso

        bt_calcular.setOnClickListener{

            val editPeso= binding.editPeso.text.toString()
            val editAltura=binding.editAltura.text.toString()

            if(editPeso.isEmpty()){
                mensagem.setText("Informe seu peso")
            }else if (editAltura.isEmpty()){
                mensagem.setText("Informe sua Altura")
            }else{
                CalculodeImc()
            }

        }
    }

    private fun CalculodeImc(){

        val pesoId = binding.editPeso
        val alturaId = binding.editAltura

        val peso= Integer.parseInt(pesoId.text.toString())
        val altura= java.lang.Float.parseFloat(alturaId.text.toString())
        val resultado=binding.infpeso
        val imc = peso / (altura*altura)

        val Mensagem = when{
            imc <= 18.5 -> "Peso Baixo"
            imc <= 24.9 -> "Peso Normal"
            imc <= 29.9 -> "Sobrepeso"
            imc <= 34.9 -> "Obesidade (Grau I)"
            imc <= 39.9 -> "Obesidade (Grau II)"
            else -> "Obesidade Morbida( Grau III)"

        }

        imc.toString()
        resultado.setText("IMC:$imc \n $Mensagem")
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflate= menuInflater
        inflate.inflate(R.menu.menu_principal,menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.reset ->{
                val limparEditPeso = binding.editPeso
                val limparEditAltura=binding.editAltura
                val limparMensagem=binding.infpeso

                limparEditAltura.setText("")
                limparEditPeso.setText("")
                limparMensagem.setText("")
            }
        }
        return super.onOptionsItemSelected(item)
    }
}