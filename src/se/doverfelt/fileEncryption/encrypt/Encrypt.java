package se.doverfelt.fileEncryption.encrypt;

import se.doverfelt.fileEncryption.Start;

import javax.swing.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * © Rickard Doverfelt 2013
 */
public class Encrypt extends Thread {

    public static byte[] encrypt(byte[] in, String key) {
        Logger.getLogger(Start.ENCRYPTNAME).log(Level.INFO, "Encryption Started!");
        byte[] out = in;
        char[] keys = key.toCharArray();

        Start.TOTAL = out.length * keys.length;

        for (int i = 0; i < in.length ; i++) {
            for (int j = 0; j < keys.length; j++) {
                out[i] = ((byte)(out[i] + keys[j]));
                Start.PROGRESS++;
            }
        }

        Start.TOTAL = 0;
        Start.PROGRESS = 0;
        
        Logger.getLogger(Start.ENCRYPTNAME).log(Level.INFO, "Encryption Done!");
        
        return out;
    }

    public static void encryptFile(String in, String out, String key) {
Path file = Paths.get(in);

        try {
            if (Files.size(file) > ((long)((1024*1024)*1024)*2)) {
                JOptionPane.showMessageDialog(null, "File size exceeds 2Gib");
                return;
            }
        } catch (IOException e1) {
            e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        byte[] fileArray = null;
        try {
            fileArray = Files.readAllBytes(file);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        Path fileOut = Paths.get(out);
        try {
            Files.write(fileOut, Encrypt.encrypt(fileArray, key));
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }


    @Override
    public void run() {
        encryptFile(Start.RAW, Start.ENCRYPTED, Start.KEY);
    }
}
