package org.joget.marketplace;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import org.joget.apps.app.model.DefaultHashVariablePlugin;

public class FormatNumberHashVariable extends DefaultHashVariablePlugin {

    @Override
    public String processHashVariable(String variableKey) {
        String formattedNumber = "";
        DecimalFormat decimalFormat = new DecimalFormat();
        DecimalFormatSymbols customSymbols = new DecimalFormatSymbols();
        if (variableKey.indexOf("[") == -1) {
            return variableKey;
        } else {
            String numberString = variableKey.substring(0, !variableKey.contains("[") ? variableKey.length() - 1 : variableKey.indexOf("["));
            String options = variableKey.substring(variableKey.indexOf("["));
            int decimalPlaces = 0;
            options = options.replaceAll("\\[|\\]", "");
            String[] pieces = options.split("&");
            for (String opt : pieces) {
                if (opt.contains("decimal=")) {
                    try {
                        decimalPlaces = Integer.parseInt(opt.substring(opt.indexOf("=") + 1));
                        decimalFormat.setMaximumFractionDigits(decimalPlaces);
                        decimalFormat.setMinimumFractionDigits(decimalPlaces);
                        customSymbols.setGroupingSeparator('\0');
                        decimalFormat.setDecimalFormatSymbols(customSymbols);
                    } catch (NumberFormatException e) {

                    }

                } else if (opt.contains("thousandSeparator=")) {
                    String separator = opt.substring(opt.indexOf("=") + 1);
                    if (separator != null && !separator.isEmpty()) {
                        customSymbols.setGroupingSeparator(separator.charAt(0));
                        decimalFormat.setDecimalFormatSymbols(customSymbols);
                    }
                }
            }
            formattedNumber = numberString;
            formattedNumber = decimalFormat.format(Double.parseDouble(formattedNumber));
        }
        return formattedNumber;
    }

    @Override
    public String getPrefix() {
        return "formatNumber";
    }

    @Override
    public String getName() {
        return "Format Number Hash Variable";
    }

    @Override
    public String getVersion() {
        return "8.0.0";
    }

    @Override
    public String getDescription() {
        return "Format Number Hash Variable";
    }

    @Override
    public String getLabel() {
        return "Format Number Hash Variable";
    }

    @Override
    public String getClassName() {
        return this.getClass().getName();
    }

    @Override
    public String getPropertyOptions() {
        return "";
    }

}
