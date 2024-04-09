import java.io.File
class FinancialGoal {

        private val goals: MutableList<FinancialGoal> = mutableListOf()

        init {
                File("C:\\Users\\Laptop\\Desktop\\GitHub grupo\\Trabalho-de-Grupo-POO\\src\\finalcialGoals.csv").forEachLine { line ->
                        val parts = line.split(",").map { it.trim() }
                        val description = parts[0]
                        val value = parts[1]
                        val startDate = parts[2]
                        val endDate = parts[3]

                }

        }




}