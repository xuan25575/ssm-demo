package com.training.CFX;

import javax.jws.WebService;

@WebService
public interface HelloService {
    String say(String name);
}
