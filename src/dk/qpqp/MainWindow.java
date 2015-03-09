package dk.qpqp;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

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
    private JTabbedPane tabbedPane;
    private JTextField txtOutput;
    private JButton btnOutput;
    private JButton btnGenerate;
    private JTextField txtVersion;
    private JPanel mainPanel;
    private JPanel basicPanel;
    private JLabel labelName;
    private JLabel labelDesc;
    private JLabel labelIcon;
    private JLabel labelExe;
    private JLabel labelCategories;
    private JLabel labelKeywords;
    private JPanel panelAdvanced;
    private JLabel labelType;
    private JLabel labelVersion;
    private JLabel labelOutput;
    private JComboBox comboType;
    private JLabel labelURL;
    private JTextField txtURL;
    private JTextField txtGenericName;
    private JComboBox comboNoDisplay;
    private JComboBox comboHidden;
    private JComboBox comboDBus;
    private JTextField txtTryExe;
    private JButton btnTryExe;
    private JTextField txtPath;
    private JLabel labelTerminal;
    private JComboBox comboTerminal;
    private JTextField txtWM;
    private JLabel imgIcon;
    private JLabel labelGenericName;
    private JLabel labelNoDisplay;
    private JLabel labelHidden;
    private JLabel labelDBus;
    private JLabel labelTryExe;
    private JLabel labelPath;
    private JButton btnPath;
    private JLabel labelStartupNotify;
    private JComboBox comboStartupNotify;
    private JLabel labelWM;
    private BufferedImage image;

    public MainWindow() throws HeadlessException {
        super("Desktop File Creator");
        init();
        pack();
        setSize(750, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setupListeners();

    }

    public void init(){
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
            try {
                image = ImageIO.read(file);
                Image scaledImage = image.getScaledInstance(64, 64, Image.SCALE_DEFAULT);
                imgIcon.setIcon(new ImageIcon(scaledImage));
                revalidate();
                repaint();
                getContentPane().repaint();
            } catch (IOException e) {
                e.printStackTrace();
            }
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

                return file.getName().toLowerCase().endsWith(".desktop") ||
                        file.isDirectory();
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

    private void createUIComponents() {
        try {
            File icon = new File(getClass().getClassLoader().getResource("icon.png").getFile());
            image = ImageIO.read(icon);
            Image scaledImage = image.getScaledInstance(64, 64, Image.SCALE_DEFAULT);
            imgIcon = new JLabel(new ImageIcon(scaledImage));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException e){
            e.printStackTrace();
        }

        String[] listItems = {"Audio", "Video",
                "Development", "Education", "Game", "Graphics",
                "Network", "Office", "Science", "Settings",
                "System", "Utility"};
        listCategories = new JList(listItems);
        listCategories.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        listCategories.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        listCategories.setVisibleRowCount(-1);
    }
}
