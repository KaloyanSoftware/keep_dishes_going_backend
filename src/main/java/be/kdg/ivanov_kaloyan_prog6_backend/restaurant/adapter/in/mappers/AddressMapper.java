package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.in.mappers;

import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.AddressDTO;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.Address;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AddressMapper {
    Address toDomain(AddressDTO dto);
}
