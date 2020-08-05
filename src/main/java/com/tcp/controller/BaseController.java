
package com.tcp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tcp.tcp.service.TestService;
import com.tcp.tcp.service.impl.StartChargingServiceImp;

@Component
public class BaseController {

	@Autowired
	protected TestService testService;

	@Autowired
	protected StartChargingServiceImp startCharging;
}
