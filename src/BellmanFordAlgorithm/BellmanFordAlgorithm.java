package BellmanFordAlgorithm;

import java.io.*;
import java.util.*;

public class BellmanFordAlgorithm {

    static int vertexNum = 0;           // 정점 개수
    static int edgeNum = 0;             // 간선 개수
    static int[] dist = null;           // 최단 거리 저장 배열
    static List<Integer> pointList;     // txt 읽어와 저장할 점의 리스트
    static List<Edge> Edge;             // 점의 정보(시작점, 도착점, 가중치) 저장될 리스트

    final int INFINITY = Integer.MAX_VALUE;                 // 무한대 값

    /** 초기화 */
    public void init() {
        pointList = new ArrayList<>();
        Edge = new ArrayList<>();
    }

    public void insert(File F) throws FileNotFoundException {
        /** File read & insert */
        FileReader fr = new FileReader(F);
        BufferedReader br = new BufferedReader(fr);

        String[] temp = null, temp_p = null;
        Edge = new ArrayList<>();
        dist = null;

        try {
            /** txt 파일의 첫 줄 : 정점 정보 */
            String firstLine = br.readLine();
            temp_p = firstLine.split(",");
            for(int i = 0; i < temp_p.length; i++)
                pointList.add(Integer.parseInt(temp_p[i]));

            String line = "";
            while ((line = br.readLine()) != null) {
                temp = line.split(",");
                Edge e = new Edge(Integer.parseInt(temp[0]), Integer.parseInt(temp[1]), Integer.parseInt(temp[2]));
                Edge.add(e);
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            System.err.println(e);
        }

        vertexNum = pointList.size();
        edgeNum = Edge.size();
        dist = new int[vertexNum];

        /** 출력문 */
        print(0);       // 0번째 정점부터 시작
    }

    private boolean BellmanFord(int s) {
        InitSingleSource(s);

        for(int i = 0; i < vertexNum; i++) {
            System.out.printf("\n-------------- iteration %d --------------\n", i);
            for(int j = 0; j < edgeNum; j++)
                Relax(Edge.get(j));
            System.out.printf("iteration %d distance : ", i);
            System.out.println(Arrays.toString(dist) + "");
        }

        for(int j = 0; j < edgeNum; j++)
            if(dist[Edge.get(j).src] + Edge.get(j).weight < dist[Edge.get(j).dest])
                return false;

        return true;
    }

    /** 초기화 */
    private void InitSingleSource(int s) {
        for(int i = 0; i < vertexNum; i++)
            dist[i] = INFINITY;  // 최단거리 배열 INFINITY값으로 초기화
        dist[s] = 0;   // 시작 정점 최단거리 index값 0으로 초기화
    }

    private void Relax(Edge e) {
        if(dist[e.src] != INFINITY && dist[e.src] + e.weight < dist[e.dest]) {
            if(dist[e.dest] == INFINITY)
                System.out.println("Update distance of " + e.dest + " from inf to " + (dist[e.src] + e.weight));
            else System.out.println("Update distance of " + e.dest + " from " + dist[e.dest] + " to " + (dist[e.src] + e.weight));
            dist[e.dest] = dist[e.src] + e.weight;
        }
    }

    private void print(int s) {
        if(BellmanFord(s))  // BellmanFord return값이 true인 경우
            System.out.println("Final distance " + Arrays.toString(dist));
        else                // BellmanFord return값이 false인 경우
            System.out.println("The graph has negative cycle");
    }
}
