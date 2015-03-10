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
 * This is the Main windows file, from here the window gets created and all functions is created here.
 */
public class MainWindow extends JFrame {

    protected JTextField txtName;
    protected JTextField txtDesc;
    protected JTextField txtIcon;
    protected JButton btnIcon;
    protected JTextField txtExe;
    protected JButton btnExe;
    protected JList listCategories;
    protected JTextField txtKeywords;
    protected JTabbedPane tabbedPane;
    protected JTextField txtOutput;
    protected JButton btnOutput;
    protected JButton btnGenerate;
    protected JTextField txtVersion;
    protected JPanel mainPanel;
    protected JPanel basicPanel;
    protected JLabel labelName;
    protected JLabel labelDesc;
    protected JLabel labelIcon;
    protected JLabel labelExe;
    protected JLabel labelCategories;
    protected JLabel labelKeywords;
    protected JPanel panelAdvanced;
    protected JLabel labelType;
    protected JLabel labelVersion;
    protected JLabel labelOutput;
    protected JComboBox comboType;
    protected JLabel labelURL;
    protected JTextField txtURL;
    protected JTextField txtGenericName;
    protected JComboBox comboNoDisplay;
    protected JComboBox comboHidden;
    protected JComboBox comboDBus;
    protected JTextField txtTryExe;
    protected JButton btnTryExe;
    protected JTextField txtPath;
    protected JLabel labelTerminal;
    protected JComboBox comboTerminal;
    protected JTextField txtWM;
    protected JLabel imgIcon;
    protected JLabel labelGenericName;
    protected JLabel labelNoDisplay;
    protected JLabel labelHidden;
    protected JLabel labelDBus;
    protected JLabel labelTryExe;
    protected JLabel labelPath;
    protected JButton btnPath;
    protected JLabel labelStartupNotify;
    protected JComboBox comboStartupNotify;
    protected JLabel labelWM;

    private BufferedImage image;
    private Compiler compiler;

    private Popup popupIconBrowse, popupExecPath, popupTryExec, popupPathBrowse, popupOutput;

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
        compiler = new Compiler(this);

        // Initializes the popups
        popupExecPath = new Popup("Add file / path to execution command");

        popupIconBrowse = new Popup("Open icon");
        String[] iconExtensions = {"png", "svg", "svgz"};
        popupIconBrowse.addFilter("Allowed file formats", iconExtensions);

        popupOutput = new Popup("Where to save the desktop file", System.getProperty("user.home")+"/.local/share/applications");
        String[] outputExtensions = {"desktop"};
        popupOutput.addFilter(".desktop extension", outputExtensions);

        popupPathBrowse = new Popup("Open default path for application");
        popupTryExec = new Popup("Open try execute");

    }

    /**
     * Sets up all the input listeners for the MainWindow like button inputs
     */
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
        btnGenerate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                compiler.compile();
            }
        });
        btnTryExe.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                showTryExecPopup();
            }
        });
    }

    public void showIconSelectPopup(){
        popupIconBrowse.showOpenDialog(new PopupListener() {
            @Override
            public void popupSuccessful(File file) {
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

            @Override
            public void popupCanceled() {
            }
        }, MainWindow.this);
    }

    public void showExecSelectPopup(){
        popupExecPath.showOpenDialog(new PopupListener() {
            @Override
            public void popupSuccessful(File file) {
                txtExe.setText(txtExe.getText() + file.toString());
            }

            @Override
            public void popupCanceled() {

            }
        }, MainWindow.this);
    }

    public void showOutputSelectPopup(){
        popupOutput.showSaveDialog(new PopupListener() {
            @Override
            public void popupSuccessful(File file) {
                if(!file.toString().toLowerCase().endsWith(".desktop")){
                    txtOutput.setText(file.toString()+".desktop");
                } else txtOutput.setText(file.toString().substring(0, file.toString().length()-8)+".desktop");
            }

            @Override
            public void popupCanceled() {

            }
        }, MainWindow.this);
    }

    public void showTryExecPopup(){
        popupTryExec.showOpenDialog(new PopupListener() {
            @Override
            public void popupSuccessful(File file) {
                txtTryExe.setText(file.getAbsoluteFile().toString());
            }

            @Override
            public void popupCanceled() {

            }
        }, MainWindow.this);
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
