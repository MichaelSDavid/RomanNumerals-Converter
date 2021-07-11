class Roman {
    // Roman Numeral operations class
    // Instance var(s) / const(s) init
    private static final short[] numbers = {1, 4, 5, 9, 10, 40, 50, 90, 100, 400, 500, 900, 1000};
    private static final String[] letters = {"I", "IV", "V", "IX", "X", "XL", "L", "XC", "C", "CD", "D", "CM", "M"};

    // Linear search helper method
    @SuppressWarnings("SameParameterValue")
    private static int indexOf(String[] arr, char s)  {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].equals(Character.toString(s))) {
                // If the item exists, return its index
                return i;
            }
        }
        // Return -1 if the item is not found
        return -1;
    }

    public static short convertToInt(String s) {
        // Converts a roman numeral to an integer
        // Exception handling to check for a potential null or empty string
        if (s == null) {
            throw new RuntimeException("Value of string must not be null.");
        }
        if (s.length() == 0) {
            throw new RuntimeException("Value of string must have a length greater than 0.");
        }

        // Var(s) / const(s) init
        short integer = 0;

        // Main loop (starting from the end of the numeral)
        for (int i = s.length() - 1; i > -1; i--) {
            if (integer == 0) {
                // Add rightmost numeral if the integer representation is 0 entry case)
                integer += numbers[indexOf(letters, s.charAt(i))];
                continue;
            }

            if (numbers[indexOf(letters, s.charAt(i))] >= numbers[indexOf(letters, s.charAt(i + 1))]) {
                // If the numeral to the left is greater than or equal to the right,
                // add that to the integer representation
                integer += numbers[indexOf(letters, s.charAt(i))];
            } else {
                // If less, subtract that from the integer representation
                integer -= numbers[indexOf(letters, s.charAt(i))];
            }
        }

        return integer;
    }

    @SuppressWarnings("StringConcatenationInLoop")
    public static String convertToString(int n) {
        // Converts an integer to a roman numeral
        // Exception handling to check for potential 0 / negative integers, and integers greater than 3999
        if (n < 1) {
            throw new NumberFormatException("Value of integer must be positive.");
        }
        if (n > 3999) {
            throw new NumberFormatException("Value of integer must be 3999 or less.");
        }

        // Var(s) / const(s) init
        String roman = "";

        // Main loop (starting from the end of the numbers array)
        for (int i = numbers.length - 1; i > -1; i--) {
            while (n >= numbers[i]) {
                // For number array references less than or equal to the integer,
                // add and concatenate letters for the numeral until the integer has been exhausted to 0
                n -= numbers[i];
                roman += letters[i];
            }
        }

        return roman;
    }
}

public class RomanMain {
    @SuppressWarnings("CommentedOutCode")
    public static void main(String[] args) {
        // Driver class and testing

        // Test integer to numeral string
        System.out.println(Roman.convertToString(36));
        System.out.println(Roman.convertToString(1023));
        System.out.println(Roman.convertToString(542));
        // Invalid cases outside the operable range of (0 < n < 4000)
        // System.out.println(Roman.convertToString(-2));
        // System.out.println(Roman.convertToString(4002));

        // Test numeral string to integer
        System.out.println(Roman.convertToInt("IXX"));
        System.out.println(Roman.convertToInt("DXLII"));
        System.out.println(Roman.convertToInt("MXXIII"));
        // Invalid string cases (null or empty)
        // System.out.println(Roman.convertToInt(null));
        // System.out.println(Roman.convertToInt(""));
    }
}
