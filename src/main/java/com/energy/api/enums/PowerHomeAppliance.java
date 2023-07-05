package com.energy.api.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum PowerHomeAppliance {
    @JsonProperty("110v")
    FEMALE,
    @JsonProperty("Masculino")
    MALE
}
