package com.notification.controller;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import com.notification.ApplicationConfiguration;
import com.notification.conn.SenderConnPool;
import com.notification.exception.NotificationException;
import com.notification.listener.MessageListener;
import com.notification.queue.NotificationQueue;

/**
 * It keeps object of the queue, listener and starts the listener with given configuration 
 * @author Hiral Salvi
 *
 */
@Controller
@RestController
public class ConfigInitializer {

	@Autowired
	private ApplicationConfiguration configuration;

	@Autowired
	public NotificationQueue queue;
	
	@Autowired
	public MessageListener listener;
	
	public ApplicationConfiguration getConfiguration() {
		return configuration;
	}
	
	public void setConfiguration(ApplicationConfiguration configuration) {
		this.configuration = configuration;
	
	} 
	
	/**
	 * Starts listener by setting the queue to be read and configuration to be used
	 * 
	 * @throws NotificationException
	 * @throws InterruptedException
	 */
	@PostConstruct
	public void startListerners() throws NotificationException, InterruptedException {
		Executor executor = Executors.newSingleThreadExecutor();
		listener.setProperties(SenderConnPool.getInstance(configuration), queue);
		executor.execute(listener);
	}

	/**
	 * returns of the given channel is supported or not
	 * @param channel
	 * @return
	 * @throws NotificationException
	 */
	public boolean isChannelSupported(String channel) throws NotificationException {
		return SenderConnPool.getInstance(configuration).isChannelSupported(channel);
	}
}
