package com.mei.pdf.service;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import lombok.extern.slf4j.Slf4j;

import java.io.FileOutputStream;

/**
 * @Author meiyouqing
 * @Date 2021-02-07
 * @Description
 **/
@Slf4j
public class TestCreate {

	public static void main(String[] args) {
		try {
			/**
			 * BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
			 * 			StringBuilder builder = new StringBuilder();
			 * 			String line = null;
			 * 			while ((line = reader.readLine()) != null) {
			 * 				builder.append(line);
			 * 				builder.append(System.getProperty("line.separator"));
			 *                        }
			 */
			Document pdfDocument = new Document();
			Rectangle pageSize = new Rectangle(PageSize.A4.getHeight(), PageSize.A4.getWidth());
			PdfPTable personAttribute = new PdfPTable(4);
			personAttribute.addCell(new PdfPCell());

			PdfWriter.getInstance(pdfDocument,new FileOutputStream("/Users/lq-025/Desktop/test.pdf"));
			pdfDocument.open();
			pdfDocument.add(new Chunk("test"));
			PdfPTable pdfPTable = new PdfPTable(2);

			pdfDocument.add(pdfPTable);
			pdfDocument.setPageSize(pageSize);
			pdfDocument.setMargins(5,5,5,5);
			log.info("open result is {}",pdfDocument.isOpen());

			log.info("pdf writer open result is {}",pdfDocument.isOpen());


			pdfDocument.add(new Paragraph("name : 姓名"));
			pdfDocument.close();


		}catch (Exception e){
			log.info("e : {}",e);
		}

	}
}
