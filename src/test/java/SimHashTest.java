import org.junit.Test;
import org.lzh.deduplicate.simhash.SimHash;

/**
 * test for simhash
 *
 * @author BFD_499
 * @create 2016-10-30 17:43
 */
public class SimHashTest {
    @Test
    public void test() throws Exception {
       SimHash simHash = new SimHash();
        String content = "";
        String content2 = "";
        System.out.println("汉明距离为:" + simHash.hammingDistance(content, content2));
    }
}
