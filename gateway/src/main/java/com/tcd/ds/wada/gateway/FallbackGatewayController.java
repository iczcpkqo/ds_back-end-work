package com.tcd.ds.wada.gateway;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallbackGatewayController {

	@GetMapping("/fallback")
	public String defaultMessage() {
		return "System not able to prcess the request. Sorry for Inconvenience. Please try again later";
	}

}
