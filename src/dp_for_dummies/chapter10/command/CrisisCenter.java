package dp_for_dummies.chapter10.command;

public class CrisisCenter {

    public static void main(String[] args) {
        // Invoker là class đảm nhiệm việc run command nào đó,
        // muốn đổi command khác bạn phải load command cần đổi (dùng method setCommand)
        Invoker invoker = new Invoker();

        // Các receiver cho từng server (là các class bao gồm các action riêng rẽ)
        Receiver asiaServer = new AsiaServer();
        Receiver euroServer = new EuroServer();
        Receiver usServer = new USServer();

        // Tạo các command (gồm tập các action)
        Command shutDownAsia = new ShutDownCommand(asiaServer);
        Command runDiagnosticsAsia = new RunDiagnosticsCommand(asiaServer);
        Command rebootAsia = new RebootCommand(asiaServer);
        Command shutDownEuro = new ShutDownCommand(euroServer);
        Command runDiagnosticsEuro = new RunDiagnosticsCommand(euroServer);
        Command rebootEuro = new RebootCommand(euroServer);
        Command shutDownUS = new ShutDownCommand(usServer);
        Command runDiagnosticsUS = new RunDiagnosticsCommand(usServer);
        Command rebootUS = new RebootCommand(usServer);

        invoker.setCommand(shutDownAsia);
        invoker.run();

        invoker.setCommand(rebootAsia);
        invoker.run();

        invoker.setCommand(runDiagnosticsUS);
        invoker.run();

        invoker.setCommand(runDiagnosticsEuro);
        invoker.run();

        invoker.setCommand(rebootEuro);
        invoker.run();

        invoker.setCommand(shutDownUS);
        invoker.run();

        invoker.setCommand(shutDownEuro);
        invoker.run();

        invoker.setCommand(runDiagnosticsAsia);
        invoker.run();

        invoker.setCommand(rebootUS);
        invoker.run();
    }
}
