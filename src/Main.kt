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
        println("Selecione uma opção")

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
            3 -> println("Aplication Closed")
            else -> println("Invalid option")
            
            }
        }

    }


    //val decision = readln().toInt()
    //if(decision == 1){
    //    println("---Menu---")
//        println("1:Username")
//        val login = readLine().toString()
//        println("2:Password")
//        val pass = readLine().toString()
//        if(Login(login,pass).Login() != "false"){
//            val list = Login(login,pass).Login().split(",")
//            println("---Menu---")
//            println("Saldo: ${list[2]}")
//            println("Numero de conta: ${list[3]}")
//        }
//    }

//    if(decision == 2){
//        println("---Menu---")
//        println("1:Username")
//        val login = readLine().toString()
//        println("2:Password")
//        val password = readLine().toString()
//        println("3:Balance")
//        val balance = readLine()?.toDoubleOrNull() ?: 0.0
//        println("4:Account Number")
//        val accountNumber = readLine()?.toInt() ?: 0
//
//        val registration = Login(login,password).register(balance,accountNumber)
//
//        if (registration){
//            println("New Registered User: ${login} ")
//        }else{
//            println("Error Registering User")
 //       }


       // expenseManager.newTransaction("01/01/2023",TransactionType.EXPENSE,"Almoçarada",74.99,"Comi num restaurante fantástico!")
        //expenseManager.newTransaction("01/01/2023",TransactionType.EXPENSE,"Sobremesa",5.0,"Pudim de ovos")
        //expenseManager.newTransaction("02/01/2023",TransactionType.INCOME,"Abono Familiar",774.99, "O estado entregou o abono familiar correspondente ao José e à Ana")
        //expenseManager.newTransaction("03/01/2023",TransactionType.INCOME,"Despesa Automovel",278.75,)



       // allTransactions.forEach {
        //    println("${it.date} - ${it.type}: ${it.category} - ${it.amount} - ${it.note ?: "Sem Notas adicionais!"}")
       // }


       // goalsManager.newGoal("teste1", 3.0, "27/08/1990", "27/08/2016")
        //goalsManager.newGoal("Comprar Carro",4500.0,"2024","2026")
        //goalsManager.goalsLoader()

        //goalsManager.saveGoals()

        //val allGoals = goalsManager.obtainGoals()

        //allGoals.forEach { println("Objetivo: ${it.description} - Valor a Alcançar: ${it.value} - Data de inicio: ${it.startDate} - Data final:${it.endDate}") }
    //}
//}
}
