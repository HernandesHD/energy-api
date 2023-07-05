package com.energy.api.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Gender {
    @JsonProperty("Feminino")
    FEMALE,
    @JsonProperty("Masculino")
    MALE
}
