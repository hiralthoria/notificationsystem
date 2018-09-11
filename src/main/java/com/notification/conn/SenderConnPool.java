package com.notification.conn;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import com.notification.ApplicationConfiguration;
import com.notification.exception.NotificationException;
import com.notification.sender.MessageSender;

/**
 * Pool of Notification message senders.
 * It creates NotificationSender objects based on the data loaded through application configuration class
 * @author Hiral Salvi
 *
 */
public class SenderConnPool {

	private static SenderConnPool instance;
	private Map<String,MessageSender> connPool = new ConcurrentHashMap<>();
	private static final Logger logger = LoggerFactory.getLogger(SenderConnPool.class);
	
	/**
	 * Keep only one instance
	 * @param configuration
	 * @return
	 * @throws NotificationException
	 */
	public static SenderConnPool getInstance(ApplicationConfiguration configuration) throws NotificationException {
		if(instance==null)
			instance = new SenderConnPool(configuration);
		return instance;
	}
	
	private SenderConnPool(ApplicationConfiguration configuration) throws NotificationException {
		if(connPool==null ||connPool.isEmpty()) 
			initializeSenders(configuration);
	}
	
	/**
	 * reads configuration and registers message senders for all supported channels
	 * @param configuration
	 * @throws NotificationException
	 */
	private void initializeSenders(ApplicationConfiguration configuration) 
			throws NotificationException {
		List<String> senders = configuration.getSenders();
		int index=0;
		
		for(String channel : configuration.getChannels()) {
			if(senders.size()<=index)
				continue;
			String sender = senders.get(index++);
			registerSender(channel, sender);
		}
	}

	/**
	 * Creates appropriate object of the MessageSender class and puts them in the map
	 * @param channel - supported channel
	 * @param sender - sender class to be used for the channel
	 * @throws NotificationException
	 */
	private void registerSender(String channel, String sender) throws NotificationException {
		if(StringUtils.isEmpty(channel) || StringUtils.isEmpty(sender)) {
			logger.error("Channel or Sender information is missing while registration of new channel");
			throw new NotificationException("Channel or Sender information is missing while registration of new channel");
		}
		try {
			Class c = Class.forName(sender);
			connPool.put(channel.toLowerCase(),(MessageSender)c.newInstance());
		} catch (Exception e) {
			logger.error("Unable to initialize sender for the channel " + channel);
			throw new NotificationException("Unable to initialize sender for the channel " + channel);
		}
	}
	
	/**
	 * Gets appropriate Message sender for the given channel from the pool/map
	 * @param channel
	 * @return
	 * @throws NotificationException
	 */
	public MessageSender getSender(String channel) throws NotificationException {
		if(!connPool.isEmpty() && connPool.containsKey(channel.toLowerCase()))
			return connPool.get(channel.toLowerCase());
		logger.error(channel + " channel is not supported");
		throw new NotificationException(channel + " channel is not supported");
	}

	/**
	 * Checks whether given channel is supported or not
	 * @param channel
	 * @return
	 */
	public boolean isChannelSupported(String channel) {
		if(!connPool.isEmpty() && connPool.containsKey(channel.toLowerCase()))
			return true;
		return false;
	}
	
}
