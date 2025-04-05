package eagel.tailung.mapper;

import eagel.tailung.dto.CitizenDTO;
import eagel.tailung.entity.Citizen;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(
        componentModel = "cdi"
)
public interface CitizenMapper {
    @Mapping(target = "firstName",expression = "java(citizenDTO.getFullName().substring(0,citizenDTO.getFullName().indexOf(' ')))")
    @Mapping(target = "lastName",expression = "java(citizenDTO.getFullName().substring(citizenDTO.getFullName().indexOf(' ')))")
    @Mapping(target = "address",expression = "java(citizenDTO.getAddress()+\"-\"+citizenDTO.getPinCode())")
    @Mapping(target = "id",ignore = true)
    Citizen toDAO(CitizenDTO citizenDTO);

    @Mapping(target = "fullName",expression = "java(citizen.getFirstName()+' '+citizen.getLastName())")
    @Mapping(target = "address",expression = "java(citizen.getAddress().substring(0,citizen.getAddress().indexOf('-')))")
    @Mapping(target = "pinCode",expression = "java(citizen.getAddress().substring(citizen.getAddress().indexOf('-')))")
    CitizenDTO toDTO(Citizen citizen);

    @Mapping(target = "id",ignore = true)
    void merge(@MappingTarget Citizen target,Citizen source);
}
