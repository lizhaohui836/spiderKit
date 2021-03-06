import org.junit.Test;
import org.lzh.trie.TrieTree;

/**
 * 字典树test
 *
 * @author BFD_499
 * @create 2016-11-07 15:24
 */
public class TrieTreeTest {

    @Test
    public void addTest(){
        TrieTree<String> treeTest = new TrieTree<String>();
        treeTest.add("天气真好", "");
        treeTest.add("天气不错", "");
        treeTest.add("一万", "");
        treeTest.add("一万个", "");
        treeTest.add("两万个", "");
        treeTest.add("你好", "");
        treeTest.add("你说", "");
//        System.out.println(treeTest.contains("天气真好"));
//        System.out.println(treeTest.contains("天气不错"));
//        System.out.println(treeTest.contains("你好"));
//        System.out.println(treeTest.contains("你说"));
        System.out.println(treeTest.contains("一万"));
        System.out.println(treeTest.contains("两万个"));
        treeTest.remove("一万个");
        System.out.println(treeTest.contains("一万个"));
        System.out.println(treeTest.contains("两万个"));
        treeTest.add("你说", "");
        System.out.println(treeTest.contains("你说"));
        treeTest.clear();
        System.out.println("");
    }
}
