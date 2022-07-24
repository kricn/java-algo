package Other;

import java.util.HashMap;
import java.util.List;
import java.util.Stack;

/**
 * 并查集
 */
public class UnionFind {

    // 样本进来会被包裹一层元素
    public static class Element<V> {
        public V value;
        public Element(V value) {
            this.value = value;
        }
    }

    /**
     *
     * @param <V>
     */
    public static class UnionFindSet<V> {
        // 样本对对应的元素
        public HashMap<V, Element<V>> elementHashMap;
        // 某个元素的父级
        public HashMap<Element<V>, Element<V>> fatherHashMap;
        // 元素集合的大小
        public HashMap<Element<V>, Integer> sizeMap;

        public UnionFindSet(List<V> list) {
            elementHashMap = new HashMap();
            fatherHashMap = new HashMap();
            sizeMap = new HashMap();

            for (V value : list) {
                // 为每个样本包裹成 element
                Element<V> element = new Element<V>(value);
                elementHashMap.put(value, element);
                // 自己指向自己
                fatherHashMap.put(element, element);
                sizeMap.put(element, 1);
            }
        }

        /**
         * 寻找头节点
         */
        public Element<V> findHead(Element<V> element) {
            Stack<Element<V>> path = new Stack<>();
            // 判断 element 是否到顶部了
            // 自己 == 自己的时候就到顶部
            while (element != fatherHashMap.get(element)) {
                // 储存沿途的节点
                path.push(element);
                // 更新 element 让它往上走
                element = fatherHashMap.get(element);
            }
            // 将沿途的节点扁平化，下次再查找该节点的时候就可以直接找到其父节点
            while (!path.isEmpty()) {
                fatherHashMap.put(path.pop(), element);
            }
            // 返回最顶部的节点
            return element;
        }

        public boolean isSameSet(V a, V b) {
            if (elementHashMap.containsKey(a) && elementHashMap.containsKey(b)) {
                return findHead(elementHashMap.get(a)) == findHead(elementHashMap.get(b));
            }
            return false;
        }

        /**
         * 合并函数
         */
        public void union(V a, V b) {
            if (elementHashMap.containsKey(a) && elementHashMap.containsKey(b)) {
                Element<V> aF = findHead(elementHashMap.get(a));
                Element<V> bF = findHead(elementHashMap.get(b));
                // 如果两个是同一个集合，那就什么也不做
                if (aF != bF) {
                    // 区分两个节点数量的大小
                    Element<V> big = sizeMap.get(aF) > sizeMap.get(bF) ? aF : bF;
                    Element<V> small = big == aF ? bF : aF;
                    // 直接将小的元素指向大的元素
                    fatherHashMap.put(small, big);
                    // 更新大的元素的大小
                    sizeMap.put(big, sizeMap.get(aF) + sizeMap.get(bF));
                    // 移除小元素
                    sizeMap.remove(small);
                }
            }
        }
    }

}
