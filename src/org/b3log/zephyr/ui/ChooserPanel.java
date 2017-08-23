package org.b3log.zephyr.ui;

import com.intellij.openapi.editor.markup.TextAttributes;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.EventListener;

/**
 * Created by Rammer on 07/02/2017.
 */
public interface ChooserPanel {

    @NotNull
    JComponent getPanel();

    void resetDefault();

    void reset(@NotNull TextAttributes namedTextAttr);

    void apply(@NotNull TextAttributes namedTextAttr);

    void addListener(@NotNull ColorChangedListener listener);
    
    interface ColorChangedListener extends EventListener {

        void onColorChanged(ActionEvent e);
    }
}
