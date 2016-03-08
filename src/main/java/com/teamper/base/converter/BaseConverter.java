package com.teamper.base.converter;

import com.teamper.base.repository.model.BaseEntity;
import com.teamper.base.service.model.BaseDto;
import org.modelmapper.ModelMapper;

public class BaseConverter {

    private static ModelMapper modelMapper = new ModelMapper();

    public static <T extends BaseDto> T toDto(BaseEntity baseEntity, Class<T> clazz) {
        return modelMapper.map(baseEntity, clazz);
    }

    public static <T extends BaseEntity> T toEntity(BaseDto baseDto, Class<T> clazz) {
        return modelMapper.map(baseDto, clazz);
    }
}
