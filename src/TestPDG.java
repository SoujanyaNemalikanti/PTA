
public class TestPDG {
	
	public void doPrint() {
		int i = 1;	//1
		if (i == 1) { //2
			System.out.println("Entered branch 1 "); //3
		} else {
			i = 1; //4
			System.out.println("Entered branch 2 ");	//5
			}
		System.out.println(i + "Last statement"); //6
	} 
}