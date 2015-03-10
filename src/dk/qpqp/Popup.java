package dk.qpqp;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.io.File;

/**
 * Created by viktorstrate on 3/10/15.
 * This file is a template for the popup windows
 */
public class Popup extends JFileChooser {

    /**
     * Initializes the popup
     * @param title The title of the popup
     */
    public Popup(String title){
        super();
        this.setDialogTitle(title);
    }

    /**
     * Initializes the popup
     * @param title The title of the popup
     * @param defaultPath The default file path of the popup window
     */
    public Popup(String title, String defaultPath){
        super(defaultPath);
        this.setDialogTitle(title);
    }

    /**
     *
     * @param desc The description of the filter
     * @param extensions Allowed extension fx. "png", "jpg"
     */
    public void addFilter(final String desc, final String[] extensions){
        this.addChoosableFileFilter(new FileFilter() {
            @Override
            public boolean accept(File file) {
                if (file.isDirectory()) return true;
                for (String ext : extensions) {
                    if (file.getName().toLowerCase().endsWith("." + ext)) return true;
                }
                return false;
            }

            @Override
            public String getDescription() {
                return desc;
            }
        });
    }

    /**
     * Shows the popup on the screen with the preset to open files
     * @param popupListener a PopupListener class to listen on success or cancel
     * @param frame the popups parent, default the Main JFrame
     */
    public void showOpenDialog(PopupListener popupListener, JFrame frame){
        if (this.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION) {
            popupListener.popupSuccessful(this.getSelectedFile());
        } else if(this.showOpenDialog(frame) == JFileChooser.CANCEL_OPTION){
            popupListener.popupCanceled();
        }
    }

    /**
     * Shows the popup on the screen with the preset to save files
     * @param popupListener a PopupListener class to listen on success or cancel
     * @param frame the popups parent, default the Main JFrame
     */
    public void showSaveDialog(PopupListener popupListener, JFrame frame){
        if (this.showSaveDialog(frame) == JFileChooser.APPROVE_OPTION) {
            popupListener.popupSuccessful(this.getSelectedFile());
        } else if(this.showOpenDialog(frame) == JFileChooser.CANCEL_OPTION){
            popupListener.popupCanceled();
        }
    }

}
