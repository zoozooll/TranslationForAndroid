/**
 * 
 */
package com.mouselee.translation.test;

/**
 * @author aaronli
 *
 */
public class TestXml {
	
	private Thread bar;

	public void begin() {
		bar = new AddNumInThread();
		bar.start();
		/*try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		bar = new AddNumInThread();
		bar.start();
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TestXml test = new TestXml();
		test.begin();
	}

}

class AddNumInThread extends Thread {
	private static Integer num = 0;

	/* (non-Javadoc)
	 * @see java.lang.Thread#run()
	 */
	@Override
	public void run() {
		while (num < 100) {
			synchronized (num) {
				System.out.println("num in "+ this.getId() + " = "+num);
				num ++;
			}
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		super.run();
	}
	
	
}