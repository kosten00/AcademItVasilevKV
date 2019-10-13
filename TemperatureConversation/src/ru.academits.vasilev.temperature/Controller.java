package ru.academits.vasilev.temperature;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Controller {
    private View view;
    private Model model;

    private String fromScale;
    private String toScale;

    private StringBuilder stringBuilder;

    public Controller(View view, Model model) {
        this.view = view;
        this.model = model;
        initController();
        stringBuilder = new StringBuilder();
    }

    private void initController() {
        view.getInputTemperatureField().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
                char c = e.getKeyChar();

                if (!Character.isDigit(c)) {
                    view.getInputTemperatureField().setText("");
                    stringBuilder.replace(0, stringBuilder.length(), "");
                }

                if (Character.isDigit(c)) {
                    stringBuilder.append(c);
                    System.out.println(stringBuilder);
                }
            }
        });

        view.getConvertButton().addActionListener(e -> {
            //stringBuilder.toString();
            System.out.println(view.getRadioGroupFrom().getSelection().getActionCommand());
            System.out.println(view.getRadioGroupTo().getSelection().getActionCommand());

            stringBuilder.replace(0, stringBuilder.length(), "");
            view.getInputTemperatureField().setText("");
        });


    }
}
