import java.util.Calendar;

class Record {
    private Calendar startTime;
    private Calendar finishTime;
    private double accessible;

    public Record(double accessible, Calendar startTime, Calendar finishTime) {
        this.accessible = accessible;
        this.startTime = startTime;
        this.finishTime = finishTime;
    }

    public Calendar getStartTime() {
        return this.startTime;
    }

    public Calendar getFinishTime() {
        return this.finishTime;
    }

    public double getAccessible() {
        return this.accessible;
    }
}
