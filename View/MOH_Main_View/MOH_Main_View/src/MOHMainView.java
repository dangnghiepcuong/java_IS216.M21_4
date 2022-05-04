import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MOHMainView extends JFrame implements ActionListener
{
    private DefaultValue dv = new DefaultValue();
    private JPanel InfoPanel;
    private JLabel InfoLabel;
    private JLabel NameLabel;
    private JLabel LocationLabel;

    private JPanel FeaturePanel;
    private JButton InfoSettingButton;
    private JButton CreateOrgAccButton;
    private JButton PublishPostButton;
    private JButton SearchButton;
    private JButton StatisticButton;

    private JButton LogoutButton;

    private void initInfoPanel()
    {
        InfoPanel = new JPanel();

        InfoPanel.setBounds(0,0,dv.FrameWidth()-dv.FrameHeigth(), dv.FieldHeigth());

        InfoPanel.setPreferredSize(new Dimension(dv.FrameWidth()-dv.FrameHeigth(), dv.FieldHeigth()));

    }
    private void initInfoLabel(){}
    private void initNameLabel(){}
    private void initLocationLabel(){}

    private void initFeaturePanel(){}
    private void initInfoSettingButton(){}
    private void initCreateOrgAccButton(){}
    private void initPublishPostButton(){}
    private void initSearchButton(){}
    private void initStatisticButton(){}

    private void initLogoutButton(){}

    public MOHMainView()
    {

    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }

}
