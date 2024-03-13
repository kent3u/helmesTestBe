package com.example.demo.appdomain.sector;

import lombok.Value;

@Value(staticConstructor = "of")
public class Sector {

    Long id;
    String name;
    Long parentId;
}
