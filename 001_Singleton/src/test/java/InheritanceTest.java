import inheritance.InheritableSingleton;
import inheritance.SingletonType;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;

public class InheritanceTest {

    @Test
    void testSingletonInheritanceBase() throws
            InvocationTargetException,
            NoSuchMethodException,
            InstantiationException,
            IllegalAccessException {

        var singleton = InheritableSingleton.getInstance();
        System.out.println(singleton.getMessage());

        assert singleton.getMessage().equals("Base");
    }

    @Test
    void testSingletonInheritanceCooler() throws
            InvocationTargetException,
            NoSuchMethodException,
            InstantiationException,
            IllegalAccessException {

        InheritableSingleton.setType(SingletonType.COOLER);
        var singleton = InheritableSingleton.getInstance();
        System.out.println(singleton.getMessage());

        assert singleton.getMessage().equals("Cooler");
    }

    @Test
    void testSingletonInheritanceHotter() throws
            InvocationTargetException,
            NoSuchMethodException,
            InstantiationException,
            IllegalAccessException {

        InheritableSingleton.setType(SingletonType.HOTTER);
        var singleton = InheritableSingleton.getInstance();
        System.out.println(singleton.getMessage());

        assert singleton.getMessage().equals("Hotter");
    }
}
