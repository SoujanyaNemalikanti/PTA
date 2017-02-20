import soot.PackManager;
import soot.SceneTransformer;
import soot.Transform;

public class runSoot {

	public static String[] setSootParams() {
		String params = "";
		params += "-p ";
		params += "cg ";
		params += "library:any-subtype,all-reachable:true ";
		params += "-W ";
		params += "-no-bodies-for-excluded ";
		params += "-full-resolver ";
		params += "-pp ";
		params += "-process-dir " + System.getProperty("user.dir") + "/bin";
		return params.split(" ");
	}

	public static void main(String[] args) {
		SceneTransformer trans = new MyTransformer();
		PackManager.v().getPack("cg")
				.add(new Transform("cg.MyTranformer", trans));
		soot.Main.v().run(setSootParams());
	}
}
