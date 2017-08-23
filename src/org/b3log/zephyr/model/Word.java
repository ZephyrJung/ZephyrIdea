package org.b3log.zephyr.model;

/**
 * Author: Zhang Yu
 * Date: 17年8月23日
 * Email: yu.zhang@7fresh.com
 */

public class Word {
    /**
     * 单词
     */
    private String word;

    /**
     * 记录在文本中的起始位置
     */
    private int start;

    /**
     *  结束位置
     */
    private int end;

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }
}
