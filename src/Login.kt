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
}