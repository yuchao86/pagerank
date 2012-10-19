/**
 * 香港理工大学-中国科学院研究生院数据挖掘-PageRank算法实现
 * @author chaoyu
 * @right Copyright by PolyU team
 * @github https://github.com/yuchao86/pagerank.git
 * @date 2012-10-13
 */
package polyu.gucas.rawPagerank.pagerank;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import polyu.gucas.rawPagerank.pagerank.Node;

public class UrlPageRank implements Serializable{
	
	private static final long serialVersionUID = 1L;
	Node nodes[] = new Node[10000]; // Array of Nodes.

	HashMap<String, Node> StringUrl = new HashMap<String, Node>(); // Map the

	// Node
	// object to
	// the node
	// name.

	int index = 0;

	public UrlPageRank(StringMap sm[], int size) {
		if (size > 10000) {
			System.out.println("There're more than 10000 nodes");
		} else {
			for (int i = 0; i < size; i++) {
				String res = sm[i].getResource();
				String des = sm[i].getDestination();

				if (!StringUrl.containsKey(res)) {
					nodes[index] = new Node(res, 0);
					StringUrl.put(res, nodes[index]);
					index++;
				}

				if (!StringUrl.containsKey(des)) {
					nodes[index] = new Node(des, 0);
					nodes[index].addNodein((Node) StringUrl.get(res));
					StringUrl.put(des, nodes[index]);
					index++;
				} else {
					Node n = (Node) StringUrl.get(des);
					n.addNodein((Node) StringUrl.get(res));
				}

				Node nt = (Node) StringUrl.get(des);
				Node nt1 = (Node) StringUrl.get(res);
				nt1.addNodeout(nt);
			}
		}
	}
	
	public HashMap<String, Node> getStringUrl(){
		return StringUrl;
	}

	public Node[] getNode() {
		return nodes;
	}

	public int getNodeSize() {
		return index;
	}
	
	public int getNodeValue(String name){
		Node n1 = StringUrl.get(name);
		return (int)(n1.getValue()*1000);		
	}

	public void saveFile(String path) throws Exception {
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(
				path));

		out.writeObject(this);
		
		out.close();
		System.out.println(index + " records has been write into " + path);
	}
	
	public void print(){
		for (int i = 0; i < index; i++) {
			double dd = nodes[i].getValue();
			double ii = dd * 1000;
			int iii = (int) ii;
			System.out
					.print("The Node " + nodes[i].getName() + "'s value is: ");
			System.out.println(iii);
		}
	}

	public void print(int times, double d) {
		for (int i = 0; i < times; i++) {
			PageRank.calc(nodes, d, index);
		}

		Arrays.sort(nodes, 0, index, new NodeMatchScoreCompare());
		
		print();	
	}
}
