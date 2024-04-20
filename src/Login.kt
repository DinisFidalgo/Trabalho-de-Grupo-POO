import java.io.File
import java.io.FileWriter
import java.io.IOException

class Login(var login: String, var pass: String) {

    val file = File("src/Login.csv")
    val lines = file.readLines().drop(1)
    var verification = 0
    var line = ""

    fun Login(): String {
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
    fun register(balance: Double, accountNumber: Int ): Boolean{

        val newUser = "$login,$pass,$balance,$accountNumber"

        return try {

            FileWriter(file, true).use {writer ->
                writer.write("\n$newUser")
                writer.flush()
                true
            }
        }catch (e: IOException){
            println("Registration error: ${e.message}")
            false
        }
    }
}