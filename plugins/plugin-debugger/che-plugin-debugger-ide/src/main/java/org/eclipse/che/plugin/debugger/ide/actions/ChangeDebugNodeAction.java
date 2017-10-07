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

import com.google.inject.Inject;

import org.eclipse.che.ide.api.action.AbstractPerspectiveAction;
import org.eclipse.che.ide.api.action.ActionEvent;
import org.eclipse.che.plugin.debugger.ide.DebuggerLocalizationConstant;
import org.eclipse.che.plugin.debugger.ide.DebuggerResources;
import org.eclipse.che.plugin.debugger.ide.debug.DebuggerView;
import org.eclipse.che.plugin.debugger.ide.debug.dialogs.changevalue.UpdateValuePresenter;
import org.eclipse.che.plugin.debugger.ide.debug.dialogs.watch.UpdateExpressionPresenter;

import java.util.Collections;

import static org.eclipse.che.ide.workspace.perspectives.project.ProjectPerspective.PROJECT_PERSPECTIVE_ID;

/**
 * Action which allows change value of selected variable with debugger
 *
 * @author Mykola Morhun
 */
public class ChangeDebugNodeAction extends AbstractPerspectiveAction {

  private final UpdateValuePresenter updateValuePresenter;
  private final UpdateExpressionPresenter updateExpressionPresenter;
  private final DebuggerView debuggerView;

  @Inject
  public ChangeDebugNodeAction(
      DebuggerLocalizationConstant locale,
      DebuggerResources resources,
      UpdateValuePresenter updateValuePresenter,
      UpdateExpressionPresenter updateExpressionPresenter,
      DebuggerView debuggerView) {
    super(
        Collections.singletonList(PROJECT_PERSPECTIVE_ID),
        locale.updateVariableValueAndWatchExpression(),
        locale.updateVariableValueAndWatchExpressionDescription(),
        null,
        resources.changeDebugNode());
    this.updateValuePresenter = updateValuePresenter;
    this.updateExpressionPresenter = updateExpressionPresenter;
    this.debuggerView = debuggerView;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (debuggerView.isWatchExpressionSelected()) {
      updateExpressionPresenter.showDialog();
    } else if (debuggerView.isVariableSelected()) {
      updateValuePresenter.showDialog();
    }
  }

  @Override
  public void updateInPerspective(ActionEvent event) {
    event
        .getPresentation()
        .setEnabled(debuggerView.isVariableSelected() || debuggerView.isWatchExpressionSelected());
  }
}
