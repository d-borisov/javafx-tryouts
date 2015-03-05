package sample.settings;

import org.ini4j.Ini;
import sample.logic.app.CantStartException;

import java.io.File;
import java.io.IOException;

public class Settings {

    private static final long DEFAULT_INTERVAL_LOG4J_CONF_UPDATE = 5000L;     // 5 секунд

    private final String dbServerName;
    private final int dbPortNumber;
    private final String dbDatabaseName;
    private final String dbUser;
    private final String dbPassword;

    private final String log4jXml;
    private final boolean log4jTrack;
    private final long log4jDelay;

    public Settings(Ini iniFile) {
        this.dbServerName = getOrDefault(iniFile, String.class, "db", "host", "localhost");
        this.dbPortNumber = getOrDefault(iniFile, Integer.class, "db", "port", 5432);
        this.dbDatabaseName = getOrDefault(iniFile, String.class, "db", "db_name", "wings");
        this.dbUser = getOrDefault(iniFile, String.class, "db", "user", "wings");
        this.dbPassword = getOrDefault(iniFile, String.class, "db", "password", "wings");

        this.log4jXml = getOrDefault(iniFile, String.class, "logger", "log4j_xml", "log4j.xml");
        this.log4jTrack = getOrDefault(iniFile, Boolean.class, "logger", "track", false);
        final Long delay = getOrDefault(iniFile, Long.class, "logger", "delay", DEFAULT_INTERVAL_LOG4J_CONF_UPDATE);
        this.log4jDelay = delay > DEFAULT_INTERVAL_LOG4J_CONF_UPDATE ? delay : DEFAULT_INTERVAL_LOG4J_CONF_UPDATE;
    }

    public static Settings fromFile(String[] programArgs) throws CantStartException {
        final String fileName = programArgs.length >= 1 ? programArgs[0] : "local.ini";

        final File file = new File(fileName);
        if (!file.exists()) {
            throw new CantStartException("settings file '%s' not found (%s)", fileName, file.getAbsolutePath());
        }

        try {
            return new Settings(new Ini(file));
        } catch (IOException e) {
            throw new CantStartException(e, "error while reading settings file '%s'", fileName);
        }
    }

    private static <T> T getOrDefault(Ini ini, Class<T> cls, String section, String option, T defaultValue) {
        try {
            final T value = ini.get(section, option, cls);
            return value != null ? value : defaultValue;
        } catch (Exception e) {
            return defaultValue;
        }
    }

    public String getDbServerName() {
        return dbServerName;
    }

    public int getDbPortNumber() {
        return dbPortNumber;
    }

    public String getDbDatabaseName() {
        return dbDatabaseName;
    }

    public String getDbUser() {
        return dbUser;
    }

    public String getDbPassword() {
        return dbPassword;
    }

    public String getLog4jXml() {
        return log4jXml;
    }

    public boolean isLog4jTrack() {
        return log4jTrack;
    }

    public long getLog4jDelay() {
        return log4jDelay;
    }

}
