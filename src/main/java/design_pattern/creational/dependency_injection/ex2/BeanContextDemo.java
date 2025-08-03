package design_pattern.creational.dependency_injection.ex2;

public class BeanContextDemo {

    private static class ConverseHelper {
        private static final Converse INSTANCE = new Converse();
    }

    private static Converse getConverseInstance() {
        return ConverseHelper.INSTANCE;
    }

    private static class CoolmateHelper {
        private static final Coolmate INSTANCE = new Coolmate();
    }

    private static Coolmate getCoolmateInstance() {
        return CoolmateHelper.INSTANCE;
    }

    private static class LouisVuittonHelper {
        private static final LouisVuitton INSTANCE = new LouisVuitton();
    }

    private static LouisVuitton getLouisVuittonInstance() {
        return LouisVuittonHelper.INSTANCE;
    }

    private static class SidepartHelper {
        private static final Sidepart INSTANCE = new Sidepart();
    }

    private static Sidepart getSidepartInstance() {
        return SidepartHelper.INSTANCE;
    }
    
    public static Object getBean(String beanName) {
        switch (beanName) {
            case "converse":
                return getConverseInstance();
            case "coolmate":
                return getCoolmateInstance();
            case "louisVuitton":
                return getLouisVuittonInstance();
            case "sidepart":
                return getSidepartInstance();

            default:
                return null;
        }
    }

}
