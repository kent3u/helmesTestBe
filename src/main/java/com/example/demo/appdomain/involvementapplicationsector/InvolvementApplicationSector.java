package com.example.demo.appdomain.involvementapplicationsector;

import com.example.demo.appdomain.involvementapplication.InvolvementApplication;
import lombok.Value;

@Value(staticConstructor = "of")
public class InvolvementApplicationSector {

    Long id;
    InvolvementApplication.Id involvementApplicationId;
    Long sectorId;
}
