package service;

import model.Customer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

public class CustomerService {
    private Collection<Customer> setOfCustomers = new HashSet<Customer>();
    private static CustomerService customerService = null;
    //facilitate the Singleton Pattern
    private CustomerService() {

    }
    //static reference for CustomerService class
    public static CustomerService getInstance() {
        if (customerService == null){
            customerService = new CustomerService();
        }
        return customerService;
    }

    // add customer
    public void addCustomer(String email, String firstName, String lastName) {
        Customer customer = new Customer(firstName, lastName, email);
        setOfCustomers.add(customer);
    }
    // search customers from customer sets
    public Customer getCustomer(String customerEmail){
        for (Customer customer : setOfCustomers) {
            if (customerEmail.equals(customer.getEmail()));
            return customer;
        }
        return null;
    };
    //get all customers
    public Collection<Customer> getAllCustomers(){
        if (!setOfCustomers.isEmpty()) {
            for (Customer customer : setOfCustomers) {
                System.out.println(customer);
            }
            return setOfCustomers;
        }
        return null;
    };
}
