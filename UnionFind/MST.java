import java.util.*;
public class MST {
    //给定的类
    public static class Connection {
        String city1;
        String city2;
        int cost;
        public Connection(String a, String b, int c) {
            city1 = a;
            city2 = b;
            cost = c;
        }
    }
 
    /**
     * 为了避免使用全局变量，声明一个UnionFind类
     */
    public static class UnionFind {
        Map<String, Integer> map; //map中装的是城市->城市所在的集合代号
        int setNum; //城市集合代号
        public UnionFind() {
            map = new HashMap<>();
            setNum = 0;
        }
        /**
         * 对每一个connection做union操作
         * 如果没有union操作，返回FALSE，如果有union操作，返回TRUE
         * 这里跟算法描述中不太一样：这里合并过的城市才会被分配集合代号
         */
        public boolean union(Connection conn) {
            // 两个城市都没有城市代号（都存在于单独的集合，都没有被合并过）
            if (!map.containsKey(conn.city1) && !map.containsKey(conn.city2)) {
                map.put(conn.city1, setNum);
                map.put(conn.city2, setNum);
                setNum++;
                return true;
            }
            // 有一个城市有代号（说明其中有一个是之前合并过的）
            if (map.containsKey(conn.city1) && !map.containsKey(conn.city2)) {
                map.put(conn.city2, map.get(conn.city1));
                return true;
            }
            if (!map.containsKey(conn.city1) && map.containsKey(conn.city2)) {
                map.put(conn.city1, map.get(conn.city2));
                return true;
            }
            // 两个都有代号（那么合并它们分别所在的集合中的所有城市）
            if (map.containsKey(conn.city1) && map.containsKey(conn.city2)) {
                int num1 = map.get(conn.city1);
                int num2 = map.get(conn.city2);
                if (num1 == num2) { //避免出现环
                    return false;
                }
                for (String city : map.keySet()) { //把city1在集合中的所有城市代号改为city2的代号
                    if (map.get(city) == num1) {
                        map.put(city, num2);
                    }
                }
                return true;
            }
            return false;
        }
    }
    /**
     * Time: O(ElogE + E) 后面的 "+E"是在union函数中，当两个城市都有代号的时候
     * Space: O(E)
     */
    public static List<Connection> findMinimum(List<Connection> list) {
        List<Connection> result = new ArrayList<>(); //最终结果，输出必须排序
        if (list == null || list.size() == 0) {
            return result;
        }
        UnionFind uf = new UnionFind();
        // 首先把边以权重来排序
        Collections.sort(list, new Comparator<Connection>() {
            @Override
            public int compare(Connection conn1, Connection conn2) {
                return conn1.cost - conn2.cost;
            }
        });
        // 遍历每一条边，进行处理
        for (Connection conn : list) {
            if (uf.union(conn)) {
                result.add(conn);
            }
        }
        // 最后把结果再排序一次
        Collections.sort(result, new Comparator<Connection>() {
            @Override
            public int compare(Connection conn1, Connection conn2) {
                if (conn1.city1.equals(conn2.city1)) {
                    return conn1.city2.compareTo(conn2.city2);
                }
                return conn1.city1.compareTo(conn2.city1);
            }
        });
        return result;
    }
}
