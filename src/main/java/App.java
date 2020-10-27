import org.apache.commons.cli.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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

        if (cmd != null && cmd.hasOption("u")) {
            System.out.println(cmd.getOptionValue("u"));
        }
        if (cmd != null && cmd.hasOption("t")) {
            System.out.println(cmd.getOptionValue("t"));
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.println(reader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
