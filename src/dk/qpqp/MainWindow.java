package dk.qpqp;

import javax.swing.*;
import java.awt.*;

/**
 * Created by viktorstrate on 3/9/15.
 */
public class MainWindow extends JFrame {
    private JTextField txtName;
    private JTextField txtDesc;
    private JTextField txtIcon;
    private JButton btnIcon;
    private JTextField txtExe;
    private JButton btnExe;
    private JList listCategories;
    private JTextField txtKeywords;
    private JComboBox comboTerminal;
    private JTabbedPane tabbedPane;
    private JTextField txtOutput;
    private JButton btnOutput;
    private JButton btnGenerate;
    private JTextField txtType;
    private JTextField txtVersion;
    private JPanel mainPanel;
    private JPanel basicPanel;
    private JLabel labelName;
    private JLabel labelDesc;
    private JLabel labelIcon;
    private JLabel labelExe;
    private JLabel labelCategories;
    private JLabel labelKeywords;
    private JLabel labelTerminal;
    private JPanel panelAdvanced;
    private JLabel labelType;
    private JLabel labelVersion;
    private JLabel labelOutput;

    public MainWindow() throws HeadlessException {
        super("Desktop File Creator");
        pack();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
}
