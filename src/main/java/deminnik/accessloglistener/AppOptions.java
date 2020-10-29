package deminnik.accessloglistener;

import org.apache.commons.cli.*;

class AppOptions {
    private double time;
    private double accessible;

    public AppOptions(String[] args) {
        Options options = new Options();
        options.addRequiredOption("u",
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
            System.out.println(e.toString());
        }

        this.time = Double.parseDouble(cmd.getOptionValue("t"));
        this.accessible = Double.parseDouble(cmd.getOptionValue("u"));
    }

    public double getAccessible() {
        return this.accessible;
    }

    public double getTime() {
        return this.time;
    }
}
