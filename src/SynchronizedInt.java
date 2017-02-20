import java.util.Random;

class MyInt implements Runnable {
	private int value;
	public synchronized int get() {
		return value;
	}
	public synchronized void set(int value) {
		this.value = value;
	}
	@Override
	public synchronized void run() {
		Random rand = new Random();
		int val = rand.nextInt(50);
		set(val);
		System.out.println(get());
	}
}
public class SynchronizedInt {
	public void main() {
		MyInt myInt = new MyInt();
		Thread t1 = new Thread(myInt, "Thread-1");
		Thread t2 = new Thread(myInt, "Thread-2");
		Thread t3 = new Thread(myInt, "Thread-3");
		t1.start();
		t2.start();
		t3.start();
	}
}
