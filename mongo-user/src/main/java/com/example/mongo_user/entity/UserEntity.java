package com.example.mongo_user.entity;

import java.util.List;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("users")
public class UserEntity {

  @Id
  private String id;

  private String firstName;

  private String lastName;

  private List<Address> adresses;

  private Country country;

}
