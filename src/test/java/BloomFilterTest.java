import org.junit.Before;
import org.junit.Test;
import org.lzh.deduplicate.bloomFilter.BloomFilter;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 布隆过滤器测试
 *
 * @author BFD_499
 * @create 2016-11-01 16:00
 */
public class BloomFilterTest {
    private List<String> list;
    @Before
    public void init(){
        list = new ArrayList<String>();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(new File("src/main/resources/finger.dic")),"utf-8"));
            String tmp;
            while ((tmp = reader.readLine()) != null){
                list.add(tmp);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    @Test
    public void test(){
        BloomFilter bloomFilter = null;
        try {
            bloomFilter = new BloomFilter(50);
            for(String tmp : list){
                bloomFilter.add(tmp.split("\t")[0]);
            }
            System.out.println(bloomFilter.contains("同治"));
            System.out.println(bloomFilter.contains("国美金控"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
