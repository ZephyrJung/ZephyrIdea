package org.b3log.zephyr.action;

import com.intellij.codeInsight.highlighting.HighlightManager;
import com.intellij.codeInsight.highlighting.HighlightManagerImpl;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.editor.Caret;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.actionSystem.EditorActionHandler;
import com.intellij.openapi.editor.actions.TextComponentEditorAction;
import com.intellij.openapi.editor.markup.RangeHighlighter;
import com.intellij.openapi.editor.markup.TextAttributes;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.util.TextRange;
import com.intellij.spellchecker.dictionary.ProjectDictionary;
import com.intellij.spellchecker.state.ProjectDictionaryState;
import org.apache.commons.lang.StringUtils;
import org.b3log.zephyr.highlight.TextAttributesFactory;
import org.b3log.zephyr.model.Range;
import org.b3log.zephyr.model.Word;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

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
                final HighlightManager highlightManager = HighlightManager.getInstance(editor.getProject());
                final TextAttributes ta = TextAttributesFactory.getInstance().get();
                final Document doc = editor.getDocument();
                for (int line = 0; line < doc.getLineCount(); line++) {
                    String text = doc.getText(new TextRange(doc.getLineStartOffset(line), doc.getLineEndOffset(line)));
                    List<Range> ranges = getRangeList(text,doc.getLineStartOffset(line));
                    if (StringUtils.isNotBlank(text)) {
                        for(Range range : ranges){
                            highlightManager.addRangeHighlight(editor, range.getStart(), range.getEnd(), ta, true, null);
                        }
                    }
                }
            } catch (Exception e) {
                Messages.showErrorDialog("发生了未知错误", "错误");
            }
        }

        private List<Range> getRangeList(final String text,final int lineStart) {
            String backup = text;
            String backup2 = text;
            List<Range> rangeList = new ArrayList<>();
            //字符串拆分，非字母，大小写
            String upperCase = text.replaceAll("[^A-Z]", "");
            //在每个大写字母之前添加一个空格
            for (char upper : upperCase.toCharArray()) {
                backup = backup.replaceFirst(String.valueOf(upper), " " + upper);
            }
            //根据非字母正则匹配分割字符串
            for (String temp : backup.split("[^a-zA-Z]")) {
                if (StringUtils.isNotBlank(temp) && backup2.indexOf(temp)!=backup2.lastIndexOf(temp)) {
                    while(backup2.contains(temp)){
                        Range range = new Range();
                        range.setStart(text.indexOf(temp)+lineStart);
                        range.setEnd(text.indexOf(temp)+temp.length()+lineStart);
                        backup2 = backup2.replaceFirst(temp,"~");
                        rangeList.add(range);
                    }
                }
            }
            return rangeList;
        }
    }
}