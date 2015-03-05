package sample;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SomeTest {

    @Test
    public void test_caller() throws Exception {
        final Container<String> someString = new Container<>("");
        final Container<Integer> someInt = new Container<>(0);

        Caller.call((String s) -> someString.value = s, "foo");
        Caller.call((Integer i) -> someInt.value = i, 42);

        assertThat(someString.value, is("foo"));
        assertThat(someInt.value, is(42));
    }

    private final class Container<T> {
        T value;

        private Container(T value) {
            this.value = value;
        }
    }

    final static class Caller {
        static <T> void call(CanCall<T> canCall, T value) {
            canCall.fire(value);
        }
    }

    static interface CanCall<T> {
        void fire(T param);
    }

}