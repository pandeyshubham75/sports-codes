package SportsCodes;

public class DayFromDate {

    public static void main(String[] args) {
        DayFromDate instance = new DayFromDate();
        System.out.println(instance.dayOfTheWeek(31,7,2100));
    }

    public String dayOfTheWeek(int day, int month, int year) {
        String[] days = new String[]{"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
        int offset = 5;
        return days[(offset + offSetForYear(year) + offSetForMonth(month, year) + day - 1) % 7];
    }

    public int offSetForYear(int year) {
        return (((year - 1971) * 365) + ((year - 1969) / 4) % 7);
    }

    public int offSetForMonth(int month, int year) {
        int offset = ((month - 1) * 30) % 7;
        if (month > 10) offset = (offset + 4) % 7;
        else if (month > 8) offset = (offset + 3) % 7;
        else if (month > 7) offset = (offset + 2) % 7;
        else if (month > 7) offset = (offset + 2) % 7;
        else if (month > 5 || month == 2) offset = (offset + 1) % 7;
        else if (month == 3) offset = (offset - 1) % 7;
        return (month > 2 && year % 4 == 0 && year != 2100 ) ? (offset + 1) % 7 : offset;
    }
}
