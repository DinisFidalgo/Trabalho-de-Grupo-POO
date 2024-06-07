import org.apache.pdfbox.Loader
import org.apache.pdfbox.io.RandomAccessRead
import org.apache.pdfbox.pdmodel.PDDocument
import org.apache.pdfbox.pdmodel.PDPage
import org.apache.pdfbox.pdmodel.PDPageContentStream
import org.apache.pdfbox.pdmodel.font.PDCIDFont
import org.apache.pdfbox.pdmodel.font.PDFont
import org.apache.pdfbox.pdmodel.font.PDType1Font
import org.apache.pdfbox.pdmodel.font.Standard14Fonts
import org.apache.pdfbox.text.PDFTextStripper
import java.awt.Font
import java.io.File
import java.io.FileWriter
import java.io.RandomAccessFile
import java.util.regex.Matcher
import java.util.regex.Pattern



class ReportOpenerCTT(private val fileCTT:String){

    val cttFile = File(fileCTT)
    var text: String = ""
    var dateList:MutableList<Int> = mutableListOf()
    var numberList: MutableList<Int> = mutableListOf()
    val csvCTT = File("src/main/kotlin/cttTemp.csv")


    fun readCTT(){
        val file: PDDocument = Loader.loadPDF(cttFile)
        val stripper = PDFTextStripper()
        text = stripper.getText(file)

    }

    fun datesCTT(){

        val pattern: Pattern = Pattern.compile("\\d{2}-\\d{2}-\\d{4}")
        val itMatch: Matcher = pattern.matcher(text)
        while (itMatch.find()){
            dateList.add(itMatch.start())
        }

        dateList.removeAt(0)
        dateList = removeValuesAtEvenPositions(dateList).toMutableList()
    }

    fun locateNumber(){
        val pattern: Pattern = Pattern.compile("\\d{1},\\d{2}")
        val matcher: Matcher = pattern.matcher(text)

        while (matcher.find()){
            numberList.add(matcher.start())
        }
        numberList.removeAt(0)

        numberList = removeValuesAtOddPositions(numberList).toMutableList()

        numberList = numberList.map { it+3 }.toMutableList()
    }

    private fun removeValuesAtEvenPositions(list: List<Int>): List<Int> {
        return list.filterIndexed { index, _ -> (index + 1) % 2 != 0}

    }

    private fun removeValuesAtOddPositions(list: List<Int>): List<Int> {
        return list.filterIndexed {index, _ -> (index + 1)% 2 == 0}
    }

    private fun containsLetters(input : String): Boolean {
        return input.any {char -> char.isLetter()}
    }

    private fun removeLetters(input: String):String{
        return input.filter{!it.isLetter()}
    }

    fun extractCTT(){
        dateList.reverse()
        numberList.reverse()

        var tNumber: Int = dateList.size - 1

        while (tNumber !== -1){
            val tList:MutableList<String> = text.substring(dateList[tNumber], numberList [tNumber]).replace("\r","").split(" ").toMutableList()
            if (containsLetters(tList[tList.size - 2])){
                tList[tList.size - 2] = removeLetters(tList[tList.size -2])
            }
            FileWriter(csvCTT,true).use {out ->
                out.append("${tList[0]} / ${tList[tList.size - 2]} / ${tList[tList.size -1]} \n")

            }
            dateList.removeAt(dateList.size - 1)
            numberList.removeAt(numberList.size -1)
            tNumber -= 1

        }
    }



}

