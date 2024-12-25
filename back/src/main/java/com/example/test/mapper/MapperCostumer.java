    package com.example.test.mapper;

    import com.example.test.dto.CostumerDto;
    import com.example.test.entity.Costumer;
    import org.mapstruct.Mapper;
    import org.springframework.stereotype.Component;

    @Component
    public class MapperCostumer {


        public static Costumer toEntity(CostumerDto dto){
           return Costumer.builder().oid(dto.getOid())
                   .name(dto.getName())
                   .email(dto.getEmail())
                   .password(dto.getPassword())

                   .build();
       }


        public  CostumerDto toDto(Costumer entity){
            return CostumerDto.builder().oid(entity.getOid())
                    .name(entity.getName())
                    .email(entity.getEmail())
                    .password(entity.getPassword())
                    .role(String.valueOf(entity.getRole()))
                    .build();
        }
    }
