package dk.qpqp;


import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

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

        if(main.txtName.getText()=="") return false;
        output += "Name="+main.txtName.getText()+"\n";

        if(main.txtExe.getText()!="")
            output += "Exec="+main.txtExe.getText()+"\n";

        if(main.txtIcon.getText()!="")
            output += "Icon="+main.txtIcon.getText()+"\n";

        try {
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("filename.txt"), "utf-8"));
            writer.write(output);
            writer.close();
            System.out.println("Wrote file:");
            System.out.println(output);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return true;
    }
}
