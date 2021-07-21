package inheritance;

import java.lang.reflect.*;

public class InheritableSingleton {

    private InheritableSingleton() {}

    private class CoolerSingleton extends InheritableSingleton {

        private CoolerSingleton() {
            super();
        }

        @Override
        public String getMessage() {
            return "Cooler";
        }
    }

    private class HotterSingleton extends InheritableSingleton {

        private HotterSingleton() {
            super();
        }

        @Override
        public String getMessage() {
            return "Hotter";
        }
    }

    private static SingletonType type = SingletonType.BASE;

    private static InheritableSingleton instance;

    public String getMessage() {
        return "Base";
    }

    public static void setType(SingletonType type) {
        InheritableSingleton.type = type;
    }

    public static InheritableSingleton getInstance()
            throws NoSuchMethodException,
            IllegalAccessException,
            InvocationTargetException,
            InstantiationException {

        if (instance == null) {
            synchronized (InheritableSingleton.class) {
                if (instance == null) {
                    InheritableSingleton singleton = new InheritableSingleton();
                    switch (type) {
                        case BASE -> instance = singleton;
                        case COOLER -> {
                            Constructor<CoolerSingleton> coolerConstructor =
                                    CoolerSingleton.class.getDeclaredConstructor(InheritableSingleton.class);
                            coolerConstructor.setAccessible(true);
                            instance = coolerConstructor.newInstance(singleton);
                        }
                        case HOTTER -> {
                            Constructor<HotterSingleton> hotterConstructor =
                                    HotterSingleton.class.getDeclaredConstructor(InheritableSingleton.class);
                            hotterConstructor.setAccessible(true);
                            instance = hotterConstructor.newInstance(singleton);
                        }
                    }
                }
            }
        }
        return instance;
    }
}

