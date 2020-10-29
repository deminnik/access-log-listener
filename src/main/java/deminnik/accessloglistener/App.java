package deminnik.accessloglistener;

import java.util.*;

public class App
{
    public static void main(String[] args)
    {
        AppOptions options = new AppOptions(args);

        AccessLogReader reader = new AccessLogReader();
        reader.readLog(options);

        printResult(reader.getRecords());
    }

    private static void printResult(List<Record> records) {
        for (Record record :
                records) {
            String str = String.format("%d.%d.%d - %d.%d.%d : %f",
                    record.getStartTime().get(Calendar.HOUR),
                    record.getStartTime().get(Calendar.MINUTE),
                    record.getStartTime().get(Calendar.SECOND),
                    record.getFinishTime().get(Calendar.HOUR),
                    record.getFinishTime().get(Calendar.MINUTE),
                    record.getFinishTime().get(Calendar.SECOND),
                    record.getAccessible());
            System.out.println(str);
        }
    }
}
