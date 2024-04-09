import Login
import org.example.ExpenseManager
import org.example.TransactionType

fun main() {

    println("---Menu---")
    println("1:Login   ")
    println("2:Registar")
    val decision = readln().toInt()
    if(decision == 1){
        println("---Menu---")
        println("1:Username")
        val login = readLine().toString()
        println("2:Password")
        val pass = readLine().toString()
        if(Login(login,pass).Login() != "false"){
            val list = Login(login,pass).Login().split(",")
            println("---Menu---")
            println("Saldo: ${list[2]}")
            println("Numero de conta: ${list[3]}")
        }
    }

    if(decision == 2){
        val expenseManager =  ExpenseManager()
        expenseManager.newTransaction("01/01/2023",TransactionType.EXPENSE,"Almoçarada",74.99,"Comi num restaurante fantástico!")
        expenseManager.newTransaction("01/01/2023",TransactionType.EXPENSE,"Sobremesa",5.0,"Pudim de ovos")
        expenseManager.newTransaction("02/01/2023",TransactionType.INCOME,"Abono Familiar",774.99, "O estado entregou o abono familiar correspondente ao José e à Ana")
        expenseManager.newTransaction("03/01/2023",TransactionType.INCOME,"Despesa Automovel",278.75,)

        val allTransactions = expenseManager.getTransactions()

        allTransactions.forEach {
            println("${it.date} - ${it.type}: ${it.category} - ${it.amount} - ${it.note ?: "Sem Notas adicionais!"}")
        }
    }
}