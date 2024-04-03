//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {

    val expenseManager =  ExpenseManager()


    expenseManager.newTransaction("01/01/2023",TransactionType.EXPENSE,"Almoçarada",74.99,"Comi num restaurante fantástico!")
    expenseManager.newTransaction("01/01/2023",TransactionType.EXPENSE,"Sobremesa",5.0,"Pudim de ovos")
    expenseManager.newTransaction("02/01/2023",TransactionType.INCOME,"Abono Familiar",774.99, "O estado pagou-me o abono")

    val allTransactions = expenseManager.getTransactions()

    allTransactions.forEach {
        println("${it.date} - ${it.type}: ${it.category} - ${it.amount} - ${it.note ?: "Sem Nota!"}")
    }


}