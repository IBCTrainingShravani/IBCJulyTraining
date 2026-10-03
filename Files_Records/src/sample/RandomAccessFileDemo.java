package sample;

import java.io.IOException;
import java.io.RandomAccessFile;

public class RandomAccessFileDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String file = "ledger.dat";

		try (RandomAccessFile raf = new RandomAccessFile(file, "rw")) {

			raf.writeUTF("TXN_1001");
			raf.writeDouble(450.50);

			long secondRecordPointer = raf.getFilePointer();

			raf.writeUTF("TXN_1002");
			raf.writeDouble(1200.75);

			raf.seek(secondRecordPointer);
			System.out.println("Jumped directly to offset" + secondRecordPointer);
			System.out.println("Txn ID:" + raf.readUTF());

			System.out.println("AMount:" + raf.readDouble());

			raf.seek(0);
			System.out.println("Rewound to 0->Txn ID:" + raf.readUTF());

		} catch (IOException e) {
			System.err.println("i/o failure:" + e.getMessage());
		}
	}

}
