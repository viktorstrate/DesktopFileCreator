package dk.qpqp;

import java.io.File;

/**
 * Created by viktorstrate on 3/10/15.
 */
public interface PopupListener {
    /**
     * Gets called when a file is selected
     * @param file The selected file
     */
    void popupSuccessful(File file);

    /**
     * Gets called if the user presses the cancel button
     */
    void popupCanceled();
}
