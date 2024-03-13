package com.example.demo.appdomain.involvementapplicationsector;

import com.example.demo.appdomain.involvementapplication.InvolvementApplication;
import lombok.Value;

import java.util.Set;

@FunctionalInterface
public interface FindActiveSectorIdsByInvolvementApplicationId {

    Set<Long> execute(Request request);

    @Value(staticConstructor = "of")
    class Request {
        InvolvementApplication.Id involvementApplicationId;
    }
}
