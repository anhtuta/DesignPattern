package dp_for_dummies.chapter7.builder.myway;

import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;


abstract class RobotTemplate {
    private List<Integer> actions;

    public final void loadActions(List<Integer> actions) {
        this.actions = actions;
    }

    public void start() {
        System.out.println("Starting....");
    }

    public void getParts() {
        System.out.println("Getting parts....");
    }

    public void assemble() {
        System.out.println("Assembling....");
    }

    public void test() {
        System.out.println("Testing....");
    }

    public void stop() {
        System.out.println("Stopping....");
    }

    // Mặc định thuật toán go sẽ cần step test, nếu class con nào ko cần
    // thì chỉ cần override lại method này
    public boolean isTest() {
        return true;
    }

    // thuật toán go của template gồm các step như ở dưới (theo đúng trình tự và số lượng),
    // class con có thể customize 1 vài step, và tất nhiên nó ko thê được phép
    // customize method này
    public final void go() {
        Iterator<Integer> itr = actions.iterator();
        while (itr.hasNext()) {
            switch (itr.next()) {
                case 1:
                    start();
                    break;
                case 2:
                    getParts();
                    break;
                case 3:
                    assemble();
                    break;
                case 4:
                    if (isTest()) { // make the testing part optional
                        test();
                    }
                    break;
                case 5:
                    stop();
                    break;
                default:
                    break;
            }
        }
    }
}


class RobotBuilder {
    private List<Integer> actions;

    public RobotBuilder() {
        actions = new LinkedList<Integer>();
    }

    public RobotBuilder addStart() {
        actions.add(1);
        return this;
    }

    public RobotBuilder addGetParts() {
        actions.add(2);
        return this;
    }

    public RobotBuilder addAssemble() {
        actions.add(3);
        return this;
    }

    public RobotBuilder addTest() {
        actions.add(4);
        return this;
    }

    public RobotBuilder addStop() {
        actions.add(5);
        return this;
    }

    public RobotTemplate build(Class<? extends RobotTemplate> robotTemplateClass) {
        try {
            RobotTemplate robot;
            robot = robotTemplateClass.getDeclaredConstructor().newInstance();
            robot.loadActions(actions);
            return robot;
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException
                | InvocationTargetException | NoSuchMethodException | SecurityException e) {
            e.printStackTrace();
        }
        return null;
    }
}


class CookieRobot extends RobotTemplate {

    @Override
    public void assemble() {
        System.out.println("Baking a cookie....");
    }

    @Override
    public void test() {
        System.out.println("Crunching a cookie....");
    }
}


class ChungCakeRobot extends RobotTemplate {
    @Override
    public void getParts() {
        System.out.println("Chuẩn bị lá dong, nạt tre, gạo nếp, thịt ba chỉ, đậu xanh....");
    }

    @Override
    public void assemble() {
        System.out.println("Gói bánh xong ngồi đun....");
    }

    @Override
    public boolean isTest() {
        return false;
    }
}


public class BuilderPatternMyWay {

    public static void main(String[] args) {
        RobotBuilder builder = new RobotBuilder().addStart().addTest().addAssemble().addStop();
        RobotTemplate cookieRobot = builder.build(CookieRobot.class);
        cookieRobot.go();

        RobotBuilder builder2 = new RobotBuilder().addStart().addGetParts().addAssemble().addStop();
        ChungCakeRobot chungCakeRobot = (ChungCakeRobot) builder2.build(ChungCakeRobot.class);
        chungCakeRobot.go();
    }
}
