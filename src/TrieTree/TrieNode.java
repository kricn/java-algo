package TrieTree;

/**
 * 前缀树节点
 */
public class TrieNode {

    int pass;  // 有次经过了该节点
    int end;  // 有多少次以该节点为结束
    public TrieNode[] nexts;  // 字符串多种多样的时候可以用 HashMap<Char, TrieNode> nexts 表示

    public TrieNode() {
        pass = 0;
        end = 0;
        // nexts[0] == null 没有走向 "a" 的路
        // ...
        // nexts[25] != null 有走向 "z" 的图
        nexts = new TrieNode[26];
    }

}
