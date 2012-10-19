/**
 * 香港理工大学-中国科学院研究生院数据挖掘-PageRank算法实现
 * @author chaoyu
 * @right Copyright by PolyU team
 * @github https://github.com/yuchao86/pagerank.git
 * @date 2012-10-18
 */
package polyu.gucas.rawPagerank.UnitTest;
/*
 * For web search project.
 * The main runnable entry.
 * */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Arrays;

import polyu.gucas.rawPagerank.robot.Irobot;
import polyu.gucas.rawPagerank.pagerank.StringMap;
import polyu.gucas.rawPagerank.pagerank.UrlPageRank;
import polyu.gucas.rawPagerank.pagerank.Node;
import polyu.gucas.rawPagerank.wordretrieve.IndexBuilder;
import polyu.gucas.rawPagerank.pagerank.NodeMatchScoreCompare;

public class SimpleSearch {
	private static Map<String, Node> nodemap = new HashMap<String, Node>();

	private static Set<String> scorednode = new HashSet<String>();

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

	public static void printResult(int resultlength) {
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
				System.out.println("The node with name "
						+ sortnode[j].getName() + " get the score "
						+ sortnode[j].getMatchScore());
				System.out.println("and get the pagerank value: "
						+ sortnode[j].getValue());

				sortnode[j].setMatchScore(0); // wait for new search
				scorednode.remove(sortnode[j].getName()); // empty it.
			}
		}
	}

	public static void setTermDocScore(Iterator<String> it) {
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

	public static void main(String[] args) throws Exception {
		System.out.println("Please input the URL you want to retrieve:");
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		String url = "http://";
		String in = bf.readLine();
		url = url + in;

		Irobot i = new Irobot(url);
		StringMap sm[] = i.getUrls();

		UrlPageRank ur = new UrlPageRank(sm, i.getUrlsize());
		ur.print(10000, 0.85);

		IndexBuilder ib = getIndexBuilder(ur);

		System.out.println("Retrieving is over. Please enjoy your search..");
		in = bf.readLine();
		while (!in.equalsIgnoreCase("quit")) {
			if (in.equalsIgnoreCase("") || in == null) {
			} else {
				String query[] = in.split(" ");

				if (query[0].equalsIgnoreCase("backlink")) {
					if(query.length>2) System.out.println("backlink [URL]. No more than 1 variable.");
					else{
							
					}
				} else {
					// for each document who has the term, they get one mark.
					for (int j = 0; j < query.length; j++) {
						Iterator<String> it = ib.lookUpDocForTerm(query[j]);
						setTermDocScore(it);
					}

					printResult(1);
				}
			}

			in = bf.readLine();
		}

		System.out.println("Thank you for using!!");
	}
}
