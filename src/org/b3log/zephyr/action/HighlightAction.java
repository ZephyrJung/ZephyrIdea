package org.b3log.zephyr.action;

import com.intellij.codeInsight.highlighting.HighlightManager;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.editor.Caret;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.actionSystem.EditorActionHandler;
import com.intellij.openapi.editor.actions.TextComponentEditorAction;
import com.intellij.openapi.editor.markup.EffectType;
import com.intellij.openapi.editor.markup.TextAttributes;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.util.TextRange;
import org.apache.commons.lang.StringUtils;
import org.b3log.zephyr.model.Range;
import org.b3log.zephyr.model.WordRanges;
import org.jetbrains.annotations.Nullable;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 行级代码着色
 * 相同的单词会打上相同的标记
 *
 * @author <a href="mailto:zephyrjung@126.com">Zephyr Cheung</a>
 * @version $Id$
 */
public class HighlightAction extends TextComponentEditorAction {

    protected HighlightAction() {
        super(new Handler());
    }

    private static class Handler extends EditorActionHandler {

        @Override
        public void doExecute(Editor editor, @Nullable Caret caret, DataContext dataContext) {
            try {
                final Document doc = editor.getDocument();
                for (int line = 0; line < doc.getLineCount(); line++) {
                    String text = doc.getText(new TextRange(doc.getLineStartOffset(line), doc.getLineEndOffset(line)));
                    List<WordRanges> wordRangesList = getWordRangesList(text, doc.getLineStartOffset(line));
                    if (StringUtils.isNotBlank(text)) {
                        for (WordRanges wordRanges : wordRangesList) {
                            final HighlightManager highlightManager = HighlightManager.getInstance(editor.getProject());
                            final TextAttributes ta = new TextAttributes();
                            ta.setForegroundColor(Color.RED);
                            ta.setBackgroundColor(Color.YELLOW);
                            for (Range range : wordRanges.getRangeList()) {
                                highlightManager.addRangeHighlight(editor, range.getStart(), range.getEnd(),
                                        ta, true, null);
                            }
                        }
                    }
                }
            } catch (Exception e) {
                Messages.showErrorDialog("发生了未知错误", "错误");
            }
        }

        private List<WordRanges> getWordRangesList(final String text, final int lineStart) {
            String backup = text;
            String backup2 = text.toLowerCase();
            List<WordRanges> wordRangesList = new ArrayList<>();
            //字符串拆分，非字母，大小写
            String upperCase = text.replaceAll("[^A-Z]", "");
            //在每个大写字母之前添加一个空格
            for (char upper : upperCase.toCharArray()) {
                backup = backup.replaceFirst(String.valueOf(upper), "0" + upper);
            }
            backup = backup.replaceAll("[^a-zA-Z0]", "1");

            //根据非字母正则匹配分割字符串
            String[] words = backup.split("[1|0]");
            for (String temp : words) {
                temp = temp.toLowerCase();
                WordRanges wordRanges = new WordRanges();
                if (StringUtils.isNotBlank(temp) && countWords(temp, words) > 1) {
                    List<Range> rangeList = new ArrayList<>();
                    while (backup2.contains(temp)) {
                        Range range = new Range();
                        range.setStart(backup2.indexOf(temp) + lineStart);
                        range.setEnd(backup2.indexOf(temp) + temp.length() + lineStart);
                        backup2 = backup2.replaceFirst(temp, generateMask(temp));
                        rangeList.add(range);
                    }
                    wordRanges.setWord(temp);
                    wordRanges.setRangeList(rangeList);
                    wordRangesList.add(wordRanges);
                }
            }
            return wordRangesList;
        }

        private int countWords(String word, String[] words) {
            int count = 0;
            for (String temp : words) {
                if (temp.equalsIgnoreCase(word)) {
                    count++;
                }
            }
            return count;
        }

        private String generateMask(String text) {
            return text.replaceAll("[a-zA-Z]", "*");
        }
    }
}