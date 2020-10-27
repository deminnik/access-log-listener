import org.apache.commons.cli.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.regex.Pattern;

public class App
{
    public static void main( String[] args )
    {
        Options options = new Options();
        options.addOption("u",
                "access",
                true,
                "Минимально допустимый уровень доступности (проценты. Например, \"99.9\")");
        options.addRequiredOption("t",
                "time",
                true,
                "Приемлемое время ответа (миллисекунды. Например, \"45\")");

        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = null;
        try {
            cmd = parser.parse(options, args);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            String line;
            while ((line = reader.readLine()) != null) {
                Pattern pattern = Pattern.compile(" ");
                String[] words = pattern.split(line);
                System.out.println(isNotAccessable(words[8],
                        Double.parseDouble(words[10]),
                        Double.parseDouble(cmd.getOptionValue("t"))));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean isNotAccessable(String code, double time, double minTime) {
        boolean flag = false;
        Pattern pattern = Pattern.compile("5\\d\\d");
        if (pattern.matcher(code).find()) flag = true;
        else if (time > minTime) flag = true;
        return flag;
    }
}
