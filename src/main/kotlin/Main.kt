package org.example
import ReportOpenerCTT
import bpiProc
import cttproc
import org.apache.pdfbox.Loader
import org.apache.pdfbox.pdmodel.PDDocument
import org.apache.pdfbox.pdmodel.PDPageContentStream
import org.apache.pdfbox.pdmodel.font.PDType1Font
import org.apache.pdfbox.pdmodel.font.Standard14Fonts
import org.apache.pdfbox.text.PDFTextStripper
import java.io.File
import java.io.FileOutputStream
import java.io.FileWriter
import java.util.regex.Matcher
import java.util.regex.Pattern
import kotlin.math.round


fun main() {
    var escolhaMenu: Int = 0
    println("1.BPI")
    println("2.CTT")
    println("3.BPI e CTT")
    escolhaMenu = readln().toInt()

    // PDF file
    var list: MutableList<Int> = mutableListOf()
    var list2: MutableList<Int> = mutableListOf()
    val fileName = File("src/main/BPI.pdf")
    val doc: PDDocument = Loader.loadPDF(fileName)
    val stripper = PDFTextStripper()
    val text = stripper.getText(doc)
    val fileCsv: File = File("src/main/kotlin/temp.csv")

    // StringBuilder para guardar o texto extraido
    val sb = StringBuilder()
    val sb2 = StringBuilder()


    // adicionar o text ao StringBuilder do PDF
    sb.append(stripper.getText(doc))
    sb2.append(stripper.getText(doc ))

    // Regex-> Vai defenir o padrao de que estamos a procura neste caso uma data dd-MM-yyyy
    val p: Pattern = Pattern.compile("\\d{2}-\\d{2}-\\d{4}")
    // Regex-> Vai defenir o padrao de que estamos a procura neste caso um numero x,xx
    val p2: Pattern = Pattern.compile("\\d{1},\\d{2}")


    // Encontra o primeiro index do que estamos a procura
    val m: Matcher = p.matcher(sb)
    while (m.find()) {
        //adiciona a uma lista todos as posicoes inicias de uma data encontradas num documento
        list.add(m.start())
    }
    // PDF comeca com uma data nao importante por isso temos que remover primeira localizacao de uma data, alem disto para extratos do banco a data e repetida 2 vezes em uma linha, para bom funcionamento do programa vamos ignorar e retirar da lista essas datas
    list.removeFirst()
    fun <T> removeValuesAtEvenPositions(list: List<T>): List<T> {
        return list.filterIndexed { index, _ -> (index + 1) % 2 != 0 }
    }
    fun <T> removeValuesAtOddPositions(list: List<T>): List<T> {
        return list.filterIndexed { index, _ -> (index + 1) % 2 == 0 }
    }
    fun containsLetters(input: String): Boolean {
        return input.any{ char -> char.isLetter() }
    }
    fun removeLetters(input: String): String {
        return input.filter { !it.isLetter() }
    }
    list = removeValuesAtEvenPositions(list).toMutableList()

    if (doc != null) {
        doc.close()
    }

    val m2: Matcher = p2.matcher(sb2)
    while (m2.find()) {
        //adiciona a uma lista todos as posicoes inicias de uma data encontradas num documento
        list2.add(m2.start())
    }
    //todos os filtros necessarios para que todas as posicoes inuteis do extrato do CTT sejam apagadas
    list2.removeFirst()
    list2.removeFirst()
    list2.removeFirst()
    list2.removeFirst()
    list2 = removeValuesAtOddPositions(list2).toMutableList()
    list2 = list2.map { it + 3 }.toMutableList()
    var tempNumber:Int = list.size - 1
    //verificar se o ficheiro csv tem algo escrito, se tiver apagar tudo
    if(fileCsv.readText().isNotEmpty()){
        FileOutputStream(fileCsv).close()
    }
    //cria uma string atraves da posicao inicial e final presentes em cada lista, trasforma em lista e escreve para o ficheiro csv os numeros extraidos de cada linha do extrato bancario
    while (tempNumber != -1){
        var tempList: MutableList<String> = mutableListOf()
        tempList = text.substring(list[tempNumber],list2[tempNumber]).replace("\r\n","").split(" ").toMutableList()
        if (containsLetters(tempList[tempList.size-2])){
            tempList[tempList.size-2] = removeLetters(tempList[tempList.size-2])
        }
        FileWriter(fileCsv, true).use { out ->
            out.append("${tempList[0]}/${tempList[tempList.size-2].replace(".","").replace(",",".")}/${tempList[tempList.size-1].replace(".","").replace(",",".")}\n")
        }
        list.removeLast()
        list2.removeLast()
        tempNumber = tempNumber - 1
    }
    ReportOpenerCTT("src/main/ctt.pdf").readCTT()
    ReportOpenerCTT("src/main/ctt.pdf").datesCTT()
    ReportOpenerCTT("src/main/ctt.pdf").locateNumber()
    ReportOpenerCTT("src/main/ctt.pdf").extractCTT()
    val listaBpi: List<Any> = bpiProc().principal()
    val listaCtt: List<Any> = cttproc().principal()
    if(escolhaMenu == 1){
        val file: File = File("src/main/resultado.pdf")
        val doc = Loader.loadPDF(file)
        val contentStream: PDPageContentStream = PDPageContentStream(doc, doc.getPage(0))
        contentStream.beginText()
        contentStream.setFont(PDType1Font(Standard14Fonts.FontName.TIMES_ROMAN), 12F);
        contentStream.setLeading(14.5f)
        contentStream.newLineAtOffset(25F, 725F)
        contentStream.showText("---------------------------BPI---------------------------")
        contentStream.newLine()
        contentStream.showText("Maior lucro: ${listaBpi[0]} no dia ${listaBpi[1]}")
        contentStream.newLine()
        contentStream.showText("Maior gasto: ${listaBpi[2]} no dia ${listaBpi[3]}")
        contentStream.newLine()
        contentStream.showText("Total Mes: ${listaBpi[4]}")
        contentStream.newLine()
        contentStream.showText("Media Gastos: ${listaBpi[5]}")
        contentStream.endText()
        contentStream.close()
        doc.save("src/main/resultado.pdf")
        doc.close()
    }
    if(escolhaMenu == 2){
        val file: File = File("src/main/resultado.pdf")
        val doc = Loader.loadPDF(file)
        val contentStream: PDPageContentStream = PDPageContentStream(doc, doc.getPage(0))
        contentStream.beginText()
        contentStream.setFont(PDType1Font(Standard14Fonts.FontName.TIMES_ROMAN), 12F);
        contentStream.setLeading(14.5f)
        contentStream.newLineAtOffset(25F, 725F)
        contentStream.showText("---------------------------CTT---------------------------")
        contentStream.newLine()
        contentStream.showText("Maior lucro: ${listaCtt[0]} no dia ${listaCtt[1]}")
        contentStream.newLine()
        contentStream.showText("Maior gasto: ${listaCtt[2]} no dia ${listaBpi[3]}")
        contentStream.newLine()
        contentStream.showText("Total Mes: ${listaCtt[4]}")
        contentStream.newLine()
        contentStream.showText("Media Gastos: ${listaCtt[5]}")
        contentStream.endText()
        contentStream.close()
        doc.save("src/main/resultado.pdf")
        doc.close()
    }
    if(escolhaMenu==3){
        val file: File = File("src/main/resultado.pdf")
        val doc = Loader.loadPDF(file)
        val contentStream: PDPageContentStream = PDPageContentStream(doc, doc.getPage(0))
        contentStream.beginText()
        contentStream.setFont(PDType1Font(Standard14Fonts.FontName.TIMES_ROMAN), 12F);
        contentStream.setLeading(14.5f)
        contentStream.newLineAtOffset(25F, 725F)
        contentStream.showText("---------------------------BPi---------------------------")
        contentStream.newLine()
        contentStream.showText("Maior lucro: ${listaCtt[0]} no dia ${listaCtt[1]}")
        contentStream.newLine()
        contentStream.showText("Maior gasto: ${listaCtt[2]} no dia ${listaBpi[3]}")
        contentStream.newLine()
        contentStream.showText("Total Mes: ${listaCtt[4]}")
        contentStream.newLine()
        contentStream.showText("Media Gastos: ${listaCtt[5]}")
        contentStream.newLine()
        contentStream.showText("---------------------------CTT---------------------------")
        contentStream.newLine()
        contentStream.showText("Maior lucro: ${listaBpi[0]} no dia ${listaBpi[1]}")
        contentStream.newLine()
        contentStream.showText("Maior gasto: ${listaBpi[2]} no dia ${listaBpi[3]}")
        contentStream.newLine()
        contentStream.showText("Total Mes: ${listaBpi[4]}")
        contentStream.newLine()
        contentStream.showText("Media Gastos: ${listaBpi[5]}")
        contentStream.endText()
        contentStream.close()
        doc.save("src/main/resultado.pdf")
        doc.close()
    }
}

