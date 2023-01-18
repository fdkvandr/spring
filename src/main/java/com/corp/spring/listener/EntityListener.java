package com.corp.spring.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class EntityListener {

    @EventListener(condition = "#root.args[0].accessType.name() == 'READ'")
    @Order(10)
    public void acceptEntityRead(EntityEvent entityEvent) {
        log.info("Entity: " + entityEvent);
    }
}
