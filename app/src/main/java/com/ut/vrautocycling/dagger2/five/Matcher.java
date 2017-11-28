package com.ut.vrautocycling.dagger2.five;

import java.util.ArrayList;
import java.util.List;

class Matcher {

    public static List<String> match(CharSequence inputStr, List<String> data) {
        final ArrayList<String> newValues = new ArrayList<>();
        if (inputStr == null || inputStr.length() == 0) {
            // 输入为空
            newValues.addAll(data);
        } else {
            final String input = inputStr.toString().trim().toLowerCase();

            final ArrayList<String> values;
            synchronized (Matcher.class) {
                values = new ArrayList<>(data);
            }

            final int count = values.size();

            for (int i = 0; i < count; i++) {
                final String value = values.get(i);
                final String valueText = value.toLowerCase();

                if (valueText.contains(input)) {
                    newValues.add(value);
                }
            }
        }
        return newValues;
    }
}