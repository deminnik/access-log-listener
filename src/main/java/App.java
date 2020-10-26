package org.apache.commons.cli;

public class App
{
    public static void main( String[] args )
    {
        Options options = new Options();
        options.addOption("p", "print", true, "Print");

        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = null;
        try {
            cmd = parser.parse(options, args);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (cmd != null && cmd.hasOption("p")) {
            System.out.println(cmd.getOptionValue("p"));
        }
    }
}
