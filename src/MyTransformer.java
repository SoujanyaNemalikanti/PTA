import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import soot.Body;
import soot.MethodOrMethodContext;
import soot.Scene;
import soot.SceneTransformer;
import soot.SootMethod;
import soot.jimple.toolkits.callgraph.CallGraph;
import soot.jimple.toolkits.callgraph.CallGraphBuilder;
import soot.jimple.toolkits.callgraph.Targets;
import soot.toolkits.graph.BriefUnitGraph;
import soot.toolkits.graph.UnitGraph;
import soot.toolkits.graph.pdg.HashMutablePDG;
import soot.toolkits.graph.pdg.PDGNode;
import soot.toolkits.graph.pdg.PDGRegion;
import soot.toolkits.graph.pdg.ProgramDependenceGraph;

public class MyTransformer extends SceneTransformer {
	@Override
	protected void internalTransform(String phaseName,
			@SuppressWarnings("rawtypes") Map options) {
		SootMethod src = Scene.v().getMainClass().getMethodByName("main");
		System.out.println("src = " + src); 
		List<SootMethod> ep = new ArrayList<SootMethod>();
		//ep.add(Scene.v().getSootClass("TestPDG").getMethodByName("doPrint"));
		ep.add(Scene.v().getSootClass("TestPDGLoop").getMethodByName("printSum"));
		
		Scene.v().setEntryPoints(ep);
		System.out.println("ep ----" + ep);
		System.out.println("getEntryPoints : " + ep.get(0).getName());

		/**********
		 * Control Flow Graph - In Jimple Form
		 *********/
		List<String> workflowsNames = new ArrayList<String>();
		Scene.v().getClasses().forEach(cl -> {
			if (cl.isApplicationClass()) {
				cl.getMethods().forEach(mth -> {
					//if (mth.getName().contains("doPrint")) {
					if (mth.getName().contains("printSum")) {
						Body b = mth.retrieveActiveBody();
						UnitGraph cfg = new BriefUnitGraph(b);
						System.out.println("IsEntryPoint: " + mth.isEntryMethod());
						
						//EnhancedUnitGraph eug = new EnhancedUnitGraph(b);
						//for pDG
						ProgramDependenceGraph pdg = new HashMutablePDG(cfg);
						System.out.println("PDG: " + pdg);
						List<PDGRegion> pdgregions = ((HashMutablePDG) pdg)
								.getPDGRegions();
						System.out.println("regions of this PDG are : " + pdgregions);
						PDGNode head = pdg.GetStartNode();
						List<PDGNode> deps = pdg.getDependents(head);
						System.out.println("deps of this PDG are : " + deps);
					}
				});
			}
		});
		/*workflowsNames.forEach(wfn -> {
			System.out.println("WorkFlowName: " + wfn.toString());
		});*/

		/***********
		 * Call graph code *
		 ***********/

		CallGraph cg;// = Scene.v().getCallGraph();
		CallGraphBuilder cgb = new CallGraphBuilder();
		cgb.build();
		cg = cgb.getCallGraph();
		Iterator<MethodOrMethodContext> targets = new Targets(
				cg.edgesOutOf(src));
		while (targets.hasNext()) {
			SootMethod tgt = (SootMethod) targets.next();
			System.out.println(src + " may call " + tgt);
		}
	}
}
