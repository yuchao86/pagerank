/**
 * 香港理工大学-中国科学院研究生院数据挖掘-PageRank算法实现
 * @author chaoyu
 * @right Copyright by PolyU team
 * @github https://github.com/yuchao86/pagerank.git
 * @date 2012-10-13
 */
package polyu.gucas.rawPagerank.pagerank;

public class StringMap {
	private String source,destination;

	public StringMap(String res, String des){
		this.setDestination(des);
		this.setResource(res);
	}
	
	public String getResource() {
		return source;
	}

	public void setResource(String resource) {
		this.source = resource;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}
	
	

}
