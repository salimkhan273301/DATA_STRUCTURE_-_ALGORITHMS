package multithreading;

public class OneThreadOneTask extends Thread {
	
	

	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		System.out.println("Task executed by "+Thread.currentThread().getName());
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		OneThreadOneTask t=new OneThreadOneTask();
		t.start();
	}

}
