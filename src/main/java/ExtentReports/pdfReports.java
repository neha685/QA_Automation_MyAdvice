package ExtentReports;

import java.io.FileOutputStream;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class pdfReports {

	public void generateReport(String reportContent, String filePath) {
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream(filePath));
            document.open();
            document.add(new Paragraph(reportContent));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            document.close();
        }
}
}