import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

data class Transaction(val amount: Int, val operationType: String, val transactionCategory: String, val timeStamp: LocalDateTime)
// creating a class
class FinanceTracker {
    private val transactionLog = mutableListOf<Transaction>()
    private val supportedCategories = listOf("Food", "Transport", "Entertainment")
// creating class values
    fun recordTransaction(transaction: Transaction) {
        transactionLog.add(transaction)
        println("${transaction.operationType} of ${transaction.amount} added under ${transaction.transactionCategory} category.")
    }
// function for deleting last transaction
    fun revertLastTransaction() {
        if (transactionLog.isNotEmpty()) {
            transactionLog.removeLast()
            println("Reversed the most recent transaction.")
        } else {
            println("Transaction log is empty.")
        }
    }
// function showing the transactions
    fun displayTransactions(filterCategory: String? = null) {
        val relevantTransactions = transactionLog.filter { filterCategory == null  it.transactionCategory == filterCategory }
        if (relevantTransactions.isEmpty()) {
            println("No matching transactions.")
            return
        }
        relevantTransactions.forEach { transaction ->
            println("Amount: ${transaction.amount}, Operation: ${transaction.operationType}, Category: ${transaction.transactionCategory}, Date: ${transaction.timeStamp.format(DateTimeFormatter.ISO_DATE)}")
        }
    }
// calculating function
    fun calculateBalance(): Int = transactionLog.sumOf { if (it.operationType == "Income") it.amount else -it.amount }
// category choosing function
    fun showCategories() {
        println("Categories:")
        supportedCategories.forEach { println(it) }
    }
// check if category is in categories
    fun isCategoryValid(category: String): Boolean = supportedCategories.contains(category)
}
// main function for calculator
fun main() {
    val tracker = FinanceTracker()
    var isActive = true
// while this menu is opened we can write in console
    while (isActive) {
        println("Current Balance: ${tracker.calculateBalance()}")
        println("""
            Options:
            1 - Log Expense
            2 - Log Income
            3 - Reverse Last Transaction
            4 - Transaction Log
            0 - Close
        """.trimIndent())
        /* variables that can be chosen */
        when (readLine()) {
            "0" -> isActive = false
            "1", "2" -> processTransaction(isExpense = it == "1", tracker)
            "3" -> tracker.revertLastTransaction()
            "4" -> tracker.displayTransactions()
            else -> println("Invalid option")
        }
        println("*** *** ***\n")
    }
}
// check if amount is set correctly
fun processTransaction(isExpense: Boolean, tracker: FinanceTracker) {
    println("Transaction Amount:")
    val amount = readLine()?.toIntOrNull()
    if (amount == null or amount <= 0) {
        println("Amount is not valid.")
        return
    }
// checking categories to be validated
    tracker.showCategories()
    println("Choose a category (type the name):")
    val category = readLine()
    if (category == null || !tracker.isCategoryValid(category)) {
        println("Category is not recognized.")
        return
    }

    val transactionType = if (isExpense) "Expense" else "Income"
    tracker.recordTransaction(Transaction(amount, transactionType, category, LocalDateTime.now()))
}
