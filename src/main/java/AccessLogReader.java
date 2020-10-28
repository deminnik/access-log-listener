import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.regex.Pattern;

class AccessLogReader {
    List<Record> records = new LinkedList<Record>();

    public void readLog(AppOptions options) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            String line;
            int commonCounter = 0;
            int badCounter = 0;
            while ((line = reader.readLine()) != null) {
                commonCounter++;
                Pattern pattern = Pattern.compile(" ");
                String[] words = pattern.split(line);
                GregorianCalendar startTime = convertToCalendar(words[3]);
                if (isNotAccessible(words[8],
                        Double.parseDouble(words[10]),
                        options.getTime())) badCounter++;
                double accessible = ((double) badCounter / commonCounter) * 100;
                if (accessible < options.getAccessible()) {
                    records.add(new Record(accessible, startTime, convertToCalendar(words[3])));
                    commonCounter = 0;
                    badCounter = 0;
                }
            }
        } catch (IOException e) {
            System.out.println(e.toString());
        }
        sortRecords();
    }

    public List<Record> getRecords() {
        return records;
    }

    private void sortRecords() {
        Collections.sort(records, new Comparator<Record>() {
            public int compare(Record r1, Record r2) {
                return r1.getStartTime().compareTo(r2.getStartTime());
            }
        });
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
