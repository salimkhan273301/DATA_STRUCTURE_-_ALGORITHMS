package multithreading;

/**
 * 
 */
public class OneToManyTask implements Runnable {

	
	public void run(){
		System.out.println("Cooking task is done by ->"+Thread.currentThread().getName());
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		OneToManyTask task=new OneToManyTask();
		Thread t=new Thread(task);
		Thread t1=new Thread(task);
		Thread t2=new Thread(task);
		Thread t3=new Thread(task);
		
		t.start();
		t1.start();
		t2.start();
		t3.start();
		
		
		

	}

}
