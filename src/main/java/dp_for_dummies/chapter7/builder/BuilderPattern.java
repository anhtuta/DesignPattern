package dp_for_dummies.chapter7.builder;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

interface RobotBuildable {
    public void go();
}


interface RobotBuilder {
    public void addStart();

    public void addGetParts();

    public void addAssemble();

    public void addTest();

    public void addStop();

    public RobotBuildable getRobot();
}


class CookieRobotBuildable implements RobotBuildable {
    private List<Integer> actions;

    public void loadActions(List<Integer> actions) {
        this.actions = actions;
    }

    @Override
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
                    test();
                    break;
                case 5:
                    stop();
                    break;
            }
        }
    }

    public void start() {
        System.out.println("Starting....");
    }

    public void getParts() {
        System.out.println("Getting flour and sugar....");
    }

    public void assemble() {
        System.out.println("Baking a cookie....");
    }

    public void test() {
        System.out.println("Crunching a cookie....");
    }

    public void stop() {
        System.out.println("Stopping....");
    }
}


class CookieRobotBuilder implements RobotBuilder {
    private CookieRobotBuildable robot;
    private List<Integer> actions;

    public CookieRobotBuilder() {
        robot = new CookieRobotBuildable();
        actions = new LinkedList<Integer>();
    }

    @Override
    public void addStart() {
        actions.add(1);
    }

    @Override
    public void addGetParts() {
        actions.add(2);
    }

    @Override
    public void addAssemble() {
        actions.add(3);
    }

    @Override
    public void addTest() {
        actions.add(4);
    }

    @Override
    public void addStop() {
        actions.add(5);
    }

    @Override
    public RobotBuildable getRobot() {
        robot.loadActions(actions);
        return robot;
    }
}


public class BuilderPattern {

    public static void main(String[] args) {
        RobotBuilder builder = new CookieRobotBuilder();
        builder.addStart();
        builder.addTest();
        builder.addAssemble();
        builder.addStop();

        RobotBuildable robot = builder.getRobot();
        robot.go();
    }
}
