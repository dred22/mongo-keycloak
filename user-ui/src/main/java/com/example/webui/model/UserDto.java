package com.example.webui.model;

import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class UserDto {

  private String id;

  private String firstName;

  private String lastName;

  private List<AddressDto> addresses;

  private CountryDto country;

}
