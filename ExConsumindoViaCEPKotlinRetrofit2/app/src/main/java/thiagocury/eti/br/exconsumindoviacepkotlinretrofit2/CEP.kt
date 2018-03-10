package thiagocury.eti.br.exconsumindoviacepkotlinretrofit2

/**
 * Created by thiagocury on 10/03/2018.
 */
class CEP{

    var cep: String? = ""
    var logradouro: String? = ""
    var complemento: String? = ""
    var bairro: String? = ""
    var localidade: String? = ""
    var uf: String? = ""
    var unidade: String? = ""
    var ibge: String? = ""
    var gia: String? = ""

    override fun toString(): String {
        return "cep $cep logradouro $logradouro complemento $complemento bairro $bairro localidade $localidade uf $uf unidade $unidade ibge $ibge gia $gia"
    }

}

