import Login
import org.example.ExpenseManager
import org.example.TransactionType

fun main() {
    val expenseManager =  ExpenseManager()
    val allTransactions = expenseManager.getTransactions()
    val goalsManager = FinancialGoalLoader()
    var logged = false

    println("---Menu---")
    println("1:Login   ")
    println("2:Registar")
    println("3:Sair")
    println("Selecione uma opção: ")

    var option = readln().toInt()

//    while (option != 3){

        when (option) {
            1 -> {
                println("---Menu---");
                println("1:Username")
                val login = readLine().toString()
                println("2:Password")
                val pass = readLine().toString()
                if(Login().login(login,pass) != "false"){
                    val list = Login().login(login,pass).split(",")
                    println("---Menu---")
                    println("Saldo: ${list[2]}")
                    println("Numero de conta: ${list[3]}")
                    println("1.Criar Objetivo")
                    println("1.Verificar Objetivo")
                    println("3.Sair")
                    var option3 = readln().toInt()
                    when (option3){
                        1->{
                            println("Nome do objetivo:")
                            val nome = readLine().toString()
                            println("Custo:")
                            val custo = readln().toInt()
                            println("Dias para completar o objetivo:")
                            val dias = readln().toInt()
                            FinancialGoalLoader().createGoal(nome,custo,dias,list[3].toInt())
                        }
                        2->{
                            println(FinancialGoalLoader().getGoals(list[3].toInt()).forEach{println(it[0].dropLast(2))})
                        }
                        3->{"A sair"}
                        else->{println("erro")}
                    }

                }
            }
            2 -> {
                println("---Menu---")
                println("Introduza o seu username:")
                val login = readLine().toString()
                println("Introduza a sua password:")
                val pass = readLine().toString()
                println("Confirme a sua password:")
                val pass2 = readLine().toString()
                if(pass2 != pass){
                    println("erro")
                }   else {
                    println("Introduza o seu saldo:")
                    val saldo = readln().toInt()
                    Login().Register(login,pass,saldo)
                }
            }
        }

//    }
//
//    var option2 = 0
//
//    while (option2 != 3){
//
//        println("---Menu---")
//        println("1 - Adicionar Transação ")
//        println("2 - Consultar Transações ")
//        println("3 - Sair ")
//
//        option2 = readln().toInt()
//
//        when (option2) {
//            1 -> {
//                println("Nova Transação")
//                println("Data (DD/MM/AAAA): ")
//
//                val date = readln().toString()
//
//                println("Tipo (INCOME/EXPENSE): ")
//
//                val transactionType = TransactionType.valueOf(readln().toString().toUpperCase())
//
//                println("Categoria: ")
//
//                val transactionCategory = readln().toString()
//
//                println("Valor: ")
//
//                val transactionAmount = readln().toDouble()
//
//                println("Nota Adicional (Opcional): ")
//
//                val note = readln().toString()
//
//                expenseManager.newTransaction(date,transactionType,transactionCategory,transactionAmount,note)
//
//                println("A sua transação foi adicionada com sucesso!")
//            }
//            2 -> {
//                println("Estas são todas as suas transações:")
//
//                val transactions = expenseManager.getTransactions()
//                if (transactions.isEmpty()){
//                    println("Ainda não efectuou nenhuma transação")
//                } else {
//                    transactions.forEachIndexed { index, transaction ->
//                        println("${index + 1}. $transaction")
//                    }
//                }
//            }
//            3 -> println("Aplicação encerrada")
//            else -> println("A opção introduzida é inválida! Insira uma nova opção.")
//        }
//    }
}