import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Jerry
 */
public class stats {

    private static final String LIST_NAME = "C:\\Users\\Jerry\\Documents\\GitHub\\list\\list.txt";

    public static void main(final String[] args) {
        final File folder = new File("H:\\jav");
        final File[] listOfFiles = folder.listFiles();

        final List<String> names = new ArrayList<String>();


        final String pattern = "(\\w+)-(\\d+)";
        final Pattern r = Pattern.compile(pattern);

        for (final File file : listOfFiles) {
            if (file.isFile()) {
                final String name = file.getName();
                final Matcher m = r.matcher(name);
                if (m.find()) {
                    final String simpleName = String.format(String.format("%s-%s", m.group(1), m.group(2)));
                    if (!names.contains(simpleName)) {
                        names.add(simpleName);
                    }
                }
            }
        }

        try {
            final List<String> existingNames = Files.readAllLines(Paths.get(LIST_NAME), Charset.defaultCharset());


            for (final String name : names) {
                if (!existingNames.contains(name)) {
                    existingNames.add(name);
                }
            }

            Collections.sort(existingNames);

            final PrintWriter writer = new PrintWriter(LIST_NAME, "UTF-8");

            for (final String name : existingNames) {
                writer.println(name);
            }
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
