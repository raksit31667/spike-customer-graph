package com.raksit.spike.customer.domain;

import java.util.List;
import org.springframework.data.neo4j.core.schema.RelationshipProperties;
import org.springframework.data.neo4j.core.schema.TargetNode;

@RelationshipProperties
public class Partnership {

  @TargetNode
  private final Account account;

  private final String code;

  private final List<String> salesArea;

  public Partnership(Account account, String code, List<String> salesArea) {
    this.account = account;
    this.code = code;
    this.salesArea = salesArea;
  }
}
