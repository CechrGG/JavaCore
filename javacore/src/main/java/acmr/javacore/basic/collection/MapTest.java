package acmr.javacore.basic.collection;

import java.util.HashMap;
import java.util.Map;

public class MapTest {
    public static void main(String[] args) {
        Map<Integer, String> map = new HashMap<>();
        map.put(null, null);
        map.put(null, "null");
        map.put(-1, null);
        for(int i = 0; i < 10; i++) {
            map.put(i, i + "v");
        }
        System.out.println(map.size());
        map.compute(10, (k, v) -> k + v);
        map.computeIfAbsent(10, Object::toString);
        map.computeIfAbsent(20, (k) -> k + "v");
        map.computeIfPresent(null, (k, v) -> k + v);
        map.merge(5, "wu", (ov, nv) -> ov + nv);
        map.replaceAll((k, v) -> k + v);
        map.forEach((k,v) -> System.out.println(k + "--" + v));
    }
}
