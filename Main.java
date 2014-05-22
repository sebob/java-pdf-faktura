/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package faktury;
import com.itextpdf.text.BaseColor;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Date;


import java.io.*;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.DocumentFont;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Chunk;

import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.*;
import com.itextpdf.text.*;
import java.awt.*;
import java.awt.Color.*;
import java.awt.color.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.itextpdf.text.pdf.CMYKColor;

import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Rectangle;


/**
 *
 * @author sebastian
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        CreateDocument Faktura = new CreateDocument();
        Faktura.CreateDocument();

    }

}

class CreateDocument {
    
    public Document CreateDocument(){
        Document document = new Document(PageSize.A4, 36, 20, 36, 20);
        document.addTitle("Hello World example");
        document.addSubject("This example shows how to add metadata");
        document.addKeywords("Metadata, iText, step 3, tutorial");
        document.addCreator("My program using iText");
        document.addAuthor("Bruno Lowagie");
        document.addHeader("Expires", "0");

         try {
                  PdfWriter.getInstance(document,
                    new FileOutputStream("faktura.pdf"));
                  document.open();

                  CreateTable obj = new CreateTable();
                  Data objSeller = new Data();
                  Font font = new Font(Font.FontFamily.COURIER, 12);

                    document.add(objSeller.createSeller());
                    document.add(obj.createHeader());
                    document.add(obj.createData());
                    document.add(obj.createResum());
                    document.add(obj.createSum());
                  document.add(
                    new Paragraph("Hello World",font));
                } catch (Exception e) {
                  // handle exception
                }


        document.close();
        return document;
    }
}

class CreateTable {
    public PdfPTable createHeader(){
        BaseFont bf = null;
        try {
            bf = BaseFont.createFont(BaseFont.TIMES_ROMAN, BaseFont.CP1250, BaseFont.EMBEDDED);
        } catch (DocumentException ex) {
            Logger.getLogger(CreateTable.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CreateTable.class.getName()).log(Level.SEVERE, null, ex);
        }
        Font f = new Font(bf, 9, Font.NORMAL);

        float[] widths1 = { 5, 30, 5, 5, 10, 10, 10, 10, 10 };
        PdfPTable table1 = new PdfPTable(widths1);
        table1.setWidthPercentage(100f);
        PdfPCell header = new PdfPCell(new Phrase("Faktura proforma 322186/pro/2010"));
        header.setPadding(5);
        header.setColspan(9);
        table1.addCell(header);
        table1.getDefaultCell().setBackgroundColor(BaseColor.LIGHT_GRAY);
        table1.getDefaultCell().setPaddingBottom(3);
        table1.getDefaultCell().setPaddingTop(2);
        table1.getDefaultCell().setPaddingLeft(3);
        table1.getDefaultCell().setPaddingRight(3);
        table1.getDefaultCell().setBorderWidth(1);

        Phrase lp = new Phrase("Lp.", f);
        Phrase nazwa_tow = new Phrase("Nazwa towaru/usługi\n(SWW/KU)", f);
        Phrase jm = new Phrase("J.m.", f);
        Phrase ilosc = new Phrase("Ilość", f);
        Phrase cena_netto = new Phrase("Cena j. netto [zł]", f);
        Phrase rabat = new Phrase("Rabat [zł]", f);
        Phrase wartosc_netto = new Phrase("Wartość netto [zł]", f);
        Phrase kwota_podatku = new Phrase("Kwota podatku [zł]", f);
        Phrase wartosc_brutto = new Phrase("Wartość brutto [zł]", f);

        table1.addCell(lp);
        table1.addCell(nazwa_tow);
        table1.addCell(jm);
        table1.addCell(ilosc);
        table1.addCell(cena_netto);
        table1.addCell(rabat);
        table1.addCell(wartosc_netto);
        table1.addCell(kwota_podatku);
        table1.addCell(wartosc_brutto);

        return table1;
    }

    public PdfPTable createData(){
        float[] widths1 = { 5, 30, 5, 5, 10, 10, 10, 10, 10 };
        PdfPTable table = new PdfPTable(widths1);
                  table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
                  table.getDefaultCell().setPaddingRight(4);
        table.setWidthPercentage(100f);
        for(int k = 0; k < 170; k++){
            if(k%9==1){
                table.addCell(String.valueOf("Przedłużenie okresu rejestracji domeny domena"+k+".pl, od 2010.06.01, do 2011.06.01"));
            }else{
                table.addCell(String.valueOf(k));
            }
        }
        return table;
    }

    public PdfPTable createResum(){
        float[] widths1 = { 15, 10, 10, 10, 10};
        PdfPTable table = new PdfPTable(widths1);
                  table.getDefaultCell().setBorderWidth(1);
                  table.getDefaultCell().setPadding(3);
                  table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
                  table.setWidthPercentage(58);
                  table.setHorizontalAlignment(Element.ALIGN_RIGHT);
        table.addCell(new Phrase("W tym:"));
        table.addCell(new Phrase("600"));
        table.addCell(new Phrase("0"));
        table.addCell(new Phrase("123,00"));

        PdfPCell Suma = new PdfPCell(new Phrase("732,00"));
                 Suma.setHorizontalAlignment(Element.ALIGN_RIGHT);
                 Suma.setBackgroundColor(BaseColor.LIGHT_GRAY);
        table.addCell(Suma);
        return table;
    }

    public PdfPTable createSum(){
        float[] widths1 = { 15, 10, 10, 10, 10};
        PdfPTable table = new PdfPTable(widths1);
                  table.getDefaultCell().setBorderWidth(1);
                  table.getDefaultCell().setPadding(3);
                  table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
                  table.setWidthPercentage(58);
                  table.setHorizontalAlignment(Element.ALIGN_RIGHT);
        table.addCell(new Phrase("Razem:"));
        table.addCell(new Phrase("600"));
        table.addCell(new Phrase("x"));
        table.addCell(new Phrase("123,00"));


        PdfPCell Suma = new PdfPCell(new Phrase("732,00"));
        Suma.setHorizontalAlignment(Element.ALIGN_RIGHT);
        Suma.setBackgroundColor(BaseColor.LIGHT_GRAY);
        table.addCell(Suma);

        return table;
    }
}

class Data {
    public PdfPTable createSeller(){
        BaseFont bf = null;
        try {
            bf = BaseFont.createFont(BaseFont.TIMES_ROMAN, BaseFont.CP1250, BaseFont.EMBEDDED);
        } catch (DocumentException ex) {
            Logger.getLogger(CreateTable.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CreateTable.class.getName()).log(Level.SEVERE, null, ex);
        }
        Font f1 = new Font(bf, 9, Font.NORMAL);
        Font f2 = new Font(bf, 10, Font.BOLD);

       Phrase desc = new Phrase("Sprzedawca:\n", f1);
       Phrase seller = new Phrase("xxx Spółka Akcyjna S.A.\nXXX 1\30-333 Kraków\nNIP: 625-120-22-XX", f2);
       desc.add(seller);

       Phrase info = new Phrase("Faktura proforma 322186/pro/2010\n\nData wystawienia: 2010-06-09, Kraków");


       //float[] widths1 = {200};
        PdfPTable table = new PdfPTable(2);
                  table.getDefaultCell().setBorder(0);
                  table.getDefaultCell().setPadding(3);
                  
                  table.setWidthPercentage(100f);

                  PdfPCell cell_seller= new PdfPCell(desc);
                  cell_seller.setHorizontalAlignment(Element.ALIGN_LEFT);
                  cell_seller.setBorder(Rectangle.NO_BORDER);
                  cell_seller.setPaddingBottom(10);

                  PdfPCell cell_info = new PdfPCell(info);
                  cell_info.setHorizontalAlignment(Element.ALIGN_RIGHT);
                  cell_info.setBorder(Rectangle.NO_BORDER);
                  cell_info.setPaddingBottom(10);


                  PdfPCell cell_emptor = new PdfPCell();
                  cell_emptor.setHorizontalAlignment(Element.ALIGN_LEFT);
                  cell_emptor.setPaddingBottom(40);
                  cell_emptor.setBorder(Rectangle.NO_BORDER);
                  cell_emptor.setPhrase(desc);

                  PdfPCell cell_offeree = new PdfPCell();
                  cell_offeree.setHorizontalAlignment(Element.ALIGN_LEFT);
                  cell_offeree.setPaddingBottom(40);
                  cell_offeree.setBorder(Rectangle.NO_BORDER);
                  cell_offeree.setPhrase(desc);

                  table.addCell(cell_seller);
                  table.addCell(cell_info);
                  table.addCell(cell_emptor);
                  table.addCell(cell_offeree);
                return table;

    }
}
