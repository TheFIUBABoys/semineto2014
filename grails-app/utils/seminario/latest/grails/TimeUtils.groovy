package seminario.latest.grails

class TimeUtils {

    public static Date subtractMinutes(Date date, Integer minutes) {
        def cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MINUTE, - minutes);
        return cal.getTime();
    }

    public static Date subtractHours(Date date, Integer hours) {
        def cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.HOUR_OF_DAY, - hours);
        return cal.getTime();
    }

}
