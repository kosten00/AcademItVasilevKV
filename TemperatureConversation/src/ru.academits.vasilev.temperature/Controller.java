package ru.academits.vasilev.temperature;

public class Controller {
    private View view;
    private Model model;
    private String typedText;

    public Controller(View view, Model model) {
        this.view = view;
        this.model = model;
        initController();
    }

    private void initController() {
        listenConvertButton();
    }

    private void listenConvertButton() {
        view.getConvertButton().addActionListener(e -> {
            typedText = view.getInputTemperatureField().getText();

            if (hasErrors()) {
                return;
            }
            sendToModel();
            modifyView(model.convert());
        });
    }

    private boolean hasErrors() {
        if (typedText.equals("")) {
            return true;
        }

        int commaSignIndex = typedText.indexOf(",");

        if (typedText.lastIndexOf(",") != commaSignIndex) {
            modifyView("Only one \",\" allowed");
            return true;
        }

        int dotSignIndex = typedText.indexOf(".");

        if (typedText.lastIndexOf(".") != dotSignIndex) {
            modifyView("Only one \".\" allowed");
            return true;
        }

        int minusSignIndex = typedText.indexOf("-");

        if (typedText.lastIndexOf("-") != minusSignIndex) {
            modifyView("Only one \"-\" allowed");
            return true;
        }

        for (int i = 0; i < typedText.length(); i++) {
            if (i == commaSignIndex || i == minusSignIndex || i == dotSignIndex) {
                continue;
            }

            if (!Character.isDigit(typedText.charAt(i))) {
                modifyView("Only digits allowed");
                return true;
            }
        }

        return false;
    }

    private void sendToModel() {
        double temperature = Double.parseDouble(typedText.replaceAll(",", "."));

        model.setInputTemperature(temperature);
        model.setFromScale(view.getRadioGroupFrom().getSelection().getActionCommand());
        model.setToScale(view.getRadioGroupTo().getSelection().getActionCommand());
    }

    private void modifyView(String text) {
        view.getInputTemperatureField().setText("");
        view.getOutputTemperatureField().setText(text);
    }
}
