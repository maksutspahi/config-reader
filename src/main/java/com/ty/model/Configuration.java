package com.ty.model;

import com.ty.enums.Type;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "configuration")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Configuration {

    @Id
    private String id;

    private String name;

    private String value;

    private Type type;

    private boolean isActive;

    private String applicationName;

}
