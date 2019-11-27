package com.uca.multithreading;

public class PruducerConsumer {

	class MyQueue {
		int i = 0;

		private boolean readReady = false;

		@SuppressWarnings("static-access")
		public synchronized void read() {
			if (!readReady) {
				try {
					wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			System.out.println("reading:" + i);
			try {
				Thread.currentThread().sleep(500L);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			readReady = !readReady;
			notify();

		}

		@SuppressWarnings("static-access")
		public synchronized void write() {
			if (readReady) {
				try {
					wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			i++;
			System.out.println("reading:" + i);
			try {
				Thread.currentThread().sleep(500L);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			readReady = !readReady;
			notify();
		}

	}

	class Producer extends Thread {
		MyQueue q;

		public Producer(MyQueue q) {
			this.q = q;
		}

		public void run() {
			while (true) {
				write();
			}
		}

		private void write() {
			q.write();
		}
	}

	class Consumer extends Thread {
		MyQueue q;

		public Consumer(MyQueue q) {
			this.q = q;
		}

		public void run() {
			while (true) {
				read();
			}
		}

		private void read() {
			q.read();
		}
	}

	public static void main(String[] args) throws InterruptedException {
		PruducerConsumer p = new PruducerConsumer();
		p.test();
	}

	private void test() {
		MyQueue q = new MyQueue();
		Thread t1 = new Thread(new Producer(q));
		Thread t2 = new Thread(new Consumer(q));
		t1.start();
		t2.start();
	}

}
