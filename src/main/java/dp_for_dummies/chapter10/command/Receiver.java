package dp_for_dummies.chapter10.command;

// Interface bao gồm toàn bộ các action riêng rẽ
public interface Receiver {
    public void connect();
    public void diagnostics();
    public void reboot();
    public void shutdown();
    public void disconnect();
}

class AsiaServer implements Receiver {
    @Override
    public void connect() {
        System.out.println("You’re connected to the Asia server.");
    }

    @Override
    public void diagnostics() {
        System.out.println("The Asia server diagnostics check out OK.");
    }

    @Override
    public void shutdown() {
        System.out.println("Shutting down the Asia server.");
    }

    @Override
    public void reboot() {
        System.out.println("Rebooting the Asia server.");
    }

    @Override
    public void disconnect() {
        System.out.println("You’re disconnected from the Asia server.");
    }
}

class EuroServer implements Receiver {
    @Override
    public void connect() {
        System.out.println("You’re connected to the Euro server.");
    }

    @Override
    public void diagnostics() {
        System.out.println("The Euro server diagnostics check out OK.");
    }

    @Override
    public void shutdown() {
        System.out.println("Shutting down the Euro server.");
    }

    @Override
    public void reboot() {
        System.out.println("Rebooting the Euro server.");
    }

    @Override
    public void disconnect() {
        System.out.println("You’re disconnected from the Euro server.");
    }
}

class USServer implements Receiver {
    @Override
    public void connect() {
        System.out.println("You’re connected to the US server.");
    }

    @Override
    public void diagnostics() {
        System.out.println("The US server diagnostics check out OK.");
    }

    @Override
    public void shutdown() {
        System.out.println("Shutting down the US server.");
    }

    @Override
    public void reboot() {
        System.out.println("Rebooting the US server.");
    }

    @Override
    public void disconnect() {
        System.out.println("You’re disconnected from the US server.");
    }
}
