package org.b3log.zephyr.model;

import java.util.List;

/**
 * Author: Zhang Yu
 * Date: 17年8月23日
 * Email: yu.zhang@7fresh.com
 */

public class WordRanges {
    /**
     * 单词
     */
    private String word;

    /**
     * 记录在文本中的起止位置
     */
    private List<Range> rangeList;

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public List<Range> getRangeList() {
        return rangeList;
    }

    public void setRangeList(List<Range> rangeList) {
        this.rangeList = rangeList;
    }
}
