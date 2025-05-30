package in.nareshit.raghu.controller;

import java.security.Principal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class ResourceRestController {

	@GetMapping("/principal")
	public Principal getUserData(Principal p) {
		return p;
	}
}
