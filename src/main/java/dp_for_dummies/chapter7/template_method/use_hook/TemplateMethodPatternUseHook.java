package dp_for_dummies.chapter7.template_method.use_hook;

abstract class RobotTemplate {
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
        start();
        getParts();
        assemble();
        if (isTest()) { // make the testing part optional
            test();
        }
        stop();
    }
}


// Robot oto cần định nghĩa lại các step: getParts, assemble, test
class AutomotiveRobot extends RobotTemplate { // Automotive: thuộc về oto

    @Override
    public void getParts() {
        System.out.println("Getting a carburetor...."); // Bộ chế hòa khí, 1 bộ phận của oto
    }

    @Override
    public void assemble() {
        System.out.println("Installing the carburetor....");
    }

    @Override
    public void test() {
        System.out.println("Revving the engine....");
    }
}


// Robot bánh quy cũng cần định nghĩa lại 3 step sau
class CookieRobot extends RobotTemplate {
    @Override
    public void getParts() {
        System.out.println("Getting flour and sugar....");
    }

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


public class TemplateMethodPatternUseHook {

    public static void main(String[] args) {
        AutomotiveRobot automotiveRobot = new AutomotiveRobot();
        automotiveRobot.go();

        System.out.println("\n================");
        CookieRobot cookieRobot = new CookieRobot();
        cookieRobot.go();

        System.out.println("\n================");
        ChungCakeRobot chungCakeRobot = new ChungCakeRobot();
        chungCakeRobot.go();
    }
}
