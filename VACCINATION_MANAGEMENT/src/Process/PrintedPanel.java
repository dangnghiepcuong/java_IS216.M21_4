package Process;

import javax.swing.*;
import java.awt.*;

public class PrintedPanel
{
    private DefaultValue dv = new DefaultValue();
    private JPanel printedPanel;
    private int width = 595;
    private int height = 842;

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

    public JPanel getPrintedPanel()
    {
        printedPanel.setBounds(0,0,getWidth(),getHeight());
        printedPanel.setBackground(Color.WHITE);
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
        
//        panel.setBackground(Color.WHITE);
        panel.setOpaque(false);

        ImageIcon ImageIconPanel = new ImageIcon(ImageHelper.reSize(ImageHelper.createImage(panel), w, h));

        JLabel addedLabel = new JLabel();
        addedLabel.setBounds(x, y, w, h);
        addedLabel.setIcon(ImageIconPanel);
        addedLabel.setBorder(dv.border());

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

        addedLabel.setBounds(x, y, w, h);
        addedLabel.setBorder(dv.border());

        printedPanel.add(addedLabel);
    }
}
