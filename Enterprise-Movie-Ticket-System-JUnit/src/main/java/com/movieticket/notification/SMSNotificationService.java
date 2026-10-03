package com.movieticket.notification;

import com.movieticket.model.User;

public class SMSNotificationService implements NotificationService {

	@Override
	public void sendNotification(User user, String message) {
		// TODO Auto-generated method stub
		System.out.println("SMS alert->" + user.getPhone() + "]:" + message);
	}

}
