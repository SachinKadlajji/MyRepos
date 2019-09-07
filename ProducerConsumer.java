package newPack;


public class ProducerConsumer 
{
	public static String s="Key";
	public static boolean isEven=false;
	public static int i =0;
	public static void main(String[] args) 
	{
		Runnable r1 = new Runnable()
				{
				public void run()
				{
					synchronized(s)
					{
						while(true)
						{
							if(isEven)
							{
								try {
									s.wait();
								} catch (InterruptedException e) {
									
								}
							}
							
							System.out.println(i+" b"+Thread.currentThread().getName());try {
								Thread.sleep(1000);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}i=i+1;isEven=true;
							s.notify();
						}
					}
				}
				};
				
				Runnable r2 = new Runnable()
				{
				public void run()
				{
					synchronized(s)
					{
						while(true)
						{
							if(!isEven)
							{
								try {
									s.wait();
								} catch (InterruptedException e) {
									
								}
							}
							
							System.out.println(i+" a"+Thread.currentThread().getName());i=i+1;isEven=false;
							s.notify();
						}
					}
				}
				};
				
				Thread t1= new Thread(r1);
				Thread t2 = new Thread(r2);
				t1.start();t2.start();
		
	}
}
