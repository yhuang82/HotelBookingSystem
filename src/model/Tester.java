package model;

public class Tester {
    public static void main(String[] args) {
        Customer customer = null;
        Customer customer1 = null;
        try {
            customer = new Customer("first",
                    "second", "j@domain.com");
            customer1 = new Customer("first",
                    "second", "email");

        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getLocalizedMessage());
        }

        System.out.println(customer);
        if (customer1 != null)
            System.out.println(customer1);
    }
}
