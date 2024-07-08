package ecommerce.ecom.mapper;


import ecommerce.ecom.dto.UserDto;
import ecommerce.ecom.entity.User;

public class UserMapper {
	public static UserDto mapToUserDto(User user) {
		UserDto userDto = new UserDto(user.getId(), user.getName(), user.getUsername(),user.getEmail());
		return userDto;
	}
}

