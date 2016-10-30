package org.lzh.deduplicate;

import org.nlpcn.commons.lang.dic.DicManager;
import org.nlpcn.commons.lang.tire.domain.Forest;
import org.nlpcn.commons.lang.tire.library.Library;

/**
 * 默认使用forest
 *
 * @author BFD_499
 * @create 2016-10-30 17:33
 */
class AbsServer {
    protected static Forest forest = null;
    static {
        try {
            forest = Library.makeForest(DicManager.class.getResourceAsStream("/finger.dic"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
