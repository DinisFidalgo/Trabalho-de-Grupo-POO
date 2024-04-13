import java.io.File
data class FinancialGoal (
        val description: String,
        val value: Double,
        val startDate: String,
        val endDate: String
)



class FinancialGoalLoader {
        private val goals: MutableList<FinancialGoal> = mutableListOf()

        fun goalsLoader(file: String){
                File(file).forEachLine { line ->
                        val parts = line.split(",").map { it.trim() }
                        val description = parts[0]
                        val value = parts[1].toDouble()
                        val startDate = parts[2]
                        val endDate = parts[3]

                        val goal = FinancialGoal (description, value, startDate, endDate)
                }
        }

}

