package com.hanafish.hanawiki.service;

import com.hanafish.hanawiki.websocket.WebSocketServer;
import org.slf4j.MDC;
import org.springframework.scheduling.annotation.Async;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class WsService {
    @Resource
    public WebSocketServer webSocketServer;

    @Async
    public void sendInfo(String message, String logId) {
        MDC.put("LOG_ID", logId);
        webSocketServer.sendInfo(message);
    }
}

