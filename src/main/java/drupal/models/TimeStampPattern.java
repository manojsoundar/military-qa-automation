package drupal.models;

public final class TimeStampPattern {

    private TimeStampPattern() {
    }

    public static final String PATTERN = "yyyyMMddHHmmss";
    public static final String DATE_PATTERN = "MMddyyyy";
    public static final String TIME_PATTERN = "HHmmssa";
    public static final String DAY_DATE_TIME_PATTERN = "EEE, MM/dd/yyyy - HH:mm";
    public static final String DATE_TIME_PATTERN = "MM/dd/yyyy HH:mm";
    public static final String DATE_WITH_DELIMITER_PATTERN = "MM/dd/yyyy";
    public static final String DATE_WITH_HYPHEN_PATTERN = "yyyy-MM-dd";
    public static final String DATE_WITH_SLASH_PATTERN = "yyyy/MM/dd";
}
