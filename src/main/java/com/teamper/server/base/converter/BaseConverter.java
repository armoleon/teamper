package com.teamper.server.base.converter;

import com.teamper.server.base.repository.model.BaseEntity;
import com.teamper.server.base.service.model.BaseDto;
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
