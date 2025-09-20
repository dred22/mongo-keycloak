package com.example.mongo_user.model;

import com.example.mongo_user.entity.Address;
import com.example.mongo_user.entity.Country;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDto {

  private String firstName;

  private String lastName;

  private List<Address> addresses;

  private Country country;

}
