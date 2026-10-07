package com.hotel.shmrs.factory;

import com.hotel.shmrs.model.entity.DeluxeRoom;
import com.hotel.shmrs.model.entity.PremiumRoom;
import com.hotel.shmrs.model.entity.Room;
import com.hotel.shmrs.model.entity.SuiteRoom;
import com.hotel.shmrs.model.enums.RoomType;

public class RoomFactory {
	public static Room createRoom(RoomType type, int roomNumber) {
		return switch (type) {
		case DELUXE -> new DeluxeRoom(roomNumber);
		case PREMIUM -> new PremiumRoom(roomNumber);
		case SUITE -> new SuiteRoom(roomNumber);

		};
	}
}