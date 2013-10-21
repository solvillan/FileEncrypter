package se.doverfelt.fileEncryption.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import  se.doverfelt.fileEncryption.encrypt.Encrypt;

/**
 * © Rickard Doverfelt 2013
 */
public class GuiOrig extends JFrame{


    public JLabel label = new JLabel();
    public JTextField raw = new JTextField();
    public JTextField enc = new JTextField();
    public JTextField key = new JTextField();
    public JButton encBtn = new JButton();
    public JProgressBar progressBar = new JProgressBar(SwingConstants.HORIZONTAL, 0, 100);
    private boolean shouldUpdate = false;

    public GuiOrig() {
        this.setSize(500, 155);
        this.setTitle("KeyCryption 1.0");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);

        this.setLayout(new BorderLayout());

        label.setText("© 2013 Rickard Doverfelt & Joel Jonsson Rapp");
        //label.setLocation(0, 120);
        label.setVisible(true);

        //enc.setEditable(false);
        enc.setSize(380, 24);
        enc.setLocation(95, 45);
        enc.setVisible(true);
        enc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog d = new JDialog();
                FileDialog dialog = new FileDialog(d);
                dialog.show();
                enc.setText(dialog.getDirectory() + dialog.getFile());
            }
        });
        enc.setEnabled(true);

        raw.setSize(465, 25);
        raw.setVisible(true);
        raw.setLocation(10, 10);
        raw.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog d = new JDialog();
                FileDialog dialog = new FileDialog(d);
                dialog.show();
                raw.setText(dialog.getDirectory() + dialog.getFile());
            }
        });
        raw.setEnabled(true);

        key.setSize(75, 25);
        key.setLocation(10, 45);
        key.setVisible(true);
        key.setEnabled(true);

        encBtn.setSize(100, 25);
        encBtn.setLocation((485 / 2) - 50, 80);
        encBtn.setText("Encrypt");
        encBtn.setVisible(true);
        encBtn.setEnabled(false);

        encBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                System.out.println("Button pressed!");

                (new Thread(new Encrypt())).start();

            }
        });

        progressBar.setSize(100, 25);
        progressBar.setLocation(10, 80);
        progressBar.setLayout(null);
        progressBar.setVisible(true);
        progressBar.setEnabled(true);

        this.add(encBtn);
        this.add(progressBar);
        this.add(raw);
        this.add(key);
        this.add(enc);
        this.add(new JLabel());
        this.add(label, BorderLayout.SOUTH);

    }

    public void update() {
        repaint();

        //progressBar.setMaximum(Encrypt.total);
        //progressBar.setValue(Encrypt.done);

        if (!raw.getText().isEmpty() && !key.getText().isEmpty() && !enc.getText().isEmpty()) {
            shouldUpdate = true;
        } else {
            encBtn.setEnabled(false);
        }

        if (shouldUpdate) {
            encBtn.setEnabled(true);
            shouldUpdate = false;
        }

        //if (key.getText().length() > 1) {
            //key.setText(key.getText().substring(0,1));
        //}

    }

}
