package sample.settings;

public class SettingsHolder {

    private static volatile Settings sets;

    synchronized public static Settings sets() {
        return sets;
    }

    synchronized public static void link(Settings settings) {
        sets = settings;
    }
}
