package com.parts.zn.five;

import android.support.annotation.IntDef;

import java.util.ArrayList;
import java.util.List;

class Matcher {

    @IntDef({START_WITH, EVERY_START_WITH, CONTAINS, END_WITH})
    public @interface MatchType {

    }

    public static final int START_WITH = 0;
    public static final int EVERY_START_WITH = 1;
    public static final int CONTAINS = 2;
    public static final int END_WITH = 3;

    public static List<String> match(@MatchType int type, CharSequence inputStr, List<String> data) {
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
                switch (type) {
                    case START_WITH:
                        if (valueText.startsWith(input)) {
                            newValues.add(value);
                        }
                        break;
                    case EVERY_START_WITH:
                        if (valueText.startsWith(input)) {
                            newValues.add(value);
                        } else {
                            String[] words = input.split(" ");
                            for (String word : words) {
                                if (word.startsWith(input)) {
                                    newValues.add(value);
                                }
                            }
                        }
                        break;
                    case CONTAINS:
                        if (valueText.contains(input)) {
                            newValues.add(value);
                        }
                        break;
                    case END_WITH:
                        if (valueText.endsWith(input)) {
                            newValues.add(value);
                        }
                        break;
                    default:
                        break;
                }
            }
        }
        return newValues;
    }
}