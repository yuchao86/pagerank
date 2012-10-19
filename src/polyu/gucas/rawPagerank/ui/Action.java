/**
 * 香港理工大学-中国科学院研究生院数据挖掘-PageRank算法实现
 * @author chaoyu
 * @right Copyright by PolyU team
 * @github https://github.com/yuchao86/pagerank.git
 * @date 2012-10-18
 */
package polyu.gucas.rawPagerank.ui;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import polyu.gucas.rawPagerank.robot.Irobot;
import polyu.gucas.rawPagerank.pagerank.StringMap;
import polyu.gucas.rawPagerank.pagerank.UrlPageRank;
import polyu.gucas.rawPagerank.pagerank.Node;
import polyu.gucas.rawPagerank.pagerank.NodeMatchScoreCompare;
import polyu.gucas.rawPagerank.wordretrieve.IndexBuilder;

public class Action {
	private static Map<String, Node> nodemap;

	private static Set<String> scorednode;

	IndexBuilder ib;

	UrlPageRank ur;

	Irobot i;

	StringMap sm[];

	SimpleSearch ss;

	public Action(SimpleSearch ss1) {
		ss = ss1;
		scorednode = new HashSet<String>();
		nodemap = new HashMap<String, Node>();
	}

	public void process() throws Exception {
		new Thread() {
			public void run() {
				if (ss.Url.getText() == null) {

				} else {
					try {
						i = new Irobot(ss.Url.getText());
						sm = i.getUrls(10);

						ur = new UrlPageRank(sm, i.getUrlsize());
						ur.print(10000, 0.85);
						ib = getIndexBuilder(ur);

						HashMap<String, Node> hs = ur.getStringUrl();
						Iterator<String> it = (hs.keySet()).iterator();
						String key;

						while (it.hasNext()) {
							key = (String) it.next();

							Node n = (Node) hs.get(key);
							String result = n.getName() + "\n----Value is "
									+ n.getValue();
							ss.print3(result);
						}

						String result;
						it = ib.getDocIterator();
						while (it.hasNext()) {
							key = (String) it.next();
							result = "The docment name is **" + key + "\n";
							
							Iterator<String> it1 = ib.getDocTerm(key).iterator();
							while(it1.hasNext()){
								result = result + (String)it1.next() + " ";
							}
							
							ss.print1(result);
						}
					} catch (Exception e) {

					}

				}
			}
		}.run();
	}

	public void search() {
		new Thread() {
			public void run() {
				if (ss.searchProcess.getText() != null) {
					String in = ss.searchProcess.getText();

					String query[] = in.split(" ");

					if (query[0].equalsIgnoreCase("backlink")) {
						if (query.length > 2)
							System.out
									.println("backlink [URL]. No more than 1 variable.");
						else {

						}
					} else {
						// for each document who has the term, they get one
						// mark.
						for (int j = 0; j < query.length; j++) {
							Iterator<String> it = ib.lookUpDocForTerm(query[j]);
							setTermDocScore(it);
						}

						printResult(1);
					}
				}
			}
		}.run();
	}

	public void printResult(int resultlength) {
		if (scorednode.size() == 0) {
			System.out.println("There are no results matched!");
		} else {
			Iterator<String> it = scorednode.iterator();
			Node sortnode[] = new Node[scorednode.size()];
			int sorti = 0;

			while (it.hasNext()) {
				String name = (String) it.next();
				Node n = nodemap.get(name);
				sortnode[sorti] = n;
				sorti++;
			}

			Arrays.sort(sortnode, new NodeMatchScoreCompare());

			for (int j = 0; j < sorti; j++) {
				ss.print2("The node with name " + sortnode[j].getName()
						+ " get the score **" + sortnode[j].getMatchScore()
						+ "** and get the pagerank value: "
						+ sortnode[j].getValue());

				sortnode[j].setMatchScore(0); // wait for new search
				scorednode.remove(sortnode[j].getName()); // empty it.
			}
		}
	}

	public void setTermDocScore(Iterator<String> it) {
		while (it.hasNext()) {
			String nodename = (String) it.next();

			Node n = nodemap.get(nodename);

			int j = n.getMatchScore();
			j++;
			n.setMatchScore(j);

			// add the node name to the raw right set.
			if (!scorednode.contains(nodename)) {
				scorednode.add(nodename);
			}
		}
	}

	public static IndexBuilder getIndexBuilder(UrlPageRank ur) {
		Node nodes[] = ur.getNode();
		int index = ur.getNodeSize();

		IndexBuilder ib = new IndexBuilder();
		for (int j = 0; j < index; j++) {
			try {
				ib.feedUrl(nodes[j].getName());
				nodemap.put(nodes[j].getName(), nodes[j]);
			} catch (IOException e) {
				System.err
						.println("URL IS Not Accesible:" + nodes[j].getName());
			}
		}

		return ib;
	}
}

