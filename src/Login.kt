import java.io.File
import java.io.FileWriter
import java.io.IOException

class Login() {
    val file = File("src/login.csv")
    val lines = file.readLines().drop(1)
    var verification = 0
    var line = ""

    fun login(login: String, pass: String): String {
        lines.forEach() {
            val templist = it.split(",")
            if (templist[0] == login && templist[1] == pass) {
                verification = verification + 1
                line = it
            } else {
                verification = verification + 0

            }
        }
        if (verification == 1) {
            return line
        } else {
            return "false"
        }
    }
    fun Register(login: String, password: String, saldo: Number) {
        val existingUsers = existingUsers()
        if (existingUsers.any {it.first == login}){
            println("Utilizador já existente. Crie um novo.")

            return
        }

        FileWriter(file, true).use { out ->
            out.append("${login},${password},${saldo},${Login().getNumeroConta()+1}\n")
        }
    }
    fun existingUsers () : List<Pair<String, String>> {
        val user = mutableListOf<Pair<String,String>>()
        val lines = file.readLines().drop(1)
        lines.forEach { line ->
            val parts = line.split(",")
            val login = parts[0]
            val pass = parts[1]
            user.add (login to pass)
        }
        return user
    }

    fun getNumeroConta(): Int{
        var numeroConta: Int = 0
        lines.forEach(){
            val templist = it.split(",")
            if (templist[3].toInt()>numeroConta){
                numeroConta = templist[3].toInt()
            }
        }
        return  numeroConta
    }
}