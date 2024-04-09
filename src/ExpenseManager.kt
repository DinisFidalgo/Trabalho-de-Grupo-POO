package org.example


class ExpenseManager {

    private val transactions : MutableList<Transaction> = mutableListOf()
    //Função para que permite adicionar uma nova transação
    fun newTransaction(date: String, type: TransactionType, category: String, amount: Double, note: String? = null) {
    // Criar e adicionar a transação à lista
        transactions.add(Transaction(date, type, category, amount, note))
    }
    // Função para obter todas as transações efectuadas
    fun getTransactions(): List<Transaction> {
        return transactions.toList()
    }


}
