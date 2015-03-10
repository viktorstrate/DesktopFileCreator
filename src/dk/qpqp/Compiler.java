package dk.qpqp;


import java.io.*;

/**
 * Created by viktorstrate on 3/9/15.
 * This file compiles the inputs to a .desktop file
 */
public class Compiler {
    private MainWindow main;
    public Compiler(MainWindow main){
        this.main = main;
    }

    public boolean compile() {
        String output = "";
        output += "[Desktop Entry]\nEncoding=UTF-8\n";

        // Type
        output += "Type="+main.comboType.getSelectedItem().toString()+"\n";

        if(main.txtName.getText().equals("")){// Name
            System.out.println("Name not set, can't compile");
            return false;
        }
        output += "Name="+main.txtName.getText()+"\n";

        if(!main.txtExe.getText().equals("")) {// Executable
            output += "Exec=" + main.txtExe.getText() + "\n";
        } else {
            System.out.println("Execute not set, can't compile");
            return false;
        }

        if(!main.txtIcon.getText().equals("")) // Icon
            output += "Icon="+main.txtIcon.getText()+"\n";

        // Description (comment)
        if(!main.txtDesc.getText().equals("")){
            output+="Comment="+main.txtDesc.getText();
        }

        // Categories
        output += "Categories=";
        for(int i = 0; i < main.listCategories.getSelectedValuesList().size(); i++){
            output += main.listCategories.getSelectedValuesList().get(i).toString()+";";
        }
        output += "\n";

        // Keywords
        String keywords = main.txtKeywords.getText();
        keywords = keywords.replace(", ", ";");
        keywords = keywords.replace(",",";");
        output += "Keywords="+keywords+";\n";

        // Version
        if(!main.txtVersion.getText().equals("")){
            output+="Version="+main.txtVersion.getText()+"\n";
        }

        // Generic name
        if(!main.txtGenericName.getText().equals("")){
            output+="GenericName="+main.txtGenericName.getText()+"\n";
        }

        // No display
        if(main.comboNoDisplay.getSelectedItem().toString().equals("True")){
            output+="NoDisplay=true\n";
        }

        // Hidden
        if(main.comboHidden.getSelectedItem().toString().equals("True")){
            output+="Hidden=true\n";
        }

        // DBusActivatable
        if(main.comboDBus.getSelectedItem().toString().equals("True")){
            output+="DBusActivatable=true\n";
        }

        // Show terminal
        if(main.comboTerminal.getSelectedItem().toString().equals("True")){
            output+="Terminal=true\n";
        }

        // Try execute
        if(!main.txtTryExe.getText().equals("")){
            output+="TryExec="+main.txtTryExe.getText()+"\n";
        }

        // Path
        if(!main.txtPath.getText().equals("")){
            output+="Path="+main.txtPath.getText()+"\n";
        }

        // Startup notify
        if(main.comboStartupNotify.getSelectedItem().toString().equals("True")){
            output+="StartupNotify=true\n";
        }

        // Startup WMClass
        if(!main.txtWM.getText().equals("")){
            output+="StartupWMClass="+main.txtWM.getText()+"\n";
        }

        // Writes it to a file
        try {
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(main.txtOutput.getText()), "utf-8"));
            writer.write(output);
            writer.close();
            System.out.println("Wrote file:");
            System.out.println(output);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Makes it executable
        try {
            Runtime r = Runtime.getRuntime();
            Process p = null;
            p = r.exec("chmod +x "+main.txtOutput.getText());
            p.waitFor();
            BufferedReader b = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line = "";

            while ((line = b.readLine()) != null) {
                System.out.println(line);
            }

            b.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e){
            e.printStackTrace();
        }

        return true;
    }
}
