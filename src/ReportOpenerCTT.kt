import org.example
import org.apache.pdfbox.Loader
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
import java.util.regex.Matcher
import java.util.regex.Pattern



class ReportOpenerCTT(private val fileCTT: String){

    var text: String = ""
    var dateList:MutableList<Int> = mutableListOf()
    var numberList: MutableList<Int> = mutableListOf()
    val csvCTT = File("src/main/kotlin/cttTemp.csv")


    fun readCTT(){
        val file: PDDocument = Loader.loadPDF(fileCTT)
        val stripper = PDFTextStripper()
        textCTT = stripper.getText(file)

        }

    fun datesCTT(){

        val pattern: Pattern = Pattern.compile("\\d{2}-\\d{2}-\\d{4}")
        val itMatch: Matcher = pattern.matcher(text)
        while (matcher.find()){
            dateList.add(matcher.start())
        }

        dateList.removeAt(0)
        dateList = removeValuesAtEvenPositions(dateList).toMutableList()
    }

    

    }

