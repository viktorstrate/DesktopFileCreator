package dk.qpqp;

import javax.swing.*;
import javax.swing.filechooser.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

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
        init();
        pack();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setupListeners();

    }

    public void init(){
        listCategories.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        listCategories.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        listCategories.setVisibleRowCount(-1);
        setContentPane(mainPanel);
    }

    public void setupListeners(){
        btnIcon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                showIconSelectPopup();
            }
        });
        btnExe.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                showExecSelectPopup();
            }
        });
        btnOutput.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                showOutputSelectPopup();
            }
        });
    }

    public void showIconSelectPopup(){

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Select icon");
        fileChooser.addChoosableFileFilter(new FileFilter() {
            @Override
            public boolean accept(File file) {

                if(file.getName().toLowerCase().endsWith(".png") ||
                        file.getName().toLowerCase().endsWith(".svg") ||
                        file.getName().toLowerCase().endsWith(".svgz") ||
                        file.isDirectory()){
                    return true;
                } else return false;
            }

            @Override
            public String getDescription() {
                return "Compatible file extensions";
            }
        });
        if (fileChooser.showOpenDialog(MainWindow.this) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            txtIcon.setText(file.toString());
        }
    }

    public void showExecSelectPopup(){
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Select Executable file");
        if (fileChooser.showOpenDialog(MainWindow.this) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            txtExe.setText(txtExe.getText()+file.toString());
        }
    }

    public void showOutputSelectPopup(){
        JFileChooser fileChooser = new JFileChooser(System.getProperty("user.home")+"/.local/share/applications");
        fileChooser.setDialogTitle("Where to save the desktop file");
        fileChooser.addChoosableFileFilter(new FileFilter() {
            @Override
            public boolean accept(File file) {

                if(file.getName().toLowerCase().endsWith(".desktop") ||
                        file.isDirectory()){
                    return true;
                } else return false;
            }

            @Override
            public String getDescription() {
                return ".desktop file";
            }
        });
        if (fileChooser.showSaveDialog(MainWindow.this) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            if(!file.toString().toLowerCase().endsWith(".desktop")){
                txtOutput.setText(file.toString()+".desktop");
            } else txtOutput.setText(file.toString().substring(0, file.toString().length()-8)+".desktop");
        }
    }
}
