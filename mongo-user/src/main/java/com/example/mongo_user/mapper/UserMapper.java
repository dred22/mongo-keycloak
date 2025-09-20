package com.example.mongo_user.mapper;

import com.example.mongo_user.entity.UserEntity;
import com.example.mongo_user.model.UserDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

  UserEntity toUserEntity(UserDto userDto);
}
