package multithreading;

public class MyThread extends Thread {
	

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("RUNNING");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//System.out.println("Again got the resource");
		
	}

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		Thread t=new MyThread();
		System.out.println(t.getState());
		t.start();
		System.out.println(t.getState());
		Thread.sleep(100);
		System.out.println(t.getState());
		t.join();
		System.out.println(t.getState());
		

	}

}
