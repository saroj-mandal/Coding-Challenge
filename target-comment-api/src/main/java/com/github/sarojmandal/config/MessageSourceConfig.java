package com.github.sarojmandal.config;

import java.util.Set;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(name = "messageSource", ignoreResourceNotFound = false, value = {
		"classpath:message-source.properties" })
public class MessageSourceConfig {

	@Value("${badwords}")
	private Set<String> badWordList;

	/**
	 * @return the badWordList
	 */
	public Set<String> getBadWordList() {
		return badWordList;
	}
}
