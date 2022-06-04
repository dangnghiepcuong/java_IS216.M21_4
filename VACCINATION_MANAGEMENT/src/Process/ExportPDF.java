package Process;

import com.lowagie.text.Font;
import com.lowagie.text.Rectangle;
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;

import javax.swing.*;
import java.awt.*;
import java.awt.print.PageFormat;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.*;
import java.util.Scanner;

public class ExportPDF extends Component {
    private DefaultValue dv = new DefaultValue();
    private String Directory;
    private Document document = new Document(PageSize.A4);

    private static Font catFont = new Font(Font.TIMES_ROMAN, 18,
            Font.BOLD);
    private static Font redFont = new Font(Font.TIMES_ROMAN, 12,
            Font.NORMAL, Color.RED);
    private static Font subFont = new Font(Font.TIMES_ROMAN, 16,
            Font.BOLD);
    private static Font smallBold = new Font(Font.TIMES_ROMAN, 12,
            Font.BOLD);

    public static final int A0 = 0;
    public static final int A1 = 1;
    public static final int A2 = 2;
    public static final int A3 = 3;
    public static final int A4 = 4;
    public static final int A5 = 5;
    public static final int A6 = 6;
    public static final int A7 = 7;
    public static final int A8 = 8;
    public static final int LETTER = 11;

    public ExportPDF() {
        document.setPageSize(new Rectangle(595,1684));
    }

    public ExportPDF(int size) {
        document.resetHeader();
        HeaderFooter header = new HeaderFooter(new Phrase("header"), false);
        header.setAlignment(Element.ALIGN_LEFT);
        header.setBorder(Rectangle.NO_BORDER);
        document.setHeader(header);
        switch (size)
        {
            case 0:
                document = new Document(PageSize.A0);
                break;
            case 1:
                document = new Document(PageSize.A1);
                break;
            case 2:
                document = new Document(PageSize.A2);
                break;
            case 3:
                document = new Document(PageSize.A3);
                break;
            case 4:
                document = new Document(PageSize.A4);
                break;
            case 5:
                document = new Document(PageSize.A5);
                break;
            case 6:
                document = new Document(PageSize.A6);
                break;
            case 7:
                document = new Document(PageSize.A7);
                break;
            case 8:
                document = new Document(PageSize.A8);
                break;
            case 11:
                document = new Document(PageSize.LETTER);
                break;
            default:
                document = new Document(PageSize.A4);
                break;
        }
    }

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

    public void selectFile()
    {

    }

    private Font normalStyle = new Font(Font.TIMES_ROMAN, 13,Font.NORMAL, new Color(dv.BlackTextColor()));
    private Font titleStyle = new Font(Font.TIMES_ROMAN, 18,Font.NORMAL, new Color(dv.FeatureButtonColor()));

    public Font getNormalStyle() {
        return normalStyle;
    }

    public Font getTitleStyle() {
        return titleStyle;
    }


    public void addMetaData(String title, String subject, String keywords, String author, String creator) {
        document.addTitle(title);
        document.addSubject(subject);
        document.addKeywords(keywords);
        document.addAuthor(author);
        document.addCreator(creator);
    }

    public void addParagraph(String text, Font font) throws DocumentException, FileNotFoundException {
        document.add(new Paragraph(text, font));
    }

    public void addParagraph(Paragraph paragraph) throws DocumentException, FileNotFoundException {
        document.add(paragraph);
    }

    public void addPage(PagePanel panel, int pageIndex) throws DocumentException, FileNotFoundException, PrinterException {
        PrinterJob printJob = PrinterJob.getPrinterJob();
        PageFormat pf = printJob.defaultPage();

        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(getDirectory()));
        openPDF();

        PdfContentByte contentByte = writer.getDirectContent();
        document.add(new Chunk(""));
        PdfTemplate template = contentByte.createTemplate(panel.getWidth(), panel.getHeight());
        Graphics2D g2 = template.createGraphicsShapes(panel.getWidth(), panel.getHeight());
        panel.print(g2, pf, pageIndex);
        g2.dispose();
        contentByte.addTemplate(template,0,0);

        /*PdfTemplate tp;
        Graphics2D g2;
        // Assuming that the number of pages would be 3, You can calculate this same way it is calculated
        // in print (Graphics g, PageFormat pageFormat, int pageIndex) method
        tp = contentByte.createTemplate(panel.getWidth(), panel.getHeight());
        g2 = tp.createGraphicsShapes(panel.getWidth(), panel.getHeight());
        panel.print(g2, pf, page);
        g2.dispose();
        contentByte.addTemplate(tp, 0, 0);*/
    }

    public PdfWriter getPdfwriter2() throws FileNotFoundException, DocumentException {
        return PdfWriter.getInstance(document, new FileOutputStream("C:\\Users\\operator\\Desktop\\123.pdf"));
    }

    public void addPage(JPanel panel, int pageIndex) throws DocumentException, IOException {

        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(getDirectory()));
//        PdfReader reader = new PdfReader("C:\\Users\\operator\\Desktop\\123.pdf");
//        PdfImportedPage page = writer.getImportedPage(reader, pageIndex);
        openPDF();

        PdfContentByte contentByte = writer.getDirectContent();
        document.add(new Chunk(""));
        PdfTemplate template = contentByte.createTemplate(panel.getWidth(), panel.getHeight());
        Graphics2D g2 = template.createGraphicsShapes(panel.getWidth(), panel.getHeight());
        panel.print(g2);
        g2.dispose();
        int temp = (pageIndex-1)*panel.getHeight();
        contentByte.addTemplate(template,0,-(pageIndex-1)*panel.getHeight());

        /*PdfContentByte contentByte = writer.getDirectContent();
        document.add(new Chunk(""));
        Graphics2D g2 = page.createGraphicsShapes(panel.getWidth(), panel.getHeight());
        panel.print(g2);
        g2.dispose();
        contentByte.addTemplate(page,1, 0, 0, 1,0,0);*/
    }

    public void addEmptyLine(int number) throws DocumentException, FileNotFoundException
    {
        Paragraph paragraph = new Paragraph();
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph("*", getNormalStyle()));
            document.add(paragraph);
        }
    }

    public void addNewPage() throws DocumentException, FileNotFoundException {
        document.newPage();
        document.add(new Chunk(""));
    }
}
