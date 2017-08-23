package org.b3log.zephyr.action;

import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.editor.Caret;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.actionSystem.EditorActionHandler;
import com.intellij.openapi.editor.actions.TextComponentEditorAction;
import com.intellij.openapi.ui.Messages;
import org.jetbrains.annotations.Nullable;

/**
 * Dumb action to perform sort
 *
 * @author <a href="mailto:zephyrjung@126.com">Zephyr Cheung</a>
 * @version $Id$
 */
public class CountLinesAction extends TextComponentEditorAction {

    protected CountLinesAction() {
        super(new Handler());
    }

    private static class Handler extends EditorActionHandler {

        @Override
        public void doExecute(Editor editor, @Nullable Caret caret, DataContext dataContext) {
            final Document doc = editor.getDocument();

            int startLine;
            int endLine;

            boolean hasSelection = editor.getSelectionModel().hasSelection();
            if (hasSelection) {
                startLine = doc.getLineNumber(editor.getSelectionModel().getSelectionStart());
                endLine = doc.getLineNumber(editor.getSelectionModel().getSelectionEnd());
                if (doc.getLineStartOffset(endLine) == editor.getSelectionModel().getSelectionEnd()) {
                    endLine--;
                }
            } else {
                startLine = 0;
                endLine = doc.getLineCount() - 1;
            }

            if (startLine <= endLine) {
                String message = String.format("%d lines are selected!", endLine - startLine + 1);
                Messages.showInfoMessage(message, "Count Result");
            }

        }
    }
}