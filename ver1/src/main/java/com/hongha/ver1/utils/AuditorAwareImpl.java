package com.hongha.ver1.utils;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class AuditorAwareImpl implements AuditorAware<String> {

	@Override
	public Optional<String> getCurrentAuditor() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Optional<String> username;
		if (authentication != null) {
			username = Optional.of(authentication.getName());

		} else {
			username = Optional.of("anonymous");
		}
		return username;
	}

}
