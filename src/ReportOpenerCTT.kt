import org.apache.pdfbox.pdmodel.PDDocument
import org.apache.pdfbox.text.PDFTextStripper
import java.io.File

class ReportOpenerCTT(private val fileCTT: String){

    fun readCTT():String{
        val file = File(fileCTT)

        PDDocument.load(file).use { document ->
            val pdfStrip = PDFTextStripper()

            return pdfStrip.getText(document)
        }
    }

}