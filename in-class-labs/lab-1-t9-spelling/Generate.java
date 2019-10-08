public class Generate {
    public static void main(String[] args) {
        String[] arr = new String[256];
        int[] buttons = {0, 0, 3, 3, 3, 3, 3, 4, 3, 4};
        int index = 2;
        String toadd = "";
        // since characters can be represented as a number, possible to use characters in a loop
        for (int i = 'a'; i <= 'z'; i++) {
            toadd = toadd + index;
            arr[i] = toadd;
            if (toadd.length() >= buttons[index]) {
                toadd = "";
                index++;
            }
        }
        // no choice but to hardcode for whitespace
        arr[' '] = "0";
        
        // printing out all relevant elements
        for (int i = 0; i < 256; i++) {
            if (arr[i] != null) {
                System.out.println("'" + (char)i + "' -> \"" + arr[i] + "\"");
            }
        }
    }
}