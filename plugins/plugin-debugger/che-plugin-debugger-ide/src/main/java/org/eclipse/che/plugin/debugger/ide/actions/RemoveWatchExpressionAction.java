/*
 * Copyright (c) 2012-2017 Red Hat, Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Red Hat, Inc. - initial API and implementation
 */
package org.eclipse.che.plugin.debugger.ide.actions;

import static org.eclipse.che.ide.workspace.perspectives.project.ProjectPerspective.PROJECT_PERSPECTIVE_ID;

import com.google.inject.Inject;
import java.util.Collections;
import org.eclipse.che.ide.api.action.AbstractPerspectiveAction;
import org.eclipse.che.ide.api.action.ActionEvent;
import org.eclipse.che.plugin.debugger.ide.DebuggerLocalizationConstant;
import org.eclipse.che.plugin.debugger.ide.DebuggerResources;
import org.eclipse.che.plugin.debugger.ide.debug.DebuggerPresenter;
import org.eclipse.che.plugin.debugger.ide.debug.DebuggerView;
import org.eclipse.che.plugin.debugger.ide.debug.tree.node.WatchExpressionNode;

/** @author Alexander Andrienko */
public class RemoveWatchExpressionAction extends AbstractPerspectiveAction {

  private final DebuggerPresenter debuggerPresenter;
  private final DebuggerView debuggerView;

  @Inject
  public RemoveWatchExpressionAction(
      DebuggerLocalizationConstant locale,
      DebuggerResources resources,
      DebuggerPresenter debuggerPresenter,
      DebuggerView debuggerView) {
    super(
        Collections.singletonList(PROJECT_PERSPECTIVE_ID),
        locale.removeWatchExpression(),
        locale.removeWatchExpressionDescription(),
        null,
        resources.removeExpressionBtn());
    this.debuggerPresenter = debuggerPresenter;
    this.debuggerView = debuggerView;
  }

  @Override
  public void actionPerformed(ActionEvent event) {
    debuggerView.removeWatchExpression();
    debuggerPresenter.removeWatchExpressionNode((WatchExpressionNode) selectedNode);
  }

  @Override
  public void updateInPerspective(ActionEvent event) {
    event
        .getPresentation()
        .setEnabled(debuggerView.getSelectedTeeNode() instanceof WatchExpressionNode);
  }
}
