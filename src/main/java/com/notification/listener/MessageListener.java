package com.notification.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.notification.conn.SenderConnPool;
import com.notification.exception.NotificationException;
import com.notification.model.QueueElement;
import com.notification.queue.NotificationQueue;
import com.notification.sender.MessageSender;

/**
 * It listens to the message from queue and passes on to the appropriate sender class
 * @author Hiral Salvi
 *
 */
@Component
public class MessageListener implements Runnable {

	private static final Logger logger = LoggerFactory.getLogger(MessageListener.class);
	private SenderConnPool connPool; 
	private NotificationQueue queue;
	
	public void setProperties(SenderConnPool instance, NotificationQueue queue) {
		this.connPool = instance;
		this.queue = queue;
	}
	
	@Override
	public void run() {
		while(true) {
			try {
				if(queue!=null && !queue.isEmpty()) {
					// read from the queue
					QueueElement e = queue.peekextNotification();
					
					// get sender for the given channel
					MessageSender s = connPool.getSender(e.getChannel());
					
					// invoke the actual 'send' action on the sender
					synchronized(s) {
						s.send(queue.getNextNotification().getNotification());	
					}
				} else {
					// sleep if the queue is empty
					Thread.sleep(1000);
				}
			} catch(InterruptedException | NotificationException e ) {
				logger.error("problem occurred - " + e.getMessage());
			}
		
		}
	}
}
