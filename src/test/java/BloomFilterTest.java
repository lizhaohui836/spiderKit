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
            bloomFilter = new BloomFilter(5);
            for(String tmp : list){
                bloomFilter.add(tmp.split("\t")[0]);
                bloomFilter.add(tmp.split("\t")[0] + tmp.split("\t")[1]);
                bloomFilter.add(tmp.split("\t")[0] + tmp.split("\t")[1] + tmp.split("\t")[2]);
                bloomFilter.add(tmp);
            }
            int all = 0;
            int containNum = 0;
            for(String tmp : list){
                if(bloomFilter.contains(tmp.split("\t")[0])){
                    containNum++;
                }
                if(bloomFilter.contains(tmp.split("\t")[0] + tmp.split("\t")[1])){
                    containNum++;
                }
                if(bloomFilter.contains(tmp.split("\t")[0] + tmp.split("\t")[1]  + tmp.split("\t")[2])){
                    containNum++;
                }
                if(bloomFilter.contains(tmp)){
                    containNum++;
                }
                all++;
            }
            long start = System.currentTimeMillis();
            System.out.println(bloomFilter.contains("同治"));
            long end = System.currentTimeMillis();
            System.out.println("判断用时: " + (end - start) + " ms。");
            System.out.println(bloomFilter.contains("国美金控"));
            System.out.println("文件字符串数量: " + all*4 + "，布隆过滤器判断重复成功数量: " + containNum);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
