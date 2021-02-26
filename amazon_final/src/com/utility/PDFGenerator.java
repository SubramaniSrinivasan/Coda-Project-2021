package com.utility;

import java.io.OutputStream;
import java.util.List;

import com.itextpdf.text.Chapter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.model.Bill;
import com.model.Item;
import com.model.User;

public class PDFGenerator {

	public static void execute(User user, Bill bill, List<Item> itemList, OutputStream out) {
		
		try {
			Document document = new Document();
			PdfWriter.getInstance(document, out);
			document.open();
			addMetaData(document, bill.getBillNo());
			addContent(document, user, bill, itemList);
			document.close();
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		
	}
	
	private final static void addMetaData(Document document,  Integer billNo) {
		
		document.addTitle("Invoice PDF");
        document.addSubject("Invoice PDF for Bill No : " + billNo.toString());
        document.addAuthor("Amazon");
        document.addCreator("Subramani Srinivasan");
        
	}
	
	private final static void addContent(Document document, User user, Bill bill, List<Item> itemList) throws DocumentException {
		
		Paragraph title = new Paragraph("Amazon Invoice");
	      
        title.setAlignment(Element.ALIGN_CENTER);
        Chapter chapter = new Chapter(title, 1);
        
        Paragraph paragraph = new Paragraph();
        addEmptyLine(paragraph, 2);
        chapter.add(paragraph);

        chapter.add(new Paragraph("Bill Number : " + bill.getBillNo().toString()));
        chapter.add(new Paragraph("Bill Date : " + bill.getDate().toString()));
        chapter.add(new Paragraph("User Id : " + String.valueOf(user.getUid())));
        chapter.add(new Paragraph("User Name : " + user.getUname()));
        
        paragraph = new Paragraph();
        addEmptyLine(paragraph, 2);
        chapter.add(paragraph);
        
        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100.00f);
        
        PdfPCell c1 = new PdfPCell(new Phrase("S.NO"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);
        
        c1 = new PdfPCell(new Phrase("Item ID"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);
        
        c1 = new PdfPCell(new Phrase("Item Name"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);
        
        c1 = new PdfPCell(new Phrase("Unit"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);
        
        c1 = new PdfPCell(new Phrase("Price"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);
        
        table.setHeaderRows(1);
        
        int totalPrice = 0, count = 0;
        
        for(Item item : itemList) {
        	table.addCell(String.valueOf(count));
			table.addCell(item.getItemID());
			table.addCell(item.getItemDescription());
			table.addCell(item.getUnit());
			table.addCell(String.valueOf(item.getPrice()));
			totalPrice += item.getPrice();
        }
        
        chapter.add(table);
        
        Paragraph para = new Paragraph("Total Price " + totalPrice);
        para.setAlignment(Element.ALIGN_RIGHT);
        chapter.add(para);

        document.add(chapter);
        
	}
	
	 private final static void addEmptyLine(Paragraph paragraph, int number) {
	        for (int i = 0; i < number; i++) {
	            paragraph.add(new Paragraph(" "));
	        }
	    }
	
}
