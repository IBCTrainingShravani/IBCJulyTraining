package com.dispatch.queue;

import java.util.concurrent.BlockingQueue;

import com.dispatch.event.RideRequestEvent;

public class RideRequestProducer {
	private final BlockingQueue<RideRequestEvent> sharedQueue;

	public RideRequestProducer(BlockingQueue<RideRequestEvent> sharedQueue) {
		this.sharedQueue = sharedQueue;
	}

	public boolean publishRequest(RideRequestEvent event) {
		return sharedQueue.offer(event);
	}
}
