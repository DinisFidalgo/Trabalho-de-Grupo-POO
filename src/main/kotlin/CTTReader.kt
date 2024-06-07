package org.example
import org.apache.pdfbox.Loader
import org.apache.pdfbox.pdmodel.PDDocument
import org.apache.pdfbox.text.PDFTextStripper
import java.io.File
import java.util.regex.Matcher
import java.util.regex.Pattern

class CTTReader (private val fileCTT: String) {
    fun readCTT() {
        val file = File(fileCTT)
        var list: MutableList<Int> = mutableListOf()
        val doc: PDDocument = Loader.loadPDF(file)
        val stripper = PDFTextStripper()
        val text = stripper.getText(doc)

        val sb = StringBuilder()

        sb.append(stripper.getText(doc))

        val p: Pattern = Pattern.compile("\\d{2}-\\d{2}-\\d{4}")

        val m: Matcher = p.matcher(sb)
        while (m.find()) {
            //adiciona a uma lista todos as posicoes inicias de uma data encontradas num documento
            list.add(m.start())
        }
        // PDF comeca com uma data nao importante por isso temos que remover primeira localizacao de uma data
        list.removeFirst()


        if (doc != null) {
            doc.close()
        }
        for(i in list){
            println(text.substring(i,))
        }


    }
}