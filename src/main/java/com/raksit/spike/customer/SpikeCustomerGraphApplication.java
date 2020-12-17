package com.raksit.spike.customer;

import com.raksit.spike.customer.domain.Customer;
import com.raksit.spike.customer.domain.DeliveryAddress;
import com.raksit.spike.customer.domain.Payer;
import com.raksit.spike.customer.repository.CustomerRepository;
import com.raksit.spike.customer.repository.DeliveryAddressRepository;
import com.raksit.spike.customer.repository.PayerRepository;
import java.util.Arrays;
import java.util.List;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;

@SpringBootApplication
@EnableNeo4jRepositories
public class SpikeCustomerGraphApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpikeCustomerGraphApplication.class, args);
    System.exit(0);
  }

  @Bean
  public CommandLineRunner demo(CustomerRepository customerRepository, DeliveryAddressRepository deliveryAddressRepository,
      PayerRepository payerRepository) {
    return args -> {
      customerRepository.deleteAll();
      deliveryAddressRepository.deleteAll();
      payerRepository.deleteAll();

      Customer customer = new Customer("SP1");
      Customer customer2 = new Customer("SP2");

      DeliveryAddress deliveryAddress1 = new DeliveryAddress("SH1");
      DeliveryAddress deliveryAddress3 = new DeliveryAddress("SH3");
      DeliveryAddress deliveryAddress4 = new DeliveryAddress("SH4");
      DeliveryAddress deliveryAddress5 = new DeliveryAddress("SH5");

      Payer payer = new Payer("PY1");
      Payer payer2 = new Payer("PY2");

      customer.partnersWith(deliveryAddress1, "WE", "3862/03/04");
      customer.partnersWith(deliveryAddress3, "WE", "3862/03/04");
      customer.partnersWith(payer, "PY", "3862/03/04");
      customer2.partnersWith(deliveryAddress4, "WE", "0735/03/04");
      customer2.partnersWith(deliveryAddress5, "WE", "0735/03/04");

      deliveryAddress1.partnersWith(payer2, "PY", "3862/03/04");
      deliveryAddress3.partnersWith(payer2, "PY", "3862/03/04");
      deliveryAddress4.partnersWith(payer2, "PY", "0735/03/04");
      deliveryAddress5.partnersWith(payer2, "PY", "0735/03/04");

      customerRepository.saveAll(Arrays.asList(customer, customer2));
      deliveryAddressRepository.saveAll(Arrays.asList(deliveryAddress1, deliveryAddress3, deliveryAddress4, deliveryAddress5));
      deliveryAddressRepository.saveAll(Arrays.asList(deliveryAddress1, deliveryAddress3, deliveryAddress4, deliveryAddress5));
      payerRepository.saveAll(Arrays.asList(payer, payer2));

      List<Payer> payers = payerRepository.findPayersByDeliveryAddressName("SH1");
      System.out.println("Payer: " + payers);
      List<DeliveryAddress> deliveryAddresses = deliveryAddressRepository.findDeliveryAddressesByCustomerName("SP1");
      System.out.println("DeliveryAddress: " + deliveryAddresses);
      List<Customer> customers = customerRepository.findCustomersByDeliveryAddressName("SH1");
      System.out.println("Customer: " + customers);
      List<Customer> customers2 = customerRepository.findCustomersByPayerName("PY2");
      System.out.println("Customer: " + customers2);
    };
  }
}
