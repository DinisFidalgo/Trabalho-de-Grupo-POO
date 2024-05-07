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
        FileWriter(file, true).use { out ->
            out.append("${login},${password},${saldo},${Login().getNumeroConta()+1}\n")
        }
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