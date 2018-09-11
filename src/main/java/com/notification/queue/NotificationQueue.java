package com.notification.queue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.notification.model.Notification;
import com.notification.model.QueueElement;

/**
 * Queue implementation that contains the notification and channel
 * @author Hiral Salvi
 *
 */
@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class NotificationQueue {

	private BlockingQueue<QueueElement> queue = new LinkedBlockingQueue<>();

	/**
	 * Adds new elements to the queue
	 * @param notification
	 * @param channel
	 */
	public void addNotificationInqueue(Notification notification, String channel) {
		QueueElement e = new QueueElement(notification, channel);
		queue.add(e);
	}
	
	/**
	 * Removes element from the queue
	 * @return
	 * @throws InterruptedException
	 */
	public synchronized QueueElement getNextNotification() throws InterruptedException {
		return queue.take();
	}

	/**
	 * Checks for the next element from the queue, does not change the queue structure
	 * @return
	 * @throws InterruptedException
	 */
	public  QueueElement peekextNotification() throws InterruptedException {
		return queue.peek();
	}
	
	/**
	 * Checks if the queue is empty
	 * @return
	 */
	public boolean isEmpty()  {
		return queue.isEmpty();
	}
}
