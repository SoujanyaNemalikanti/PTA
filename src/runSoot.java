import soot.PackManager;
import soot.SceneTransformer;
import soot.Transform;

public class runSoot {

	public static String[] setSootParams() {
		//String params = "-allow-phantom-refs -cp cg -W ";
		String params = "-allow-phantom-refs -cp -p cg -W ";
		//params += "-p ";
		/*params += "cg ";
		params += "library:any-subtype ";
		params += "-W ";
		params += "-no-bodies-for-excluded ";
		params += "-full-resolver ";*/
		params += "-pp ";
		params += "-process-dir " + System.getProperty("user.dir") + "/bin";
		System.out.println(params);
		return params.split(" ");
	//-allow-phantom-refs -cp . IncrementerEx	
	}

	public static void main(String[] args) {
		SceneTransformer trans = new MyTransformer();
		PackManager.v().getPack("cg")
				.add(new Transform("cg.MyTransformer", trans));
		soot.Main.v().run(setSootParams());
		//soot.Main.v().run(args);
	}
}
