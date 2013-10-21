package se.doverfelt.fileEncryption;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import se.doverfelt.fileEncryption.encrypt.Encrypt;
import se.doverfelt.fileEncryption.gui.Gui;

public class Start {

    public static boolean ENCRYPT = false;
    public static String KEY = "";
    public static String RAW = "";
    public static String ENCRYPTED = "";
    
    public static Encrypt enc;

    public static Gui gui;
    public static int TOTAL = 0;
    public static int PROGRESS = 0;
    public static String VERSION = "1.0-SNAPSHOT";
    public static String GUINAME = "[Gui]";
    public static String ENCRYPTNAME = "[Encrypter]";

    public static void main(String[] args) {
        
        enc = new Encrypt();
        enc.setDaemon(true);
        
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                Logger.getLogger(Start.GUINAME).log(Level.INFO, info.getName());
                if ("Nimbus".equals(info.getName())) {
                    Logger.getLogger(Start.GUINAME).log(Level.INFO, "Found Nimbus!");
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Start.GUINAME).log(Level.SEVERE, null, ex);
        }
        
        gui = new Gui();
        while (true) {
            gui.update();
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                Logger.getLogger(Gui.class.getName()).log(Level.SEVERE, null, e);
            }
        }

    }

}