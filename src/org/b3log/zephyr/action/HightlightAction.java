package org.b3log.zephyr.action;

import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.editor.Caret;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.actionSystem.EditorActionHandler;
import com.intellij.openapi.editor.actions.TextComponentEditorAction;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.util.TextRange;
import org.apache.commons.lang.StringUtils;
import org.jetbrains.annotations.Nullable;

/**
 * 行级代码着色
 * 相同的单词会打上相同的标记
 * @author <a href="mailto:zephyrjung@126.com">Zephyr Cheung</a>
 * @version $Id$
 */
public class HightlightAction extends TextComponentEditorAction {

    protected HightlightAction() {
        super(new Handler());
    }

    private static class Handler extends EditorActionHandler {

        @Override
        public void doExecute(Editor editor, @Nullable Caret caret, DataContext dataContext) {
            final Document doc = editor.getDocument();
            for(int line = 0;line<doc.getLineCount();line++){
                String text = doc.getText(new TextRange(doc.getLineStartOffset(line),doc.getLineEndOffset(line)));
                if(StringUtils.isNotBlank(text)){
                    //TODO color
                }
            }
        }
    }
}