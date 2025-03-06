package edu.icet.pos.config;

import edu.icet.pos.dto.FoodItemsDto;
import edu.icet.pos.entity.FoodItemsEntity;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
    public ModelMapper geMapper(){
        //return new ModelMapper();

        ModelMapper modelMapper = new ModelMapper();

        modelMapper.typeMap(FoodItemsEntity.class, FoodItemsDto.class).addMappings(mapper -> {
            mapper.map(src -> src.getCategory().getId(), FoodItemsDto::setCategory);
        });

        return modelMapper;
    }
}
