package com.raksit.spike.customer.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;
import org.springframework.data.neo4j.core.schema.Relationship.Direction;

@Node("Customer")
public class Customer implements Account {

  @Id
  private final String name;

  @Relationship(type = "PARTNERS_WITH", direction = Direction.OUTGOING)
  private final List<Partnership> partnerships = new ArrayList<>();

  public Customer(String name) {
    this.name = name;
  }

  public void partnersWith(Account account, String code, String salesArea) {
    partnerships.add(new Partnership(account, code, Arrays.asList(salesArea)));
  }

  @Override
  public String toString() {
    return name;
  }

}
