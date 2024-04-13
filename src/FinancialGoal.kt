import java.io.File
data class FinancialGoal (
        val description: String,
        val value: Double,
        val startDate: String,
        val endDate: String
)

class FinancialGoalLoader {
        private val goals: MutableList<FinancialGoal> = mutableListOf()

                // csv loader to create and read the goals
        fun goalsLoader(file: String = "src/financialGoals.csv"){
                File(file).forEachLine { line ->
                        val parts = line.split(",").map { it.trim() }
                        val description = parts[0]
                        val value = parts.getOrNull(1)?.toDoubleOrNull() ?: 0.0
                        val startDate = parts[2]
                        val endDate = parts[3]

                        val goal = FinancialGoal (description, value, startDate, endDate)
                        goals.add(goal)
                }
        }
        fun newGoal(description: String, value: Double, startDate: String, endDate: String) {
                val goal = FinancialGoal(description, value, startDate, endDate)
                goals.add(goal)
        }

        fun obtainGoals():List<FinancialGoal>{
                return goals.toList()
        }
}

