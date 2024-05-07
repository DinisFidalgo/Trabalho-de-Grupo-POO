package org.example
import java.io.File
import java.io.FileWriter


class ExpenseManager {

    private val transactions : MutableList<Transaction> = mutableListOf()
    val dataBase = File("transactions.csv")
    val lines = dataBase.readLines().drop(1)

    //Função para que permite adicionar uma nova transação
    fun newTransaction(date: String, type: TransactionType, category: String, amount: Double, note: String? = null,numeroconta: Int) {
    // Criar e adicionar a transação à lista
        dataBase.createNewFile() //Criar caso não exista
        val dataBaseWriter = FileWriter(dataBase,true)
        // Tratamento de exceção no caso de ocorrer um erro
        try {
            dataBaseWriter.use{
                it.append("$date,${type.name},$category,$amount,${note ?: ""},$numeroconta,\n")
            }
            //transactions.add(Transaction(date, type, category, amount, note))
        } catch (e: Exception){
            println("Transação não adicionada \n Error: ${e.message} ")
        }
    }
    // Função para obter todas as transações efectuadas
    fun getTransactions(numeroconta: Int): MutableList<List<String>> {
        var listTransaction = mutableListOf<List<String>>()
        lines.forEach{ line ->
            val templist = line.split(",")
            if(templist.getOrNull(5)?.toIntOrNull() == numeroconta) {
                listTransaction.add(templist)
            }
        }
        return listTransaction

            }
    }
