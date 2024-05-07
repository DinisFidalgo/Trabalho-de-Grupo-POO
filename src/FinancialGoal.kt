import java.io.File
import java.io.FileWriter

data class FinancialGoal (
        val description: String,
        val value: Double,
        val startDate: String,
        val endDate: String
)

class FinancialGoalLoader {
        private val goals: MutableList<FinancialGoal> = mutableListOf()
        val file = File("src/financialGoals.csv")
        val lines = file.readLines().drop(1)
        // csv loader to create and read the goals
        fun goalsLoader(file: String = "src/financialGoals.csv"){
                try {
                File(file).forEachLine { line ->
                        val parts = line.split(",").map { it.trim() }
                        val description = parts[0]
                        val value = parts.getOrNull(1)?.toDoubleOrNull() ?: 0.0
                        val startDate = parts[2]
                        val endDate = parts[3]

                        val goal = FinancialGoal (description, value, startDate, endDate)
                        goals.add(goal)
                }
        }catch (e: Exception){
                println("Loading Error: ${e.message}")
        }

        }
        fun newGoal(description: String, value: Double, startDate: String, endDate: String) {
                val goal = FinancialGoal(description, value, startDate, endDate)
                goals.add(goal)
        }

        fun saveGoals(file: String = "src/financialGoals.csv") {
                try {
                        val goalWrite = File(file)
                        val data = goals.map { listOf(it.description, it.value.toString(), it.startDate, it.endDate) }
                        goalWrite.bufferedWriter().use { out ->
                                data.forEach { row ->
                                        out.write(row.joinToString(","))
                                        out.newLine()
                                }
                        }
                }catch (e: Exception){
                        println("Saving Error: ${e.message}")
                }
        }

        fun obtainGoals():List<FinancialGoal>{
                return goals.toList()
        }

        fun getGoals(numeroConta: Int): MutableList<List<String>> {
                var lista = mutableListOf<List<String>>()
                lines.forEach{
                        val templist = it.split(",")
                        val templist2 = mutableListOf<String>()
                        if (templist[3].toInt() == numeroConta) {
                                templist2.add(it)
                                lista.addFirst(templist2)
                        }
                        templist2.drop(0)
                }
                return lista
        }

        fun createGoal(descricao:String,custo:Number,diasParaCompletar:Int,numeroConta: Int){
                FileWriter(file, true).use { out ->
                        out.append("${descricao},${custo},${diasParaCompletar},${numeroConta}")
                }
        }
}

