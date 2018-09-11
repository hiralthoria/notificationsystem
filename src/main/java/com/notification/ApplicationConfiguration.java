package com.notification;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.stereotype.Component;

/**
 * It reads configuration for supported channel and related Sender classes
 * @author Hiral Salvi
 *
 */
@Component
@PropertySource("classpath:config.properties")
@ConfigurationProperties("notification")
public class ApplicationConfiguration {

	//channels and notification senders
    private List<String> channels = new ArrayList<>();
    private List<String> senders = new ArrayList<>();

    public List<String> getChannels() {
		return channels;
	}

	public void setChannels(List<String> channels) {
		this.channels = channels;
	}

	@Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
        return new PropertySourcesPlaceholderConfigurer();
    }

	public List<String> getSenders() {
		return senders;
	}

	public void setSenders(List<String> senders) {
		this.senders = senders;
	}

}