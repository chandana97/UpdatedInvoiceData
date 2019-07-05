package com.altimerik.extractdata;

import java.io.File;
import java.io.IOException;

import org.apache.commons.math3.analysis.function.Constant;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;
import org.apache.pdfbox.text.PDFTextStripper;
//import org.apache.sis.internal.converter.PathConverter.FileURI;

import com.altimerik.constants.Constants;
import com.altimetrik.bean.BeanVariables;
import com.altimetrik.jdbcconnection.InvoiceDataDb;

public class ExtractDataFromPdf {
	PDDocument document;
	String InvoiceNo;
	String InvoiceDate;
	String CustomerPO;
	String SoldTo;
	String ShipTo;
	String RemitTo;
	String TotalInvoice;
	String Status;
	int i;
	public void fetchPdfData() throws IOException{
		try{
		File file = new File(Constants.fileUrl);
	      PDDocument document = PDDocument.load(file);
	      PDFTextStripper pdfStripper = new PDFTextStripper();
	      String text = pdfStripper.getText(document);
	      String pdfText[]=text.split("\\r?\\n");
	      for(i=0;i<pdfText.length;i++){
	    	  while(i<pdfText.length) {
	  			
	 			 if(pdfText[i].equals("Invoice No")) {
	 				 System.out.println("Invoice No:\t"+pdfText[i+1]);
	 				 InvoiceNo=pdfText[i+1];
	 				 i+= 2;
	 				 break;	 
	 			 }
	 			 i++;
	 			
	 		 }
	    	  
	    	  while(i<pdfText.length) {
	 			 if(pdfText[i].equals("Invoice Date")) {
	 				 System.out.println("Invoice Date:\t"+pdfText[i+1]);
	 				 InvoiceDate=pdfText[i+1];
	 				 i+= 2;
	 				 break;
	 			 }
	 			 i++;
	 			 
	 		 }
	 		 
	 		 while(i<pdfText.length) {
	 			 if(pdfText[i].equals("Customer P.O.")) {
	 				 System.out.println("Customer P.O:\t"+pdfText[i+1]);
	 				 CustomerPO=pdfText[i+1];
	 				 i+= 2;
	 				 break;
	 			 }
	 			 i++;
	 		 }
	 		while(i<pdfText.length) {
				 if(pdfText[i].equals("Sold To")) {
					// System.out.print("Sold To:\t"+pdfText[i+1]);
					 i=i+1;
					 System.out.println("sold To:");
					 while(!pdfText[i].equals("Ship To"))
					 {
						 System.out.print(pdfText[i]);
						 SoldTo=pdfText[i];
						 i=i+1;
						  
					 }
					 System.out.println();
					 break;
				 }
				 i++;
			 }
			 
			 while(i<pdfText.length) {
				 if(pdfText[i].equals("Ship To")) {
					// System.out.print("Ship To:\t"+pdfText[i+1]);
					 System.out.println("Ship To:");
					 i=i+1;
					 while(!pdfText[i].equals("Remit To"))
					 {
						 System.out.print(pdfText[i]);
						 ShipTo=pdfText[i];
						 i=i+1;
						
					 }
					 System.out.println();
					 break;
				 }
				 i++;
			 }
			 
			 while(i<pdfText.length) {
				 if(pdfText[i].equals("Remit To")) {
					// System.out.print("Remit To:\t"+pdfText[i+1]);
					 System.out.println("Remit To");
					 i=i+1;
					 while(!pdfText[i].equals("Payment Terms"))
					 {
						 System.out.print(pdfText[i]);
						 RemitTo=pdfText[i];
						 i=i+1;
					 }
					 System.out.println();
					 break;
				 }
				 i++;
			 }
	 		 while(i<pdfText.length) {
				 if(pdfText[i].equals("Total Invoice")) {
					 System.out.println("Total Invoice:\t"+pdfText[i+3]);
					//i+=2;
					 TotalInvoice=pdfText[i+3];
					System.out.println();
					 break;
				 }
				 i++;
			 }
	    	  
	      }
	      InvoiceDataDb  indb=new InvoiceDataDb ();
	      indb.insertInvoiceData(InvoiceNo,InvoiceDate,CustomerPO,SoldTo,ShipTo,RemitTo,TotalInvoice,Status);
	      
	      
		}
		catch(Exception e){
			System.out.println(e);
		}
		finally {
			document.close();
		}
	}

}
