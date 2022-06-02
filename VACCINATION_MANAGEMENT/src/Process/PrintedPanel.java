package Process;

import javax.swing.*;
import java.awt.*;

public class PrintedPanel
{
    private DefaultValue dv = new DefaultValue();
    private JPanel printedPanel;
    private int width = 595;
    private int height = 842;
    private int topAlign = cmToPixel(1);
    private int leftAlign = cmToPixel(1);

    public static final int NORMAL = 0;
    public static final int TITLE = 1;
    public static final int NOTE = 2;

    private Font normalFont = new Font("Times New Roman", 0, 16);
    private Font titleFont = new Font("Times New Roman", 1, 16);
    private Font noteFont = new Font("Times New Roman", 2, 13);

    public PrintedPanel()
    {
        printedPanel = new JPanel();
        printedPanel.setLayout(null);
    }

    public PrintedPanel(int w, int h)
    {
        setWidth(w);
        setHeight(h);
        printedPanel = new JPanel();
        printedPanel.setLayout(null);
        printedPanel.setBounds(0,0,getWidth(),getHeight());
    }

    public JPanel getPrintedPanel()
    {
        printedPanel.setBounds(0,0,getWidth(),getHeight());
//        printedPanel.setBackground(Color.WHITE);
        JPanel BackgroundColor = new JPanel();
        BackgroundColor.setBounds(0,0,getWidth(),getHeight());
        BackgroundColor.setBackground(Color.WHITE);

        ImageIcon ImageIconPanel = new ImageIcon(ImageHelper.reSize(ImageHelper.createImage(BackgroundColor), getWidth(),getHeight()));

        JLabel addedLabel = new JLabel();
        addedLabel.setBounds(0,0, getWidth(),getHeight());
        addedLabel.setIcon(ImageIconPanel);
//        addedLabel.setBorder(dv.border());

        printedPanel.add(addedLabel);

        return printedPanel;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getTopAlign() {
        return topAlign;
    }

    public void setTopAlign(int topAlign) {
        this.topAlign = cmToPixel(topAlign);
    }

    public int getLeftAlign() {
        return leftAlign;
    }

    public void setLeftAlign(int leftAlign) {
        this.leftAlign = cmToPixel(leftAlign);
    }

    private int cmToPixel(float cm)
    {
        return (int) (cm*28.346456693);
    }

    /*EXPORT TO STATISTIC IMAGE PANEL*/
    public void addPanel(JPanel panel, float xf, float yf, float wf, float hf)
    {
        int x = cmToPixel(xf);
        int y = cmToPixel(yf);
        int w = cmToPixel(wf);
        int h = cmToPixel(hf);
        
        panel.setOpaque(false);

        ImageIcon ImageIconPanel = new ImageIcon(ImageHelper.reSize(ImageHelper.createImage(panel), w, h));

        JLabel addedLabel = new JLabel();
        addedLabel.setBounds(x + leftAlign, y + topAlign, w, h);
        addedLabel.setIcon(ImageIconPanel);
//        addedLabel.setBorder(dv.border());

        printedPanel.add(addedLabel);
    }

    public void addLabel(JLabel label, int style, float xf, float yf, float wf, float hf)
    {
        int x = cmToPixel(xf);
        int y = cmToPixel(yf);
        int w = cmToPixel(wf);
        int h = cmToPixel(hf);

        label.setBounds(x, y, w, h);
        label.setOpaque(true);
        label.setBackground(Color.WHITE);

        if (style == 0)
            label.setFont(normalFont);
        if (style == 1)
            label.setFont(titleFont);
        if (style == 2)
            label.setFont(noteFont);

        ImageIcon ImageIconLabel = new ImageIcon(ImageHelper.reSize(ImageHelper.createImage(label), w, h));
        JLabel addedLabel = new JLabel(ImageIconLabel);

        addedLabel.setBounds(x + leftAlign, y + topAlign, w, h);
//        addedLabel.setBorder(dv.border());

        printedPanel.add(addedLabel);
    }
}
