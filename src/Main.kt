import Login
import org.example.ExpenseManager
import org.example.TransactionType

fun main() {
    val expenseManager =  ExpenseManager()
    val allTransactions = expenseManager.getTransactions()
    val goalsManager = FinancialGoalLoader()

    var logged = false

    while (!logged){

        println("---Menu---")
        println("1:Login   ")
        println("2:Registar")
        println("3:Sair")
        println("Selecione uma opção: ")

        val option = readln().toInt()

        when (option) {
            1 -> {
                println("Login")
                val user = readln().toString()
                val pass = readln().toString()
                logged = Login(user,pass).login()
            }
            2 -> {
                println("Registar")
                val user = readln().toString()
                val pass = readln().toString()
                val balance = 0.0
                val accontNumber = readln()?.toInt() ?: 0
                val registration = Login(user, pass).register(balance, accontNumber)
                if (registration) {
                    println("New Registered User: ${user} ")
                } else {
                    println("Error Registering User")
                }}
            3 -> println("Application Closed")
            else -> println("Invalid option")

            }
        }

    var option = 0

    while (option != 3){

        println("---Menu---")
        println("1 - Adicionar Transação ")
        println("2 - Consultar Transações ")
        println("3 - Sair ")

        option = readln().toInt()

        when (option) {
            1 -> {
                println("Nova Transação")
                println("Data (DD/MM/AAAA): ")

                val date = readln().toString()

                println("Tipo (INCOME/EXPENSE): ")

                val transactionType = TransactionType.valueOf(readln().toString().toUpperCase())

                println("Categoria: ")

                val transactionCategory = readln().toString()

                println("Valor: ")

                val transactionAmount = readln().toDouble()

                println("Nota Adicional (Opcional): ")

                val note = readln().toString()

                expenseManager.newTransaction(date,transactionType,transactionCategory,transactionAmount,note)

                println("Transação adicionada com sucesso!")
            }
            2 -> {
                println("Estas são todas as transações:")

                val transactions = expenseManager.getTransactions()
                if (transactions.isEmpty()){
                    println("Ainda não foi efectuada nenhuma transação")
                } else {
                    transactions.forEachIndexed { index, transaction ->
                        println("${index + 1}. $transaction")
                    }
                }
            }
            3 -> println("Aplicação encerrada")
            else -> println("A opção introduzida é inválida! Insira uma nova opção.")
        }
    }
}