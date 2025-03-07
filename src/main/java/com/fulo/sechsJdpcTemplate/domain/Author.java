package com.fulo.sechsJdpcTemplate.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Author {

    //L capital allows null
    private Long id;

    private String name;

    private Integer age;
}
