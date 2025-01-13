package com.zurad.java.resourceserver.events;

import org.springframework.context.event.EventListener;
import org.springframework.security.authorization.event.AuthorizationDeniedEvent;
import org.springframework.stereotype.Component;

@Component
public class AuthorizationEvents {

    @EventListener
    public void onFailure(AuthorizationDeniedEvent deniedEvent) {
        // TODO: figure out logging without Lombok
//        log.error("Authorization failed for the user : {} due to : {}", deniedEvent.getAuthentication().get().getName(),
//            deniedEvent.getAuthorizationDecision().toString());
    }

}