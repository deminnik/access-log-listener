import java.util.Calendar;

class Record {
    Calendar startTime;
    Calendar finishTime;
    double accessible;

    public Record(double accessible, Calendar startTime, Calendar finishTime) {
        this.accessible = accessible;
        this.startTime = startTime;
        this.finishTime = finishTime;
    }
}
