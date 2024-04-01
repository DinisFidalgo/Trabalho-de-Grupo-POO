//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {

    val expenseManager =  ExpenseManager()


    expenseManager.newTransaction("01/01/2023",TransactionType.EXPENSE,"Almoçarada",774.99,"Comi que nem uma besta!")
    expenseManager.newTransaction("01/01/2023",TransactionType.EXPENSE,"Sobre A Mesa",5.0,"Pensei que fosse mais caro")
    expenseManager.newTransaction("02/01/2023",TransactionType.INCOME,"Reembolso da Almoçarada",774.99, "Chamei o gregório e devolveram-me a guita")

    val allTransactions = expenseManager.getTransactions()

    allTransactions.forEach {
        println("${it.date} - ${it.type}: ${it.category} - ${it.amount} - ${it.note ?: "Sem Nota!"}")
    }


}