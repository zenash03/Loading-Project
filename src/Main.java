import java.util.stream.Stream;

public class Main {
	public static void printMsgWithProgressBar(String message, int length, long timeInterval)
	{
//		char incomplete = '░';
//		char complete = '█';
		char incomplete = ' ';
		char complete = '#';
		StringBuilder builder = new StringBuilder();
		Stream.generate(() -> incomplete).limit(length).forEach(builder::append);
		int counter = 0;
		int leng = 0;
		while(counter <= 100) {
			System.out.print("\r" + message + " [" + builder + "] " + counter + "%");
			try {
				Thread.sleep(timeInterval);
			} catch (InterruptedException e) {

			}
			if(counter % (100 / length) == 0 && leng < length) {
				builder.replace(leng,leng+1,String.valueOf(complete));
				leng++;
			}
			counter++;
		}
	}

	public static void printLoadingSpin(String message, long timeInterval) {
		char[] animationChars = new char[]{'|', '/', '-', '\\'};

		int counter = 0;
		while(counter <= 100) {
			System.out.print(message + " : " + counter + "% " + animationChars[counter % 4] + "\r");

			try {
				Thread.sleep(timeInterval);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			counter++;
		}
	}
	public static void printArrow(String message, int length, long timeInterval){
		char[] animationChars = new char[]{'-','>'};
		char strip = '-';
		char rArrow = '>';
		char lArrow = '<';

		StringBuilder builder = new StringBuilder();
		Stream.generate(() -> strip).limit(length).forEach(builder::append);
		int counter = 0;
		int leng = 0;
		boolean reverse = false;
		while(counter <= 100) {
			if(!reverse){
				builder.replace(leng,leng+1,String.valueOf(rArrow));
				builder.replace(leng+1,leng+2,String.valueOf(rArrow));
			}else {
				builder.replace(leng-1,leng,String.valueOf(lArrow));
				if(leng > 1){
					builder.replace(leng-2,leng-1,String.valueOf(lArrow));
				}else {
					builder.replace(leng-1,leng,String.valueOf(lArrow));
				}
			}
			System.out.print("\r" + message + " [" + builder + "] ");
			if(counter == 100) {
				System.out.print("Done");
			}
			try {
				Thread.sleep(timeInterval);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if(!reverse){
				builder.replace(leng,leng+1,String.valueOf(strip));
				builder.replace(leng+1,leng+2,String.valueOf(strip));
			}else {
				builder.replace(leng-1,leng,String.valueOf(strip));
				if(leng > 1){
					builder.replace(leng-2,leng-1,String.valueOf(strip));
				}else {
					builder.replace(leng-1,leng,String.valueOf(strip));
				}
			}
			if(leng < length-2 && !reverse){
				leng++;
				reverse = false;
			}
			else {
				leng--;
				reverse = (leng == 0) ? false : true;
				leng = (leng == 0) ? 0 : leng;
			}
			counter++;
		}
	}
	public static void main(String[] args) {
		printLoadingSpin("Loading", 100);
		System.out.println();
		printMsgWithProgressBar("Loading", 30, 60);
		System.out.println();
		printArrow("Arrow", 30,60);
		System.out.println();
	}
}