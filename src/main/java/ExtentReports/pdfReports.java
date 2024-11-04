package ExtentReports;

import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

public class pdfReports {

	private PdfDocument pdfDoc;
	private Document document;

	public pdfReports(String filePath) {
		try {
			pdfDoc = new PdfDocument(new PdfWriter(filePath));
			document = new Document(pdfDoc);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		document.add(new Paragraph("MyAdvice Automation test report")).setFontSize(20).setBold()
				.setFontColor(ColorConstants.BLACK);
		document.add(new Paragraph(
				"Generated on:" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:MM:SS")))
				.setFontSize(10).setFontColor(ColorConstants.DARK_GRAY).setMarginBottom(20));
	}
	
	 public void logTestStep(String stepDesc, String status, String remarks) {
	        String color = "Pass".equalsIgnoreCase(status) ? "Green" : "Red";

	        Paragraph stepParagraph = new Paragraph()
	                .add("Step Description: " + stepDesc + "\n")
	                .add("Status: ").setFontColor("Pass".equalsIgnoreCase(status) ? ColorConstants.GREEN : ColorConstants.RED)
	                .add(status + "\n")
	                .add("Remarks: " + remarks + "\n\n").setFontSize(14);
	        
	        document.add(stepParagraph);
	    }

	    public void closeReport() {
	        document.close();
	    }
}