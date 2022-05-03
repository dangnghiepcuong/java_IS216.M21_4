import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class LargePanel extends JPanel
{
    private ArrayList<JPanel> OrgPanel;
    private DefaultValue dv = new DefaultValue();

    private void initOrgPanel(int i)
    {
        //Org info
        JLabel OrgName = new JLabel("Ten don vi " + i);

        OrgName.setFont(new Font("SVN-Arial", 0, 18));

        OrgName.setBounds(0,0,300,30);

        OrgName.setHorizontalAlignment(JLabel.LEFT);

        OrgName.setBorder(dv.border());

        //create OrgPanel Panel
        //OrgPanel.get(i) = new JPanel();

        //set layout
        //OrgPanel.get(i).setLayout(null);

        OrgPanel.get(i).setBounds(0,120*i + i*2,660, 120);

        //set Background color
        OrgPanel.get(i).setBackground(Color.WHITE);

        OrgPanel.get(i).add(OrgName);
    }

    public LargePanel()
    {
        this.setLayout(null);

        for (int i = 0; i < 30; i++)
        {
            initOrgPanel(i);
            this.add(OrgPanel.get(i));
        }

    }

    public LargePanel(ArrayList<JPanel> ORGPanel)
    {
        this.setLayout(null);

        OrgPanel = ORGPanel;

        for (int i = 0; i < ORGPanel.size()-1; i++)
            this.add(OrgPanel.get(i));
    }

    @Override
    public Dimension getPreferredSize()
    {
        return new Dimension( 680,630 );
    }
}
