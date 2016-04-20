

import java.io.FileOutputStream;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class PDFCreator {
	
	private String amount;
	private String dateIssued;
	private String chargeDate;
	private String procedure;
	private int patient_ID;
	private int billing_ID;
	private String name;
	
	
	public PDFCreator(String name, String amount, String procedure, String dateIssued, String chargeDate, int patient_ID, int billing_ID){
	   
		this.name = name;
		this.amount = amount;
		this.procedure = procedure;
		this.dateIssued = dateIssued;
		this.chargeDate = chargeDate;
		this.patient_ID = patient_ID;
		this.billing_ID = billing_ID;
		
		Document document = new Document();
	    try
	    {
	        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("Bill" + billing_ID +".pdf"));
	        document.open();
	 
	   /*     PdfPTable table = new PdfPTable(4); // 3 columns.
	        table.setWidthPercentage(100); //Width 100%
	        table.setSpacingBefore(10f); //Space before table
	        table.setSpacingAfter(10f); //Space after table
	 
	        //Set Column widths
	        float[] columnWidths = {1f, 1f, 1f, 1f};
	        table.setWidths(columnWidths);
	 
	        PdfPCell cell1 = new PdfPCell(new Paragraph("Cell 1"));
	        cell1.setBorderColor(BaseColor.BLUE);
	        cell1.setPaddingLeft(10);
	        cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
	        cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);
	 
	        PdfPCell cell2 = new PdfPCell(new Paragraph("Cell 2"));
	        cell2.setBorderColor(BaseColor.GREEN);
	        cell2.setPaddingLeft(10);
	        cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
	        cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
	 
	        PdfPCell cell3 = new PdfPCell(new Paragraph("Cell 3"));
	        cell3.setBorderColor(BaseColor.RED);
	        cell3.setPaddingLeft(10);
	        cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
	        cell3.setVerticalAlignment(Element.ALIGN_MIDDLE);
	        
	       
	 
	        //To avoid having the cell border and the content overlap, if you are having thick cell borders
	        //cell1.setUserBorderPadding(true);
	        //cell2.setUserBorderPadding(true);
	        //cell3.setUserBorderPadding(true);
	 
	        table.addCell(cell1);
	        table.addCell(cell2);
	        table.addCell(cell3);
	        table.addCell("cell 4");
	        table.addCell("Cell 5");
	   */
	   


	        //Add Image	
	        Image image1 = Image.getInstance("Pictures\\Logo.PNG");
	        //Fixed Positioning
	        image1.setAbsolutePosition(150f, 750f);
	        image1.scaleAbsolute( image1.getScaledWidth() / 2, image1.getScaledHeight() / 2);	

	        //Scale to new height and new width of image
	        //image1.scaleAbsolute(500, 200);
	        
	        //Add to document
	        document.add(image1);
	     
	        Paragraph companyInfo = new Paragraph("Nimbus Clinical Management \n8421 West Forest Drive,\nFayetteville, Arkansas, 72701\n555-382-9876\n12/31/2016");
	        companyInfo.setSpacingBefore(55f);
	        companyInfo.setSpacingAfter(10f);
	   
	        Paragraph billingInvoice = new Paragraph("Billing Invoice");
	        billingInvoice.setAlignment(Element.ALIGN_CENTER);
	        
	        document.add(companyInfo);
	        document.add(billingInvoice);
	        //document.add(paragraphTable1);
	        document.add(createFirstTable());
	        
	        document.close();
	        writer.close();
	    } catch (Exception e)
	    {
	        e.printStackTrace();
	    }
	}

	 public PdfPTable createFirstTable() {
		
	    	// a table with three columns
	        PdfPTable table = new PdfPTable(2);
	        table.setSpacingBefore(50f); //Space before table
	        table.setSpacingAfter(100f); //Space after table
	        float[] columnWidths = {2f,2f};
	        try {
				table.setWidths(columnWidths);
			 
			} catch (DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
	  
	        PdfPCell cell;
	        // we add a cell with colspan 3
	        cell = new PdfPCell(new Phrase("Patient Information"));
	        cell.setColspan(2);
	        cell.setBorder(Rectangle.BOTTOM);
	        table.addCell(cell);
	        
	        cell = new PdfPCell(new Phrase("Name:"));
	        cell.setBorder(Rectangle.NO_BORDER);
	        table.addCell(cell);
	        
	        cell = new PdfPCell(new Phrase(name));
	        cell.setBorder(Rectangle.NO_BORDER);
	        table.addCell(cell);
	        
	        cell = new PdfPCell(new Phrase("Patient ID:"));
	        cell.setBorder(Rectangle.NO_BORDER);
	        table.addCell(cell);
	        
	        cell = new PdfPCell(new Phrase(Integer.toString(patient_ID)));
	        cell.setBorder(Rectangle.NO_BORDER);
	        table.addCell(cell);
	        
	        //Blank row to add padding
	        cell = new PdfPCell(new Phrase(" "));
	        cell.setColspan(2);
	        cell.setBorder(Rectangle.NO_BORDER);
	        table.addCell(cell);
	        ////////////////////////////
	        cell = new PdfPCell(new Phrase("Date Issued:"));
	        cell.setBorder(Rectangle.NO_BORDER);
	        table.addCell(cell);
	        
	        cell = new PdfPCell(new Phrase(dateIssued));
	        cell.setBorder(Rectangle.NO_BORDER);
	        table.addCell(cell);
	        
	        cell = new PdfPCell(new Phrase("Charge Date:"));
	        cell.setBorder(Rectangle.NO_BORDER);
	        table.addCell(cell);
	        
	        cell = new PdfPCell(new Phrase(chargeDate));
	        cell.setBorder(Rectangle.NO_BORDER);
	        table.addCell(cell);
	        
	        //Blank row to add padding
	        cell = new PdfPCell(new Phrase(" "));
	        cell.setColspan(2);
	        cell.setBorder(Rectangle.NO_BORDER);
	        table.addCell(cell);
	        /////////////////////
	        
	        cell = new PdfPCell(new Phrase("Procedure"));
	        cell.setColspan(2);
	        cell.setBorder(Rectangle.BOTTOM);
	        table.addCell(cell);
	        
	        cell = new PdfPCell(new Phrase(procedure));
	        cell.setBorder(Rectangle.NO_BORDER);
	        table.addCell(cell);
	        
	        cell = new PdfPCell(new Phrase(amount));
	        cell.setBorder(Rectangle.NO_BORDER);
	        table.addCell(cell);
	        
	        //Blank row to add padding
	        cell = new PdfPCell(new Phrase("  "));
	        cell.setColspan(2);
	        cell.setBorder(Rectangle.NO_BORDER);
	        table.addCell(cell);
	        /////////////////////
	        
	        cell = new PdfPCell(new Phrase("Total:"));
	        cell.setBorder(Rectangle.NO_BORDER);
	        table.addCell(cell);
	        
	        cell = new PdfPCell(new Phrase(amount));
	        cell.setBorder(Rectangle.NO_BORDER);
	        table.addCell(cell);
	        
	        /*
	        cell = new PdfPCell(new Phrase("1234 West Forest Drive, Fayetteville Arkansas"));
	        cell.setColspan(4);
	        table.addCell(cell);
	        
	        // now we add a cell with rowspan 2
	        cell = new PdfPCell(new Phrase("Cell with rowspan 3"));
	        cell.setRowspan(3);
	        table.addCell(cell);
	        // we add the four remaining cells with addCell()
	        table.addCell("row 1; cell 1");
	        table.addCell("row 1; cell 2");
	        table.addCell("row 1; cell 3");
	        table.addCell("row 1; cell 4");
	        table.addCell("row 2; cell 1");
	        //table.addCell("row 2; cell 2");
	        //table.addCell("row 3; cell 1");
	         * 
	         * */
	         
	        return table;
	    }

	
}
