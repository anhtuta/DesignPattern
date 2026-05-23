package dp_refactoring_guru.strategy.payment.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.YearMonth;
import dp_refactoring_guru.strategy.payment.CreditCard;
import dp_refactoring_guru.strategy.payment.PayStrategy;

/**
 * Concrete strategy. Implements credit card payment method.
 */
public class PayByCreditCard implements PayStrategy {
    private final BufferedReader READER = new BufferedReader(new InputStreamReader(System.in));
    private CreditCard card;

    /**
     * Collect credit card data.
     */
    @Override
    public void collectPaymentDetails() {
        try {
            while (true) {
                System.out.print("Enter the card number: ");
                String number = READER.readLine();
                String sanitizedNumber = sanitizeCardNumber(number);
                if (!isCardNumberValid(sanitizedNumber)) {
                    System.out.println("Invalid card number. Please enter a valid card number.");
                    continue;
                }

                System.out.print("Enter the card expiration date 'mm/yy': ");
                String date = READER.readLine();
                if (!isExpirationDateValid(date)) {
                    System.out.println("Invalid expiration date. Use format mm/yy and a non-expired date.");
                    continue;
                }

                System.out.print("Enter the CVV code: ");
                String cvv = READER.readLine();
                if (!isCvvValid(cvv)) {
                    System.out.println("Invalid CVV code. CVV must be 3 or 4 digits.");
                    continue;
                }

                card = new CreditCard(sanitizedNumber, date, cvv);
                System.out.println("Credit card data is valid.");
                break;
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * After card validation we can charge customer's credit card.
     */
    @Override
    public boolean pay(int paymentAmount) {
        if (cardIsPresent()) {
            System.out.println("Paying " + paymentAmount + " using Credit Card.");
            card.setAmount(card.getAmount() - paymentAmount);
            return true;
        } else {
            return false;
        }
    }

    private boolean cardIsPresent() {
        return card != null;
    }

    private String sanitizeCardNumber(String number) {
        if (number == null) {
            return "";
        }
        return number.replaceAll("[\\s-]", "");
    }

    private boolean isCardNumberValid(String number) {
        if (number == null || !number.matches("\\d{13,19}")) {
            return false;
        }
        return passesLuhnCheck(number);
    }

    private boolean passesLuhnCheck(String number) {
        int sum = 0;
        boolean shouldDouble = false;
        for (int i = number.length() - 1; i >= 0; i--) {
            int digit = number.charAt(i) - '0';
            if (shouldDouble) {
                digit *= 2;
                if (digit > 9) {
                    digit -= 9;
                }
            }
            sum += digit;
            shouldDouble = !shouldDouble;
        }
        return sum % 10 == 0;
    }

    private boolean isExpirationDateValid(String date) {
        if (date == null || !date.matches("(0[1-9]|1[0-2])/\\d{2}")) {
            return false;
        }
        int month = Integer.parseInt(date.substring(0, 2));
        int year = 2000 + Integer.parseInt(date.substring(3, 5));
        YearMonth expiration = YearMonth.of(year, month);
        YearMonth current = YearMonth.now();
        return !expiration.isBefore(current);
    }

    private boolean isCvvValid(String cvv) {
        return cvv != null && cvv.matches("\\d{3,4}");
    }
}
