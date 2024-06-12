import java.io.File

class bpiProc{

fun principal(): List<Any> {
    val file = File("src/main/kotlin/temp.csv")
    val listCsv = file.readLines()
    val mapCsv: MutableMap<String, List<Number>> = mutableMapOf()
    var tList: MutableList<String> = mutableListOf()
    for(i in 0..listCsv.size-1){
        tList = listCsv[i].split("/").toMutableList()
        if(!mapCsv.containsKey(tList[0])){
            mapCsv[tList[0]]= listOf(tList[1].toDouble(),tList[2].toDouble())
        }   else {
            mapCsv[tList[0]]= listOf(tList[1].toDouble()+mapCsv.getValue(tList[0])[0].toDouble(),tList[2].toDouble())
        }
        }
    var maiorLucro: Double = 0.0
    var maiorLucroDia: String = ""
    var maiorDespesa: Double = 0.0
    var maiorDespesaDia: String = ""
    var totalMes: Double = 0.0
    var mediaGastos: Double = 0.0
    for(i in mapCsv){
        if(i.value[0].toDouble() > maiorLucro){
            maiorLucro = i.value[0].toDouble()
            maiorLucroDia = i.key
        }
        if(i.value[0].toDouble() < maiorDespesa){
            maiorDespesa = i.value[0].toDouble()
            maiorDespesaDia = i.key
        }
        mediaGastos += i.value[0].toDouble()
        }
    mediaGastos = mediaGastos / listCsv.size
    totalMes=listCsv[listCsv.size-1].split("/")[2].toDouble()-listCsv[0].split("/")[2].toDouble()
    val listaBpi: List<Any> = listOf(maiorLucro,maiorLucroDia,maiorDespesa,maiorDespesaDia,totalMes,mediaGastos)
    return listaBpi
    }
}