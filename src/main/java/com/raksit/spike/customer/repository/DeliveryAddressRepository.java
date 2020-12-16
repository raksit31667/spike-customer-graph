package com.raksit.spike.customer.repository;

import com.raksit.spike.customer.domain.DeliveryAddress;
import java.util.List;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryAddressRepository extends Neo4jRepository<DeliveryAddress, String> {

  @Query("MATCH (:Customer {name:$name})-[:PARTNERED]->(d:DeliveryAddress) RETURN d")
  List<DeliveryAddress> findDeliveryAddressesByCustomerName(String name);
}
