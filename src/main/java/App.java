import org.apache.commons.cli.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;
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
            int commonCounter = 0;
            int badCounter = 0;
            List<Record> records = new LinkedList<Record>();
            while ((line = reader.readLine()) != null) {
                commonCounter++;
                Pattern pattern = Pattern.compile(" ");
                String[] words = pattern.split(line);
                GregorianCalendar calendar = convertToCalendar(words[3]);
                System.out.println(calendar);
                if (isNotAccessible(words[8],
                        Double.parseDouble(words[10]),
                        Double.parseDouble(cmd.getOptionValue("t")))) badCounter++;
                double accessible = ((double) badCounter / commonCounter) * 100;
                if (accessible < Double.parseDouble(cmd.getOptionValue("u"))) records.add(new Record(accessible));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static GregorianCalendar convertToCalendar(String piece) {
        piece = piece.replaceFirst("\\[", "");

        Pattern pattern = Pattern.compile(":");
        String[] time = pattern.split(piece);

        int hours = Integer.parseInt(time[1]);
        int minutes = Integer.parseInt(time[2]);
        int seconds = Integer.parseInt(time[3]);

        pattern = Pattern.compile("/");
        String[] date = pattern.split(time[0]);

        int day = Integer.parseInt(date[0]);
        int month = Integer.parseInt(date[1]);
        int year = Integer.parseInt(date[2]);

        return new GregorianCalendar(year, month, day, hours, minutes, seconds);
    }

    private static boolean isNotAccessible(String code, double time, double minTime) {
        boolean flag = false;
        Pattern pattern = Pattern.compile("5\\d\\d");
        if (pattern.matcher(code).find()) flag = true;
        else if (time > minTime) flag = true;
        return flag;
    }
}
