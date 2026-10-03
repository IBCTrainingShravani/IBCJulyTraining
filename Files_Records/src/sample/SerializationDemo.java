package sample;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

class DriverProfile implements Serializable {
	//private static final long serialVersionUID = 1L;

	private final String driverId;
	private final String name;
	private transient String sessionSecurityToken;

	public DriverProfile(String driverId, String name, String token) {
		super();
		this.driverId = driverId;
		this.name = name;
		this.sessionSecurityToken = token;
	}

	@Override
	public String toString() {
		return "DriverProfile [driverId=" + driverId + ", name=" + name + ",Token=" + sessionSecurityToken + "]";
	}

}

public class SerializationDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String serFile = "driver_state.ser";
		DriverProfile driver = new DriverProfile("DRV-901", "Kishore Kumar", "SECRET_TOKEN_XYZ");

		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(serFile))) {
			oos.writeObject(driver);
			System.out.println("Serialized object:" + driver);

		} catch (IOException e) {
			e.printStackTrace();
		}

		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(serFile))) {
			DriverProfile restored = (DriverProfile) ois.readObject();
			System.out.println("Restored object:" + restored);
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}
