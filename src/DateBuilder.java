import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class DateBuilder {
    private int year;
    private int month;
    private int day;
    private int hour;
    private int minutes;

    public DateBuilder setYear(int year) {
        this.year = year;
        return this;
    }

    public DateBuilder setMonth(int month) {
        this.month = month;
        return this;
    }

    public DateBuilder setDay(int day) {
        this.day = day;
        return this;
    }

    public DateBuilder setHour(int hour) {
        this.hour = hour;
        return this;
    }

    public DateBuilder setMinutes(int minutes) {
        this.minutes = minutes;
        return this;
    }

    public LocalDateTime getDate() {
        LocalDate localDate = LocalDate.of(year, month, day);
        LocalTime localTime = LocalTime.of(hour, minutes);
        return LocalDateTime.of(localDate, localTime);
    }
}
