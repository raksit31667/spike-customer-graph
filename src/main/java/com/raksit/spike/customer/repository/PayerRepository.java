package com.raksit.spike.customer.repository;

import com.raksit.spike.customer.domain.Payer;
import java.util.List;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PayerRepository extends Neo4jRepository<Payer, String> {

  @Query("MATCH (:DeliveryAddress {name:$name})-[:PARTNERS_WITH]->(p:Payer) RETURN DISTINCT p")
  List<Payer> findPayersByDeliveryAddressName(String name);
}
