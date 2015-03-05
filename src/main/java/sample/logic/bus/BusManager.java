package sample.logic.bus;

import com.google.common.eventbus.EventBus;

public class BusManager {

    private static EventBus bus;

    public static void init() {
        bus = new EventBus("Main bus");
    }

    public static void register(Object object) {
        bus.register(object);
    }

    public static void post(Object event) {
        bus.post(event);
    }
}
