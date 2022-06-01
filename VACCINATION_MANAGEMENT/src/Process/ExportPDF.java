package Process;

import com.lowagie.text.Font;
import com.lowagie.text.List;
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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
        return PdfWriter.getInstance(document, new FileOutputStream(Directory));
    }

    public void chooseDirectory()
    {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int option = fileChooser.showOpenDialog(this);
        if(option == JFileChooser.APPROVE_OPTION){
            setDirectory(fileChooser.getSelectedFile().getPath());
        }
    }

    public void savePDF(String fileName) throws FileNotFoundException, DocumentException {
        if (fileName.toLowerCase().contains(".pdf") == false)
            fileName += ".pdf";
        Directory += "/" + fileName;

        Scanner ScanFile = null;
        try {
            ScanFile = new Scanner(new File(Directory));
        } catch (FileNotFoundException ex) {
            getPdfwriter();
            return;
        }

        if (ScanFile != null)
            if (dv.popupConfirmOption(null, "Tệp " + fileName + " đã tồn tại, xác nhận ghi đè?", "Xác nhận?") != 0)
                return;

        getPdfwriter();
    }

    private Font normalStyle = new Font(Font.TIMES_ROMAN, 13,Font.NORMAL, new Color(dv.BlackTextColor()));
    private Font titleStyle = new Font(Font.TIMES_ROMAN, 18,Font.NORMAL, new Color(dv.FeatureButtonColor()));

    public Font getNormalStyle() {
        return normalStyle;
    }

    public Font getTitleStyle() {
        return titleStyle;
    }

    public void openPDF()
    {
        document.open();
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

    public void addParagraph(String text, Font font) throws DocumentException {
        document.add(new Paragraph(text, font));
    }

    public void addParagraph(Paragraph paragraph) throws DocumentException {
        document.add(paragraph);
    }

    public void addObject(JPanel panel) throws DocumentException, FileNotFoundException
    {
        PdfWriter writer =  PdfWriter.getInstance(document, new FileOutputStream(Directory));
        openPDF();
        document.add(new Chunk(""));
        PdfContentByte contentByte = writer.getDirectContent();
        PdfTemplate template = contentByte.createTemplate(panel.getWidth(), panel.getHeight());
        Graphics2D g2 = template.createGraphics(panel.getWidth(), panel.getHeight());
        panel.print(g2);
        g2.dispose();
        contentByte.addTemplate(template, 0, 0);
        closePDF();
    }

    public void addNewPage()
    {
        document.newPage();
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

    public void addEmptyLine(int number) throws DocumentException
    {
        Paragraph paragraph = new Paragraph();
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" ", getNormalStyle()));
            document.add(paragraph);
        }
    }
}
