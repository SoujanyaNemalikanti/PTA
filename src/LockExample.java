import java.util.Date;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class MessageJob implements Runnable {
	private MessageQueue messageQueue;

	public MessageJob(MessageQueue messageQueue) {
		this.messageQueue = messageQueue;
	}

	@Override
	public void run() {
		System.out.printf("Going to print a message\n"
				+ Thread.currentThread().getName());
		messageQueue.printMessageJob(new Object());
	}
}

class MessageQueue {
	private final Lock queueLock = new ReentrantLock();

	public void printMessageJob(Object message) {
		queueLock.lock();
		try {
			Long duration = (long) (Math.random() * 3000);
			System.out.println(Thread.currentThread().getName()
					+ ": PrintQueue: Printing a Message during "
					+ (duration / 1000) + " seconds :: Time - " + new Date());
			Thread.sleep(duration);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			System.out.println("The message has been printed - "
					+ Thread.currentThread().getName());
			queueLock.unlock();
		}
	}
}

public class LockExample {
	public void main() {
		MessageQueue printerQueue = new MessageQueue();
		Thread thread[] = new Thread[4];
		for (int i = 0; i < 4; i++) {
			thread[i] = new Thread(new MessageJob(printerQueue), "Thread " + i);
		}
		for (int i = 0; i < 4; i++) {
			thread[i].start();
		}
	}
}
