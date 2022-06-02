package Process;

import com.lowagie.text.Font;
import com.lowagie.text.List;
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.Scanner;

public class ExportPDF extends Component {
    private DefaultValue dv = new DefaultValue();
    private String Directory;
    private Document document = new Document();

    private static Font catFont = new Font(Font.TIMES_ROMAN, 18,
            Font.BOLD);
    private static Font redFont = new Font(Font.TIMES_ROMAN, 12,
            Font.NORMAL, Color.RED);
    private static Font subFont = new Font(Font.TIMES_ROMAN, 16,
            Font.BOLD);
    private static Font smallBold = new Font(Font.TIMES_ROMAN, 12,
            Font.BOLD);

    public void openPDF()
    {
        document.open();
    }

    public void closePDF()
    {
        document.close();
    }

    public String getDirectory() {
        return Directory;
    }

    public void setDirectory(String directory) {
        Directory = directory;
    }

    public PdfWriter getPdfwriter() throws FileNotFoundException, DocumentException {
        return PdfWriter.getInstance(document, new FileOutputStream(getDirectory()));
    }

    public void chooseDirectory()
    {
        JFileChooser savefile = new JFileChooser();
        int sf = savefile.showSaveDialog(savefile);
        setDirectory(savefile.getSelectedFile().getPath());
        if (getDirectory().toLowerCase().contains(".pdf") == false)
            setDirectory(getDirectory()+".pdf");

        BufferedWriter writer = null;
        if(sf == JFileChooser.APPROVE_OPTION)
        {
            Scanner ScanFile = null;
            try {
                ScanFile = new Scanner(new File(getDirectory()));
            } catch (FileNotFoundException ex) {}

            if (ScanFile != null)
                if (dv.popupConfirmOption(null,
                        "Tệp " + savefile.getSelectedFile().getName() + " đã tồn tại, xác nhận ghi đè?",
                        "Xác nhận?") != 0)
                    return;

            try {
                writer = new BufferedWriter(new FileWriter(getDirectory()));
                writer.close();
                dv.popupOption(null, "Tệp đã được lưu!", "Đã lưu tệp!", 0);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private Font normalStyle = new Font(Font.TIMES_ROMAN, 13,Font.NORMAL, new Color(dv.BlackTextColor()));
    private Font titleStyle = new Font(Font.TIMES_ROMAN, 18,Font.NORMAL, new Color(dv.FeatureButtonColor()));

    public Font getNormalStyle() {
        return normalStyle;
    }

    public Font getTitleStyle() {
        return titleStyle;
    }


    /*    public void main(String[] args) {
        try {
            Document doc = new Document();
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(FilePath));
            addMetaData(document);
            addTitlePage(document);
            addContent(document);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/

    // iText allows to add metadata to the PDF which can be viewed in your Adobe
    // Reader
    // under File -> Properties
    public void addMetaData(String title, String subject, String keywords, String author, String creator) {
        document.addTitle(title);
        document.addSubject(subject);
        document.addKeywords(keywords);
        document.addAuthor(author);
        document.addCreator(creator);
    }

    public void addParagraph(String text, Font font) throws DocumentException, FileNotFoundException {
        //getPdfwriter();
        //openPDF();
        document.add(new Paragraph(text, font));
        //closePDF();
    }

    public void addParagraph(Paragraph paragraph) throws DocumentException, FileNotFoundException {
        //getPdfwriter();
        //openPDF();
        document.add(paragraph);
        //closePDF();
    }

    public void addObject(JPanel panel) throws DocumentException, FileNotFoundException
    {
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(getDirectory()));
        openPDF();
        
        PdfContentByte contentByte = writer.getDirectContent();
        document.add(new Chunk(""));
        PdfTemplate template = contentByte.createTemplate(panel.getWidth(), panel.getHeight());
        Graphics2D g2 = template.createGraphics(panel.getWidth(), panel.getHeight());
        panel.print(g2);
        g2.dispose();
        contentByte.addTemplate(template,0,0);

/*        PdfContentByte cb = writer.getDirectContent();
        PdfTemplate tp = cb.createTemplate(panel.getWidth(), panel.getHeight());
        Graphics2D g2 = tp.createGraphics(panel.getWidth(), panel.getHeight());
//        g2.scale(0.8, 1.0);
        panel.print(g2);
        g2.dispose();
        cb.addTemplate(tp, 0, 0);*/
        //closePDF();
    }

    public void addEmptyLine(int number) throws DocumentException, FileNotFoundException
    {
        //getPdfwriter();
        //openPDF();
        Paragraph paragraph = new Paragraph();
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph("*", getNormalStyle()));
            document.add(paragraph);
        }
        //closePDF();
    }

    public void addNewPage() throws DocumentException, FileNotFoundException {
        //getPdfwriter();
        //openPDF();
        document.newPage();
        //closePDF();
    }

/*    private void addContent(Document document) throws DocumentException {
        Anchor anchor = new Anchor("First Chapter", catFont);
        anchor.setName("First Chapter");

        // Second parameter is the number of the chapter
        Chapter catPart = new Chapter(new Paragraph(anchor), 1);

        Paragraph subPara = new Paragraph("Subcategory 1", subFont);
        Section subCatPart = catPart.addSection(subPara);
        subCatPart.add(new Paragraph("Hello"));

        subPara = new Paragraph("Subcategory 2", subFont);
        subCatPart = catPart.addSection(subPara);
        subCatPart.add(new Paragraph("Paragraph 1"));
        subCatPart.add(new Paragraph("Paragraph 2"));
        subCatPart.add(new Paragraph("Paragraph 3"));

        // add a list
        createList(subCatPart);
        Paragraph paragraph = new Paragraph();
        addEmptyLine(paragraph, 5);
        subCatPart.add(paragraph);

        // add a table
        createTable(subCatPart);

        // now add all this to the document
        document.add(catPart);

        // Next section
        anchor = new Anchor("Second Chapter", catFont);
        anchor.setName("Second Chapter");

        // Second parameter is the number of the chapter
        catPart = new Chapter(new Paragraph(anchor), 1);

        subPara = new Paragraph("Subcategory", subFont);
        subCatPart = catPart.addSection(subPara);
        subCatPart.add(new Paragraph("This is a very important message"));

        // now add all this to the document
        document.add(catPart);
    }*/

    private void createTable(Section subCatPart)
            throws BadElementException {
        PdfPTable table = new PdfPTable(3);

        // t.setBorderColor(BaseColor.GRAY);
        // t.setPadding(4);
        // t.setSpacing(4);
        // t.setBorderWidth(1);

        PdfPCell c1 = new PdfPCell(new Phrase("Table Header 1"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Table Header 2"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Table Header 3"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);
        table.setHeaderRows(1);

        table.addCell("1.0");
        table.addCell("1.1");
        table.addCell("1.2");
        table.addCell("2.1");
        table.addCell("2.2");
        table.addCell("2.3");

        subCatPart.add(table);

    }

    private void createList(Section subCatPart) {
        List list = new List(true, false, 10);
        list.add(new ListItem("First point"));
        list.add(new ListItem("Second point"));
        list.add(new ListItem("Third point"));
        subCatPart.add(list);
    }


}
