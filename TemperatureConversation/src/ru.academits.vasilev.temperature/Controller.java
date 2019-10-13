package ru.academits.vasilev.temperature;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Controller {
    private View view;
    private Model model;

    private String inputText;

    public Controller(View v) {
        view = v;
        initController();
    }

    private void initController() {
        view.getInputTemperatureField().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                char c = e.getKeyChar();

                if (Character.isDigit(c)) {
                    inputText = String.valueOf(c);
                    System.out.println(inputText);
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                char c = e.getKeyChar();

                if (!Character.isDigit(c)) {
                    view.getInputTemperatureField().setText("");
                }
            }
        });

        view.getConvertButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(view.getRadioGroupFrom().getSelection().getActionCommand());
                System.out.println(view.getRadioGroupTo().getSelection().getActionCommand());
            }
        });
    }
}
