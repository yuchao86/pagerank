/**
 * 香港理工大学-中国科学院研究生院数据挖掘-PageRank算法实现
 * @author chaoyu
 * @right Copyright by PolyU team
 * @github https://github.com/yuchao86/pagerank.git
 * @date 2012-10-13
 */
package polyu.gucas.rawPagerank.pagerank;
//import java.io.*;

public class PageRank {
	/*
	static Node prn[];// all the nodes
    static int d,zdy,cy;
    
    
    public static void calc(Node ns[],double d){
        double value = 0;
        double values[] = new double[100]; 
        
        for(int i=0;i<zdy;i++){
            value = 0;
            Node ns1[] = new Node[100];
            ns1 = ns[i].getNode();
            int j = ns[i].getNodeN();
            for(int k=0;k<j;k++){ 
                value = ns1[k].getValue()/ns1[k].getOutnode() + value;
            }
            
            value = value * d + 1 - d;
            values[i] = value;            
        }
        
        for(int i=0;i<zdy;i++){
            ns[i].setValue(values[i]);
        }
    }
    */
    
    /*
     * This is the main calculate algorithm to calculate the PageRank
     * */
    public static void calc(Node ns[],double d, int times){
        double value = 0;
        double values[] = new double[1000]; 
        
        for(int i=0;i<times;i++){
            value = 0;
            Node ns1[] = new Node[100];
            ns1 = ns[i].getNodein();
            int j = ns[i].getNodeN();
            for(int k=0;k<j;k++){ 
                value = ns1[k].getValue()/ns1[k].getOutnode() + value;
            }
            
            value = value * d + 1 - d;
            values[i] = value;            
        }
        
        for(int i=0;i<times;i++){
            ns[i].setValue(values[i]);
        }
    }
    
  /*
    public static void main(String[] args) throws Exception{
        d = 0;
        zdy = 0;
        prn = new Node[100];

        System.out.println("Please input the nodes and link node which popint to it. ");

        BufferedReader buf;
        String str;
        buf=new BufferedReader(new InputStreamReader(System.in));
        str=buf.readLine();

        String sttr[] = new String[100];
        sttr = str.split(" ");

        zdy = sttr.length;
        for(int i=0;i<sttr.length;i++){
            prn[i] = new Node(sttr[i],0);  
        }
        
        System.out.println("please input the data ");
        
        for(int i=0;i< sttr.length;i++){
            str = buf.readLine(); 
            String sss[] = str.split(" ");
            int abc[] = new int[100];
            
            for(int j=0;j<sss.length;j++){
                abc[j] = Integer.parseInt(sss[j]);
            }
            
            for(int j=1;j<sss.length-1;j++){
                prn[abc[0]].addNode(prn[abc[j]]);
            }
            
            prn[abc[0]].setOutnode(abc[sss.length-1]);
        }
        
        System.out.println("Please input how many times you want to calculate ");
        String a = buf.readLine();
        int time = Integer.parseInt(a);
        for(int i = time;i>0;i--){
            calc(prn,1);
        }
        
        for(int i=0;i<zdy;i++){
            System.out.println(prn[i].getValue());
        }

    }*/
}

