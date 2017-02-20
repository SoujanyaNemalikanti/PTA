import java.util.Random;

class SetGet implements Runnable {
	int value;
	public SetGet() {
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
		System.out.println(getValue() + Thread.currentThread().getName());
	}
}
public class SetterGetter {
	public void main() {
		SetGet sg1 = new SetGet();
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