package it.unibo.nestedenum;

import java.util.Comparator;
import java.util.Locale;
import java.util.Objects;

/**
 * Implementation of {@link MonthSorter}.
 */
public final class MonthSorterNested implements MonthSorter {

    private enum Month {

        JANUARY(31),
        FEBRUARY(28),
        MARCH(31),
        APRIL(30),
        MAY(31),
        JUNE(30),
        JULY(31),
        AUGUST(31),
        SEPTEMBER(30),
        OCTOBER(31),
        NOVEMBER(30),
        DECEMBER(31);

        private final int daysInMonth;

        Month(final int daysInMonth) {
            this.daysInMonth = daysInMonth;
        }

        public static Month fromString(final String name) {
                         
            Month found = null;
            for (final Month month: values()) {
                if (month.toString().startsWith(name.toUpperCase())) {
                    if (found != null) {
                        throw new IllegalArgumentException(name + "Has to many matches");
                    }
                    found = month;
                }
            }
            
            if (found == null) {
                throw new IllegalArgumentException("No compatible months");
            }
            return found;
        }
    }
    

    @Override
    public Comparator<String> sortByDays() {
        return new SortByDays();
    }

    @Override
    public Comparator<String> sortByOrder() {
        return new SortByOrder();
    }

    private static class SortByDays implements Comparator<String> {

        public SortByDays() {}
    
        @Override
        public int compare(String first, String second) {
            final var m1 = Month.fromString(first);
            final var m2 = Month.fromString(second);
            return Integer.compare(m1.daysInMonth, m2.daysInMonth);
        }
    
    }

    private static class SortByOrder implements Comparator<String> {

        public SortByOrder() {}
    
        @Override
        public int compare(final String s1, final String s2) {
            return Month.fromString(s1).compareTo(Month.fromString(s2));
        }
    
    }
}

