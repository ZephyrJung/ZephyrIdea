package org.b3log.zephyr;

import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.editor.Caret;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.actionSystem.EditorWriteActionHandler;
import com.intellij.openapi.editor.actions.TextComponentEditorAction;
import com.intellij.openapi.ui.DialogWrapper;
import org.jdesktop.swingx.sort.SortUtils;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

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

    private static class Handler extends EditorWriteActionHandler {
        public void executeWriteAction(Editor editor, @Nullable Caret caret, DataContext dataContext) {
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

            // Ignore last lines (usually one) which are only '\n'
            endLine = ignoreLastEmptyLines(doc, endLine);

            if (startLine >= endLine) {
                return;
            }

            CountLineDialog countLineDialog = new CountLineDialog(endLine - startLine + 1 + "");
            countLineDialog.show();
        }

        private int ignoreLastEmptyLines(Document doc, int endLine) {
            while (endLine >= 0) {
                if (doc.getLineEndOffset(endLine) > doc.getLineStartOffset(endLine)) {
                    return endLine;
                }

                endLine--;
            }

            return -1;
        }
    }
}

class CountLineDialog extends DialogWrapper {
    private String text;

    public CountLineDialog(String text) {
        super(false);
        this.text = text;
        init();
    }

    @Nullable
    protected JComponent createCenterPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(new JTextArea(this.text));
        return panel;
    }
}