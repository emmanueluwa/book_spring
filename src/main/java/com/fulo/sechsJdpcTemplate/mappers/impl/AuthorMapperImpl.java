package com.fulo.sechsJdpcTemplate.mappers.impl;

import com.fulo.sechsJdpcTemplate.domain.dto.AuthorDto;
import com.fulo.sechsJdpcTemplate.domain.entities.AuthorEntity;
import com.fulo.sechsJdpcTemplate.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;


//give ability to inject
@Component
public class AuthorMapperImpl implements Mapper<AuthorEntity, AuthorDto> {

    private ModelMapper modelMapper;

    public AuthorMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public AuthorDto mapTo(AuthorEntity authorEntity) {
        return modelMapper.map(authorEntity, AuthorDto.class);
    }

    @Override
    public AuthorEntity mapFrom(AuthorDto authorDto) {
        return modelMapper.map(authorDto, AuthorEntity.class);
    }
}
