package algorithmLean.giantAlgorithmProblem;


import java.util.*;

/**
 * @Description: 图和及其相关习题
 * @Author: haole
 * @Date: 2022/2/17 21:31
 */
public class Code20220217_01 {
    /**
     * 图：是一个顶点集合加上一个连接不同顶点对的边的集合组成。定义规定不允许出现重复边（平行边）、连接到顶点自身的边（自环），定义了一个简单图。
     * 自环：连接到顶点自身的边。平行边：连接同一对顶点的两条边，含有平行边的图称为多重图。
     * 顶点的度数：依附于它的边的总数。
     * 顶点连通：两个顶点之间存在一条连接双方的路径。
     * 连通图：任意一个顶点都存在一条路径到达另一个任意顶点。
     * 极大连通子图：一个非连通的图由若干个连通图组成，这些连通的图都是极大连通子图。
     * 图的密度：已经连接的顶点对占所有可能被连接顶点对的比例，稀疏图被连接的顶点对较少，稠密图没有被连接的顶点对很少。
     * <p>
     * 表示图的数据结构 1、邻接矩阵 2、邻接表
     * 图的两种搜索方式 1、深度优先搜索（depth-first-search DFS）2、广度优先搜索（breadth-first-search BFS）
     */
    /**
     * 点结构
     */
    public static class Node {
        private int value;
        private int in;
        private int out;
        private ArrayList<Node> nexts;
        private ArrayList<Edge> edges;

        public Node(int value) {
            this.value = value;
            in = 0;
            out = 0;
            nexts = new ArrayList<>();
            edges = new ArrayList<>();
        }
    }

    /**
     * 边结构
     */
    public static class Edge {
        private int weight;
        private Node from;
        private Node to;

        public Edge(int weight, Node from, Node to) {
            this.weight = weight;
            this.from = from;
            this.to = to;
        }
    }

    /**
     * 图结构
     */
    public static class Graph {
        private HashMap<Integer, Node> nodes;
        private HashSet<Edge> edges;

        public Graph() {
            nodes = new HashMap<>();
            edges = new HashSet<>();
        }
    }

    public static Graph createGraph(Integer[][] matrix) {
        Graph graph = new Graph();
        for (int i = 0; i < matrix.length; i++) {
            //拿到每一条边，matrix[i];
            //每条边的权重
            Integer weight = matrix[i][0];
            //边从哪个点来
            Integer from = matrix[i][1];
            //到哪个点去
            Integer to = matrix[i][2];
            //如果来的点没建立，new一个点
            if (!graph.nodes.containsKey(from)) {
                graph.nodes.put(from, new Node(from));
            }
            //如果去的点没建立，new一个点
            if (!graph.nodes.containsKey(to)) {
                graph.nodes.put(to, new Node(to));
            }
            //拿出from点来
            Node fromNode = graph.nodes.get(from);
            //拿出to点来
            Node toNode = graph.nodes.get(to);
            //建好这条边
            Edge edge = new Edge(weight, fromNode, toNode);
            //from点的直接邻居包含to
            fromNode.nexts.add(toNode);
            //from出度++
            fromNode.out++;
            //to入度++
            toNode.in++;
            //from边集add
            fromNode.edges.add(edge);
            //图的边集add
            graph.edges.add(edge);
        }
        return graph;
    }

    /**
     * 图的宽度优先遍历
     */
    public static void bfs(Node node) {
        if (node == null) {
            return;
        }
        Queue<Node> queue = new LinkedList<>();
        HashSet<Node> set = new HashSet<>();
        queue.add(node);
        set.add(node);
        while (queue != null) {
            Node cur = queue.poll();
            System.out.println(cur.value);
            for (Node node1 : cur.nexts) {
                if (!set.contains(node1)) {
                    queue.add(node1);
                    set.add(node1);
                }

            }
        }
    }

    /**
     * 图的深度优先遍历
     */
    public static void dfs(Node node) {
        if (node == null) {
            return;
        }
        Stack<Node> stack = new Stack<>();
        HashSet<Node> set = new HashSet<>();
        stack.push(node);
        set.add(node);
        System.out.println(node.value);
        while (!stack.isEmpty()) {
            Node cur = stack.pop();
            for (Node next : cur.nexts) {
                if (!set.contains(next)) {
                    stack.push(cur);
                    stack.push(next);
                    set.add(next);
                    System.out.println(next.value);
                    break;
                }
            }
        }
    }

    /**
     * 图的拓扑排序算法
     * 说到拓扑排序，一定是有向无环图
     * 一个结点后的所有路线走过后经过点的集合如果大，那他的拓扑序就小，例：x nodes = 100, y nodes = 80, x<y;
     */
    public static List<Node> sortedTopology(Graph graph) {
        //key 某个节点，value 剩余的入度
        HashMap<Node, Integer> inMap = new HashMap<>();
        //哪个节点入度为0，扔到这个队列里
        Queue<Node> zeroInqueue = new LinkedList<>();
        for (Node node : graph.nodes.values()) {
            inMap.put(node, node.in);
            if (node.in == 0) {
                zeroInqueue.add(node);
            }
        }

        List<Node> res = new ArrayList<>();

        while (!zeroInqueue.isEmpty()) {
            Node cur = zeroInqueue.poll();
            res.add(cur);
            for (Node next : cur.nexts) {
                inMap.put(next, inMap.get(next) - 1);
                if (inMap.get(next) == 0) {
                    zeroInqueue.add(next);
                }
            }
        }
        return res;
    }

    /**
     * 图的拓扑排序练习
     * OJ链接：https://www.lintcode.com/problem/topological-sorting
     */
    // 不要提交这个类
    public static class DirectedGraphNode {
        public int label;
        public ArrayList<DirectedGraphNode> neighbors;

        public DirectedGraphNode(int x) {
            label = x;
            neighbors = new ArrayList<DirectedGraphNode>();
        }
    }

    // 提交下面的
    public static class Record {
        public DirectedGraphNode node;
        public int deep;

        public Record(DirectedGraphNode n, int o) {
            node = n;
            deep = o;
        }
    }

    public static ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph) {
        HashMap<DirectedGraphNode, Record> order = new HashMap<>();
        for (DirectedGraphNode cur : graph) {
            f(cur, order);
        }
        ArrayList<Record> records = new ArrayList<>();
        for (Record r : order.values()) {
            records.add(r);
        }
        records.sort(new Comparator<Record>() {
            @Override
            public int compare(Record o1, Record o2) {
                return o1.deep == o2.deep ? 0 : (o1.deep > o2.deep ? -1 : 1);
            }
        });
        ArrayList<DirectedGraphNode> ans = new ArrayList<>();
        for (Record r : records) {
            ans.add(r.node);
        }
        return ans;
    }


    public static Record f(DirectedGraphNode cur, HashMap<DirectedGraphNode, Record> order) {
        if (order.containsKey(cur)) {
            return order.get(cur);
        }
        //cur之前的点次没有算过
        int nodes = 0;
        for (DirectedGraphNode next : cur.neighbors) {
            nodes += f(next, order).deep;
        }
        Record ans = new Record(cur, nodes + 1);
        order.put(cur, ans);
        return ans;
    }


    /**
     * prim算法
     * 一个连通图的生成树是一个极小的连通子图，它包含图中全部的顶点（n个顶点），但只有n-1条边。
     * 最小生成树：构造连通网的最小代价（最小权值）生成树。
     */
    public static Set<Edge> primMst(Graph graph) {
        //解锁的边进入小根堆
        PriorityQueue<Edge> priorityQueue = new PriorityQueue<>();
        //解锁的点进入set
        HashSet<Node> set = new HashSet<>();
        //依次挑选的边放到result里
        Set<Edge> result = new HashSet<>();
        for (Node node : graph.nodes.values()) {
            if (!set.contains(node)) {
                set.add(node);
                for (Edge e : node.edges) {
                    priorityQueue.add(e);
                }
                while (!priorityQueue.isEmpty()) {
                    //弹出解锁边中最小的边
                    Edge edge = priorityQueue.poll();
                    //可能是一个新点
                    Node toNode = edge.to;
                    //如果是新点
                    if (!set.contains(toNode)) {
                        //放入set
                        set.add(toNode);
                        //将这条边放入结果中去
                        result.add(edge);
                        //将这个点解锁的所有边放入
                        for (Edge nextEdge : toNode.edges) {
                            priorityQueue.add(nextEdge);
                        }
                    }
                }
            }
        }
        return result;
    }

    /**
     * Kruskal最小生成树
     * 生成树
     * 已知连通图G ，图上有N个顶点。生成树是指图G的一个极小（边最少）连通子图，生成树上有n个顶点、n-1条边，且任意两点之间都是连通的。
     */

    /**
     * “Dijkstra算法是一种最短路径路由算法，用于计算一个节点到其他所有节点的最短路径。
     * 主要特点是以起始点为中心向外层层扩展，直到扩展到终点为止。Dijkstra算法能得出最短路径的最优解，但由于它遍历计算的节点很多”
     */
    public static HashMap<Node, Integer> dijkstra1(Node from) {
        HashMap<Node, Integer> distanceMap = new HashMap<>();
        distanceMap.put(from, 0);
        HashSet<Node> selectNodes = new HashSet<>();
        Node minNode = getMinDistanceAndUnselectedNode(distanceMap, selectNodes);
        while (minNode != null) {
            //  原始点  ->  minNode(跳转点)   最小距离distance
            int distance = distanceMap.get(minNode);
            for (Edge edge : minNode.edges) {
                Node toNode = edge.to;
                if (!distanceMap.containsKey(toNode)) {
                    distanceMap.put(toNode, distance + edge.weight);
                } else {
                    distanceMap.put(edge.to, Math.min(distanceMap.get(toNode), distance + edge.weight));
                }
            }
            selectNodes.add(minNode);
            minNode = getMinDistanceAndUnselectedNode(distanceMap, selectNodes);
        }
        return distanceMap;
    }

    private static Node getMinDistanceAndUnselectedNode(HashMap<Node, Integer> distanceMap, HashSet<Node> selectNodes) {
        Node minNode = null;
        int minDistance = Integer.MAX_VALUE;
        for (Map.Entry<Node, Integer> entry : distanceMap.entrySet()) {
            Node node = entry.getKey();
            int distance = entry.getValue();
            if (!selectNodes.contains(node) && distance < minDistance) {
                minNode = node;
                minDistance = distance;
            }
        }
        return minNode;
    }

    /**
     * Dijkstra算法
     * 利用加强堆实现
     */
    public static class NodeRecord {
        public Node node;
        public int distance;

        public NodeRecord(Node node, int distance) {
            this.node = node;
            this.distance = distance;
        }
    }

    public static class NodeHeap {
        private Node[] nodes; // 实际的堆结构
        // key 某一个node， value 上面堆中的位置
        private HashMap<Node, Integer> heapIndexMap;
        // key 某一个节点， value 从源节点出发到该节点的目前最小距离
        private HashMap<Node, Integer> distanceMap;
        private int size; // 堆上有多少个点

        public NodeHeap(int size) {
            nodes = new Node[size];
            heapIndexMap = new HashMap<>();
            distanceMap = new HashMap<>();
            size = 0;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        // 有一个点叫node，现在发现了一个从源节点出发到达node的距离为distance
        // 判断要不要更新，如果需要的话，就更新
        public void addOrUpdateOrIgnore(Node node, int distance) {
            if (inHeap(node)) {
                distanceMap.put(node, Math.min(distanceMap.get(node), distance));
                insertHeapify(node, heapIndexMap.get(node));
            }
            if (!isEntered(node)) {
                nodes[size] = node;
                heapIndexMap.put(node, size);
                distanceMap.put(node, distance);
                insertHeapify(node, size++);
            }
        }

        public NodeRecord pop() {
            NodeRecord nodeRecord = new NodeRecord(nodes[0], distanceMap.get(nodes[0]));
            swap(0, size - 1);
            heapIndexMap.put(nodes[size - 1], -1);
            distanceMap.remove(nodes[size - 1]);
            // free C++同学还要把原本堆顶节点析构，对java同学不必
            nodes[size - 1] = null;
            heapify(0, --size);
            return nodeRecord;
        }

        private void insertHeapify(Node node, int index) {
            while (distanceMap.get(nodes[index]) < distanceMap.get(nodes[(index - 1) / 2])) {
                swap(index, (index - 1) / 2);
                index = (index - 1) / 2;
            }
        }

        private void heapify(int index, int size) {
            int left = index * 2 + 1;
            while (left < size) {
                int smallest = left + 1 < size && distanceMap.get(nodes[left + 1]) < distanceMap.get(nodes[left])
                        ? left + 1
                        : left;
                smallest = distanceMap.get(nodes[smallest]) < distanceMap.get(nodes[index]) ? smallest : index;
                if (smallest == index) {
                    break;
                }
                swap(smallest, index);
                index = smallest;
                left = index * 2 + 1;
            }
        }

        private boolean isEntered(Node node) {
            return heapIndexMap.containsKey(node);
        }

        private boolean inHeap(Node node) {
            return isEntered(node) && heapIndexMap.get(node) != -1;
        }

        private void swap(int index1, int index2) {
            heapIndexMap.put(nodes[index1], index2);
            heapIndexMap.put(nodes[index2], index1);
            Node tmp = nodes[index1];
            nodes[index1] = nodes[index2];
            nodes[index2] = tmp;
        }
    }

    public static HashMap<Node, Integer> dijkstra2(Node head, int size) {
        NodeHeap nodeHeap = new NodeHeap(size);
        nodeHeap.addOrUpdateOrIgnore(head, 0);
        HashMap<Node, Integer> result = new HashMap<>();
        while (!nodeHeap.isEmpty()) {
            NodeRecord record = nodeHeap.pop();
            Node cur = record.node;
            int distance = record.distance;
            for (Edge edge : cur.edges) {
                nodeHeap.addOrUpdateOrIgnore(edge.to, edge.weight + distance);
            }
            result.put(cur, distance);
        }
        return result;
    }
}
