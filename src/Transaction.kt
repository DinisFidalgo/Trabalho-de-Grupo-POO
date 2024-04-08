
enum class TransactionType {
    INCOME, EXPENSE   // Representam os ganhos e os gastos, respetivamente
}
// Class que vai representar a transação
data class Transaction(
    // Atributos da transação, a data, o tipo, a categoria, a quantidade e uma nota opcional
    val date: String,  
    val type: TransactionType,
    val category: String,
    val amount: Double,
    val note: String? = null
)
