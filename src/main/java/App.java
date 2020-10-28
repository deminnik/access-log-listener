import org.apache.commons.cli.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.*;
import java.util.regex.Pattern;

public class App
{
    public static void main(String[] args)
    {
        AppOptions options = new AppOptions(args);
        AccessLogReader reader = new AccessLogReader();

        reader.readLog(options);

        for (Record record :
                reader.getRecords()) {
            String str = String.format("%d.%d.%d - %d.%d.%d : %d",
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
