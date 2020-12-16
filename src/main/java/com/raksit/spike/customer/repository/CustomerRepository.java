package com.raksit.spike.customer.repository;

import com.raksit.spike.customer.domain.Customer;
import java.util.List;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends Neo4jRepository<Customer, String> {

  @Query("MATCH (c:Customer)-[r:PARTNERED]->(d:DeliveryAddress) WHERE d.name = $name RETURN c")
  List<Customer> findCustomersByDeliveryAddressName(String name);

  @Query("MATCH (c:Customer)-[:PARTNERED]->(:DeliveryAddress)-[:PARTNERED]->(:Payer {name:$name}) RETURN c")
  List<Customer> findCustomersByPayerName(String name);
}
