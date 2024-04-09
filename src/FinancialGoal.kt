import java.io.File
class FinancialGoal {

        private val goals: MutableList<FinancialGoal> = mutableListOf()

        fun goalsLoader(){

                        File("src/financialGoals.csv").forEachLine { line ->
                                val parts = line.split(",").map { it.trim() }
                                val description = parts[0]
                                val value = parts[1]
                                val startDate = parts[2]
                                val endDate = parts[3]

                        }


        }

        fun newFinancialGoal (){}



}

