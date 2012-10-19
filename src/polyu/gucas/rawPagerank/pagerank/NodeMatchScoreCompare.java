/**
 * 香港理工大学-中国科学院研究生院数据挖掘-PageRank算法实现
 * @author chaoyu
 * @right Copyright by PolyU team
 * @github https://github.com/yuchao86/pagerank.git
 * @date 2012-10-13
 */
package polyu.gucas.rawPagerank.pagerank;

import java.util.Comparator;

public class NodeMatchScoreCompare implements Comparator<Object> {

	/* first compare their matched score with the search thing,
	   if it is the same, them compare the pagerank value. */
	public int compare(Object o1, Object o2) {
		Node n1 = (Node)o1;
		Node n2 = (Node)o2;
		
		if(n1.getMatchScore() >
			n2.getMatchScore()){
			return -1;
		}else if(n1.getMatchScore() ==
			n2.getMatchScore()){
			
			return n1.getValue() >= n2.getValue()?-1:1; 
			/*if(n1.getValue() >= n2.getValue()){
				return -1;
			}else{
				return 1;
			}*/
		}
		
		return 1;
	}

}
