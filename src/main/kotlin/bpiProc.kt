import java.io.File

class csvData(val date: String, val value1: Double, val value2: Double)

class bpiProc(private val bpiFile: String){

    private val mapCSV: MutableMap<String, List<Number>> = mutableMapOf()

    fun readFileBPI(): List<String>{
        val file = File(bpiFile)
        return file.readLines()

    }

    fun ProcCsv(listCSV : List<String>){

        for(line in listCSV){
            val tList = line.split("/").toMutableList()
            val date = tList[0]
            val value1 = tList[1].toDouble()
            val value2 = tList[2].toDouble()

            if(!mapCSV.containsKey(date)){
                mapCSV[date] = listOf(value1, value2)
            }else{
                val existsValue = mapCSV.getValue(date)
                mapCSV[date] = listOf(value1 + existsValue[0].toDouble(), value2)
            }
        }

    }

    fun calculateMonths(): List<Any>{
        var maiorLucro = 0.0
        var maiorLucroDia = ""
        var maiorDespesa = 0.0
        var maiorDespesaDia = ""
        var totalMes = 0.0
        var mediaGasto = 0.0


        for((key, value) in mapCSV){
            val lucro = value[0].toDouble()

            if(lucro > maiorLucro){
                maiorLucro = lucro
                maiorLucroDia = key
            }
            if(lucro < maiorDespesa){
                maiorDespesa = lucro
                maiorDespesaDia = key
            }
            mediaGasto += lucro
        }

        mediaGasto /= mapCSV.size
        totalMes = mapCSV.values.last()[1].toDouble() - mapCSV.values.first()[1].toDouble()

        return listOf(maiorLucro,maiorLucroDia,maiorDespesa,maiorDespesaDia,totalMes,mediaGasto)

    }
fun principal(): List<Any>{

    val listCsv = readFileBPI()
    ProcCsv(listCsv)

    return calculateMonths()

}
}