package com.example.game.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * @Author: zhangat
 * @Date: 2020-1-2 0002 14:02
 * @DESCIBE
 */
@Configuration
public class WebSocketConfig {

   @Bean
   public ServerEndpointExporter serverEndpointExporter(){
       return new ServerEndpointExporter();
   }
}
