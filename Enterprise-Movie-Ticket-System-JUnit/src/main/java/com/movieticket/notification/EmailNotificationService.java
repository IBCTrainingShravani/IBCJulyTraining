package com.movieticket.notification;

import com.movieticket.model.User;

public class EmailNotificationService implements NotificationService {

	@Override
	public void sendNotification(User user, String message) {
		// TODO Auto-generated method stub
		System.out.println("EMail->" + user.getEmail() + "]:" + message);
	}

}
