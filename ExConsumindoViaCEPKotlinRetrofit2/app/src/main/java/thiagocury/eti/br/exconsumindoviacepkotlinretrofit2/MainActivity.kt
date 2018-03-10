package thiagocury.eti.br.exconsumindoviacepkotlinretrofit2

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Spinner
import org.jetbrains.anko.toast


class MainActivity : AppCompatActivity() {

    //Widgets
    lateinit var etCEP: EditText
    lateinit var btnBuscarPorCEP: Button
    lateinit var progressBar: ProgressBar

    lateinit var etRua: EditText
    lateinit var etCidade: EditText
    lateinit var spUFs: Spinner
    lateinit var btnBuscarPorRuaCidadeEstado: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Refs.
        etCEP = findViewById(R.id.et_cep)
        btnBuscarPorCEP = findViewById(R.id.btn_buscar_por_cep)
        progressBar = findViewById(R.id.progress_bar)
        etRua = findViewById(R.id.et_rua)
        etCidade = findViewById(R.id.et_cidade)
        spUFs = findViewById(R.id.sp_ufs)
        btnBuscarPorRuaCidadeEstado = findViewById(R.id.btn_buscar_por_rua_cidade_estado)

        /* Buscar por CEP */
        btnBuscarPorCEP.setOnClickListener {

            progressBar.visibility = View.VISIBLE
            val call = RetrofitInitializer().apiRetrofitService().getEnderecoByCEP(etCEP.text.toString())

            call.enqueue(object : Callback<CEP> {

                override fun onResponse(call: Call<CEP>, response: Response<CEP>) {

                    response?.let {

                        val CEPs: CEP? = it.body()

                        Log.i("CEP", CEPs.toString())
                        toast("CEP: "+CEPs.toString())

                        progressBar.visibility = View.INVISIBLE

                    }

                }

                override fun onFailure(call: Call<CEP>?, t: Throwable?) {
                    Log.e("Erro", t?.message)
                    progressBar.visibility = View.INVISIBLE
                }

            })
        }

        /* Busca por Rua, Cidade e Estado(UF) */
        btnBuscarPorRuaCidadeEstado.setOnClickListener {

            progressBar.visibility = View.VISIBLE
            val call = RetrofitInitializer().apiRetrofitService().getCEPByCidadeEstadoEndereco(spUFs.selectedItem.toString(),
                                                                                               etCidade.text.toString(),
                                                                                               etRua.text.toString())

            call.enqueue(object :  Callback<List<CEP>> {

                override fun onResponse(call: Call<List<CEP>>?, response: Response<List<CEP>>?) {

                    response?.let {

                        val CEPs: List<CEP>? = it.body()

                        Log.i("CEP", CEPs.toString())
                        toast("CEP: "+CEPs.toString())

                        progressBar.visibility = View.INVISIBLE

                    }
                }

                override fun onFailure(call: Call<List<CEP>>?, t: Throwable?) {
                    Log.e("Erro", t?.message)
                    progressBar.visibility = View.INVISIBLE
                }
            })

        }

    }
}
