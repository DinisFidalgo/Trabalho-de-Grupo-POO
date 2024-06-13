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

class MainProcessor {

    fun processMain(escolhaMenu: Int) {
        var list: MutableList<Int> = mutableListOf()
        var list2: MutableList<Int> = mutableListOf()
        val fileName = File("src/main/BPI.pdf")
        val doc: PDDocument = Loader.loadPDF(fileName)
        val stripper = PDFTextStripper()
        val text = stripper.getText(doc)
        val fileCsv: File = File("src/main/kotlin/temp.csv")

        val sb = StringBuilder()
        val sb2 = StringBuilder()

        sb.append(stripper.getText(doc))
        sb2.append(stripper.getText(doc))

        val p: Pattern = Pattern.compile("\\d{2}-\\d{2}-\\d{4}")
        val p2: Pattern = Pattern.compile("\\d{1},\\d{2}")

        val m: Matcher = p.matcher(sb)
        while (m.find()) {
            list.add(m.start())
        }
        list.removeFirst()

        fun <T> removeValuesAtEvenPositions(list: List<T>): List<T> {
            return list.filterIndexed { index, _ -> (index + 1) % 2 != 0 }
        }

        fun <T> removeValuesAtOddPositions(list: List<T>): List<T> {
            return list.filterIndexed { index, _ -> (index + 1) % 2 == 0 }
        }

        fun containsLetters(input: String): Boolean {
            return input.any { char -> char.isLetter() }
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
            list2.add(m2.start())
        }
        list2.removeFirst()
        list2.removeFirst()
        list2.removeFirst()
        list2.removeFirst()
        list2 = removeValuesAtOddPositions(list2).toMutableList()
        list2 = list2.map { it + 3 }.toMutableList()

        var tempNumber: Int = list.size - 1

        if (fileCsv.readText().isNotEmpty()) {
            FileOutputStream(fileCsv).close()
        }

        while (tempNumber != -1) {
            var tempList: MutableList<String> = mutableListOf()
            tempList = text.substring(list[tempNumber], list2[tempNumber])
                .replace("\r\n", "").split(" ").toMutableList()
            if (containsLetters(tempList[tempList.size - 2])) {
                tempList[tempList.size - 2] = removeLetters(tempList[tempList.size - 2])
            }
            FileWriter(fileCsv, true).use { out ->
                out.append("${tempList[0]}/${tempList[tempList.size - 2].replace(".", "").replace(",", ".")}/${tempList[tempList.size - 1].replace(".", "").replace(",", ".")}\n")
            }
            list.removeLast()
            list2.removeLast()
            tempNumber = tempNumber - 1
        }

        ReportOpenerCTT("src/main/ctt.pdf").readCTT()
        ReportOpenerCTT("src/main/ctt.pdf").datesCTT()
        ReportOpenerCTT("src/main/ctt.pdf").locateNumber()
        ReportOpenerCTT("src/main/ctt.pdf").extractCTT()

        val listaBpi: List<Any> = BpiProc().principal()
        val listaCtt: List<Any> = CttProc().principal()

        when (escolhaMenu) {
            1 -> generateBpiReport(listaBpi)
            2 -> generateCttReport(listaCtt)
            3 -> generateCombinedReport(listaBpi, listaCtt)
        }
    }

    private fun generateBpiReport(listaBpi: List<Any>) {
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

    private fun generateCttReport(listaCtt: List<Any>) {
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
        contentStream.showText("Maior gasto: ${listaCtt[2]} no dia ${listaCtt[3]}")
        contentStream.newLine()
        contentStream.showText("Total Mes: ${listaCtt[4]}")
        contentStream.newLine()
        contentStream.showText("Media Gastos: ${listaCtt[5]}")
        contentStream.endText()
        contentStream.close()
        doc.save("src/main/resultado.pdf")
        doc.close()
    }

    private fun generateCombinedReport(listaBpi: List<Any>, listaCtt: List<Any>) {
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
        contentStream.newLine()
        contentStream.showText("---------------------------CTT---------------------------")
        contentStream.newLine()
        contentStream.showText("Maior lucro: ${listaCtt[0]} no dia ${listaCtt[1]}")
        contentStream.newLine()
        contentStream.showText("Maior gasto: ${listaCtt[2]} no dia ${listaCtt[3]}")
        contentStream.newLine()
        contentStream.showText("Total Mes: ${listaCtt[4]}")
        contentStream.newLine()
        contentStream.showText("Media Gastos: ${listaCtt[5]}")
        contentStream.endText()
        contentStream.close()
        doc.save("src/main/resultado.pdf")
        doc.close()
    }
}
