import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static boolean sort;
    public static String typeData;
    public static String outputName;
    public static String[] inputNames;
    public static ArrayList<String> bufferArray = new ArrayList<>();
    public static ArrayList<String> result = new ArrayList<>();

    public static ArrayList<String> merge(ArrayList<String> m1, ArrayList<String> m2) {

        int sizeLists = m1.size() + m2.size();
        ArrayList<String> buffer = new ArrayList<>();
        int i = 0, j = 0;

        while (buffer.size() < sizeLists) {
            if (i == m1.size()) {
                buffer.add(m2.get(j));
                j++;
            } else if (j == m2.size()) {
                buffer.add(m1.get(i));
                i++;
            } else if (compareElements(m1.get(i), m2.get(j))) {
                buffer.add(m1.get(i));
                i++;
            } else {
                buffer.add(m2.get(j));
                j++;
            }
        }
        return buffer;
    }

    public static boolean compareElements(String arg1, String arg2) {
        if (typeData.contains("-s")) {
            return arg1.compareTo(arg2) < 0;
        } else return Integer.parseInt(arg1) < (Integer.parseInt(arg2));
    }

    public static void reverseArray(ArrayList<String> array) {
        if (sort) {
            String buffer;
            for (int i = 0, k = array.size() - 1; i < (array.size() / 2); i++, k--) {
                buffer = array.get(i);
                array.set(i, array.get(k));
                array.set(k, buffer);
            }
        }
    }

    public static void main(String[] args) {
        int optionalArgument = 0;
        if (args[0].equals("-d")) sort = true;
        else if (args[0].equals("-a")) sort = false;
        else optionalArgument = 1;
        typeData = args[1 - optionalArgument];
        outputName = args[2 - optionalArgument];
        inputNames = Arrays.copyOfRange(args, 3 - optionalArgument, args.length);
        BufferedReader reader;

        for (int i = 0; i < inputNames.length; i++) {
            try {
                reader = new BufferedReader(new FileReader(inputNames[i]));
                bufferArray.clear();
                String text;
                while ((text = reader.readLine()) != null) {
                    bufferArray.add(text);
                }
                if (i == 0) {
                    result = new ArrayList<>(bufferArray);
                } else {
                    result = merge(result, bufferArray);
                }
                reader.close();

            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (sort) {
            reverseArray(result);
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputName))) {
            for (String s : result) {
                System.out.println(s);
                writer.write(s);
                writer.newLine();
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
