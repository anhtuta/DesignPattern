package dp_refactoring_guru.chain_of_responsibility.access_filter.impl;

import dp_refactoring_guru.chain_of_responsibility.access_filter.Middleware;
import dp_refactoring_guru.chain_of_responsibility.access_filter.Server;

/**
 * ConcreteHandler. Checks whether a user with the given credentials exists.
 */
public class UserExistsMiddleware extends Middleware {
    private Server server;

    public UserExistsMiddleware(Server server) {
        this.server = server;
    }

    public boolean check(String email, String password) {
        if (!server.hasEmail(email)) {
            System.out.println("This email is not registered!");
            return false;
        }
        if (!server.isValidPassword(email, password)) {
            System.out.println("Wrong password!");
            return false;
        }
        return checkNext(email, password);
    }
}
