package Process;

import javax.swing.*;
import java.awt.*;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;

public class PagePanel extends JPanel implements Printable
{
    @Override
    public int print(Graphics g, PageFormat pageFormat, int pageIndex) throws PrinterException
    {
        Graphics2D g2d = (Graphics2D) g;
//        g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
        g2d.translate(0, (pageIndex-1)*this.getHeight());
        this.paint(g2d);

        return (PAGE_EXISTS);
    }
}
