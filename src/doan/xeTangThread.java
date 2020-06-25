package doan;

public class xeTangThread extends Thread{
	Surface sf = new Surface();
	Transformation tm = new Transformation();
	@Override
	public void run(){
		for(int i = 0; i<10 ; i++) {
			try {
				veXe();
				sf.vienDan = tm.translation(sf.vienDan.x, sf.vienDan.y, 50, 0);
				sf.tiLeX *= 1.1;
				sf.tiLeY *= 1.1;
				sf.vienDan.y += -5;
				//Thread.sleep(100);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}	
	}
	
	public void veXe() {
		int a = 10 * 5;
		int b = 103 * 5;
		int c = 15 * 5;
		int d = 100 * 5;

		sf.a = a;
		sf.b = b;
		sf.c = c;
		sf.d = d;
		System.out.println(sf.a);
		System.out.println(sf.b);
		System.out.println(sf.c);
		System.out.println(sf.d);
		sf.repaint();
	}
}
