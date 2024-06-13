import java.io.File

class CsvCTTData(val date: String, val value1: Double, val value2: Double)

class cttproc (private val pdfCTT: String){

    private val mapCsvCTT : MutableMap<String, List<Number>> = mutableMapOf()

    fun readCTT(): List<String>{

        val file = File(pdfCTT)
        return file.readLines()
    }

    fun procCTTcsv (listCsv : List<String>){

        for (line in listCsv){
            val tList = line.split("/").toMutableList()
            val date = tList[0]
            val value1 = tList[1].toDouble()
            val value2 = tList[2].toDouble()


            if(!mapCsvCTT.containsKey(date)){
                mapCsvCTT[date] = listOf(value1,value2)
                }
                else{
                    val existValue = mapCsvCTT.getValue(date)
                    mapCsvCTT[date] = listOf(value1 + existValue[0].toDouble(), value2)

            }
        }

    }

    fun calculateCTTMonths(): List<Any>{
        var maiorLucro = 0.0
        var maiorLucroDia = ""
        var maiorDespesa = 0.0
        var maiorDespesaDia = ""
        var totalMes = 0.0
        var mediaGasto = 0.0


        for((key, value) in mapCsvCTT){
            val lucro = value[0].toDouble()

            if(lucro > maiorLucro){
                maiorLucro = lucro
                maiorLucroDia = key
            }

            if (lucro < maiorDespesa){
                maiorDespesa = lucro
                maiorDespesaDia = key
            }

            mediaGasto += lucro
        }

        mediaGasto /= mapCsvCTT.size

        totalMes = mapCsvCTT.values.last()[1].toDouble() - mapCsvCTT.values.first()[1].toDouble()

        return listOf(maiorLucro,maiorLucroDia,maiorDespesa,maiorDespesaDia,totalMes,mediaGasto)
    }

    fun principal(): List<Any> {
        val listCsvCTT = readCTT()
        procCTTcsv(listCsvCTT)

        return calculateCTTMonths()
    }
}
