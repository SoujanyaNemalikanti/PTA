import java.util.Random;

class Increment implements Runnable {
	int value;
	boolean isSync;
	public Increment() {
		Random generator = new Random();
		value = generator.nextInt(10);
	}
	public synchronized void setValue(int val) {
		value = val;
	}
	public int getValue() {
		return value;
	}
	@Override
	public void run() {
		setValue(getValue() + 1);
		System.out.println(getValue() + Thread.currentThread().getName());
	}
}
public class IncrementerEx {
	public void main() {
		Increment sg1 = new Increment();
		Thread t1 = new Thread(sg1, "Thread-1");
		Thread t2 = new Thread(sg1, "Thread-2");
		Thread t3 = new Thread(sg1, "Thread-3");
		if (sg1.value != 0) {
			t1.start();
			t2.start();
			t3.start();
		}
	}
}