import Transaction
import TransactionType



class ExpenseManager {

    private val transactions : MutableList<Transaction> = mutableListOf()

    fun newTransaction(date: String, type: TransactionType, category: String, amount: Double, note: String? = null) {
        transactions.add(Transaction(date, type, category, amount, note))
    }

    fun getTransactions(): List<Transaction> {
        return transactions.toList()
    }


}