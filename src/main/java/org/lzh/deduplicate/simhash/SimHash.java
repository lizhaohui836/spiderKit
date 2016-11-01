package org.lzh.deduplicate.simhash;

import org.lzh.util.MurmurHash;
import org.nlpcn.commons.lang.tire.GetWord;

import java.util.ArrayList;
import java.util.List;

/**
 * simhash算法，局部敏感hash。将长串文本降维生成hashcode，通过计算海明距离判断是否相似
 *
 * @author BFD_499
 * @create 2016-10-29 20:23
 */
public class SimHash extends AbsServer{

    private static final int BYTE_LEN = 64;
    private static final long[] BITS = new long[BYTE_LEN];

    static{
        BITS[0] = 1;
        for(int i = 1; i < BYTE_LEN; i++){
            BITS[i] = BITS[i - 1]*2;
        }
    }

    public int hammingDistance(String a, String b){
        return hammingDistance(finger(a), finger(b));
    }
    /**
     * 计算a b的汉明距离
     * @param a
     * @param b
     * @return
     */
    public int hammingDistance(long a, long b){
        int num = 0;
        //按位异或运算，值不同则为1，相同为0
        a = a ^ b;
        for(int i = 0; i < BYTE_LEN; i++){
            if((a & BITS[i]) != 0){
                //该位为1
                num++;
            }
        }
        return num;
    }
    /**
     * 获取word的hash，默认返回64位
     * @param word
     * @return
     */
    public long hash(String word){
        return MurmurHash.hash64(word);
    }

    /**
     * 加权，在hash的基础上给特征向量加权
     * @param content
     * @return
     */
    public long finger(String content){
        int[] values = new int[BYTE_LEN];
        //对内容进行分词
        String word = null;
        int weight = 0;
        for(Value wordValue : spilit(content)){
            //wordMap key:单词；value:权重
            word = wordValue.getWord();
            weight = wordValue.getWeight();
            long hash = hash(word);
            //加权合并
            for(int i = 0; i < BYTE_LEN; i++){
                if((hash & BITS[i]) != 0){
                    //该位为1
                    values[BYTE_LEN - 1 - i] = values[BYTE_LEN -1 -i] + weight;
                }
                else{
                    //该位为0
                    values[BYTE_LEN - 1 -i] = values[BYTE_LEN - 1 -i] - weight;
                }
            }
        }
        //降维，如果大于0置1；小于0置0
        long result = 0;
        for(int i = 0; i < BYTE_LEN; i++){
            if(values[i] > 0){
                //按位或运算，如果i位置大于0，result该位置为1
                result = result | BITS[BYTE_LEN - 1 -i];
            }
        }
        return result;
    }

    /**
     * nlp-lang分词，暂时未实现weight分词
     * @param content
     * @return
     */
    public List<Value> spilit(String content){
        List<Value> list = new ArrayList<Value>();
        GetWord getWord = forest.getWord(content);
        String tmp = null;
        while((tmp = getWord.getFrontWords()) != null){
            //该分词调用nlp-lang，不确定是否有按照weight进行分词
            //暂时默认weight为1
            Value value = new Value(tmp, 1);
//            System.out.println(getWord.getParams());
            list.add(value);
        }
        return list;
    }

    /**
     * 特征向量对象
     */
    class Value{
        private String word;
        private Integer weight;
        private Value(String word, Integer weigt) {
            this.word = word;
            this.weight = weigt;
        }
        public String getWord() {
            return word;
        }
        public Integer getWeight() {
            return weight;
        }
    }
    public static void main(String[] args){
        GetWord getWord = forest.getWord("卓尔防线继续伤筋动骨 队长梅方出场再补漏说起来卓尔队长梅方本赛季就是个“补漏”的命！在中卫与右边后卫间不停地轮换。如果不出意外，今天与广州恒大一战梅方又要换位置，这也是汉军队长连续三场比赛中的第三次换位。而从梅方的身上也可以看出，本赛季汉军防线如此“折腾”，丢球多也不奇怪了。梅方自2009赛季中乙出道便一直司职中后卫，还曾入选过布拉泽维奇国奥队，也是司职的中卫。上赛季，梅方与忻峰搭档双中卫帮助武汉卓尔队中超成功，但谁知进入本赛季后从第一场比赛开始梅方便不断因为种种“意外”而居无定所。联赛首战江苏舜天时，也是由于登贝莱受伤，朱挺位置前移，梅方临危受命客串右边后卫。第二轮主场与北京国安之战梅方仅仅打了一场中卫，又因为柯钊受罚停赛4轮而不得不再次到边路“补漏”。随着马丁诺维奇被弃用，梅方一度成为中卫首选，在与上海东亚队比赛中，邱添一停赛，梅方与忻峰再度携手，紧接着与申鑫队比赛中移至边路，本轮忻峰又停赛，梅方和邱添一成为中卫线上最后的选择。至于左右边后卫位置，卓尔队方面人选较多，罗毅、周恒、刘尚坤等人均可出战。记者马万勇原标题：卓尔防线继续伤筋动骨队长梅方出场再补漏稿源：中新网作者：");
        System.out.println(getWord.getParams());
        System.out.println(getWord.getFrontWords());
    }
}
