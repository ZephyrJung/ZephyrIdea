package org.b3log.zephyr.ui;

import org.b3log.zephyr.config.NamedTextAttr;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.util.EventListener;
import java.util.List;

/**
 * Created by Rammer on 07/02/2017.
 */
public interface PreviewPanel {

    @NotNull
    JComponent getPanel();

    void updateView(@NotNull List<NamedTextAttr> namedTextAttrList);

    void blinkSelectedColor(@NotNull NamedTextAttr namedTextAttr);

    void addListener(@NotNull PreviewSelectListener listener);

    void disposeUIResources();

    interface PreviewSelectListener extends EventListener {

        void onPreviewSelected();
    }
}
