package TrieTree;

/**
 * 前缀树结构
 */
public class Trie {

    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    public void insert(String word) {
        if (word == null) {
            return ;
        }
        char[] chs = word.toCharArray();
        TrieNode node = root;
        node.pass ++;
        int index = 0;
        for (int i = 0; i < chs.length; i ++) { // 从左到右遍历字符
            index = chs[i] = 'a';  // 由字符寻找需要走哪条路
            if (node.nexts[index] == null) {  // 没有该字符，则创建出一条路
                node.nexts[index] = new TrieNode();
            }
            node = node.nexts[index];
            // 经过该点的次数加一
            node.pass ++;
        }
        // 结束后最后一个点的 end 次数加一
        node.end ++;
    }

    // 查询 word 单词出现过几次
    public int search(String word) {
        if (word == null) {
            return 0;
        }
        char[] chs = word.toCharArray();
        TrieNode node = root;
        int index = 0;
        for (int i = 0; i < chs.length; i ++) {
            index = chs[i] - 'a';
            if (node.nexts[index] == null) {
                return 0;
            }
            node = node.nexts[index];
        }
        return node.end;
    }

    // 所有字符串中，有多少个是以 pre 这个字符串为前缀的
    public int prefixNumber(String pre) {
        if (pre == null) {
            return 0;
        }
        char[] chs = pre.toCharArray();
        TrieNode node = root;
        int index = 0;
        for (int i = 0; i < chs.length; i ++) {
            index = chs[i] - 'a';
            if (node.nexts[index] == null) {
                return 0;
            }
            node = node.nexts[index];
        }
        return node.pass;
    }

    public void delete(String word) {
        if (search(word) != 0) {  // 有加入过的 word 才去删除
            char[] chs = word.toCharArray();
            TrieNode node = root;
            // 头节点 pass --
            node.pass --;
            int index = 0;
            for (int i = 0; i < chs.length; i ++) {
                index = chs[i] - 'a';
                // 该节点 pass 为为 0 时，删掉该节点后面的全部节点
                if (--node.nexts[index].pass == 0) {
                    node.nexts[index] = null;
                    return ;
                }
                node = node.nexts[index];
            }
            node.end --;
        }
    }

}
