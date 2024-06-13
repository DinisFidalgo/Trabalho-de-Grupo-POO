fun main() {
        println("----Menu----")
        println("Escolha o/s Banco/s para gerar um relatório")
        println("1. BPI")
        println("2. CTT")
        println("3. BPI e CTT")

        val escolhaMenu = readLine()?.toIntOrNull() ?: run {
                println("Opção inválida")
                return
        }

        val processor = Menu()
        processor.processMain(escolhaMenu)

        println("O processo foi concluido, pode verificar os movimentos da sua conta nos resultados.")
}
