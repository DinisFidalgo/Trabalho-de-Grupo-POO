import java.io.File

class BpiProc {
    private val mapCsv: MutableMap<String, List<Double>> = mutableMapOf()

    fun principal(): List<Any> {
        try {
            val file = File("src/main/kotlin/temp.csv")
            val listCsv = file.readLines()

            for (line in listCsv) {
                val tList = line.split("/")
                val date = tList[0]
                val value1 = tList[1].toDouble()
                val value2 = tList[2].toDouble()

                if (!mapCsv.containsKey(date)) {
                    mapCsv[date] = listOf(value1, value2)
                } else {
                    val existingValues = mapCsv.getValue(date)
                    mapCsv[date] = listOf(value1 + existingValues[0], value2)
                }
            }

            var maiorLucro = 0.0
            var maiorLucroDia = ""
            var maiorDespesa = 0.0
            var maiorDespesaDia = ""
            var totalMes = 0.0
            var mediaGastos = 0.0

            for ((key, value) in mapCsv) {
                val lucro = value[0]

                if (lucro > maiorLucro) {
                    maiorLucro = lucro
                    maiorLucroDia = key
                }
                if (lucro < maiorDespesa) {
                    maiorDespesa = lucro
                    maiorDespesaDia = key
                }
                mediaGastos += lucro
            }

            mediaGastos /= listCsv.size
            totalMes = listCsv.last().split("/")[2].toDouble() - listCsv.first().split("/")[2].toDouble()

            val listaBpi: List<Any> = listOf(maiorLucro, maiorLucroDia, maiorDespesa, maiorDespesaDia, totalMes, mediaGastos)
            return listaBpi

        } catch (e: Exception) {
            println("Erro ao processar o arquivo: ${e.message}")
            return emptyList()
        }
    }

}
