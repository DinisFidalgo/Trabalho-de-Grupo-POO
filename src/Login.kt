import java.io.File
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
//    println("---Menu---")
//    println("1:Login   ")
//    println("2:Registar")
//    val decision = readln().toInt()
//    if(decision == 1){
//        println("---Menu---")
//        println("1:Username")
//        val login = readLine().toString()
//        println("2:Password")
//        val pass = readLine().toString()
//        if(Login(login,pass).Login() != "false"){
//            val list = Login(login,pass).Login().split(",")
//            println("---Menu---")
//            println("Saldo: ${list[2]}")
//            println("Numero de conta: ${list[3]}")
//        }
//    }
}