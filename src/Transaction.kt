
enum class TransactionType {
    INCOME, EXPENSE
}

data class Transaction(
    val date: String,
    val type: TransactionType,
    val category: String,
    val amount: Double,
    val note: String? = null
)