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
package org.eclipse.che.plugin.debugger.ide.debug.tree.node;

import static java.util.Collections.emptyList;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import java.util.List;
import org.eclipse.che.api.debug.shared.model.WatchExpression;
import org.eclipse.che.api.debug.shared.model.impl.WatchExpressionImpl;
import org.eclipse.che.api.promises.client.Promise;
import org.eclipse.che.api.promises.client.PromiseProvider;
import org.eclipse.che.ide.api.data.tree.Node;
import org.eclipse.che.ide.debug.DebuggerManager;
import org.eclipse.che.ide.ui.smartTree.presentation.NodePresentation;
import org.eclipse.che.plugin.debugger.ide.DebuggerResources;

/** @author Alexander Andrienko */
public class WatchExpressionNode extends AbstractDebuggerNode<WatchExpression> {

  private final PromiseProvider promiseProvider;

  private WatchExpression watchExpression;
  private DebuggerResources debuggerResources;
  private DebuggerManager debuggerManager;

  @Inject
  public WatchExpressionNode(
      @Assisted WatchExpression watchExpression,
      PromiseProvider promiseProvider,
      DebuggerResources debuggerResources,
      DebuggerManager debuggerManager) {
    this.promiseProvider = promiseProvider;
    this.watchExpression = watchExpression;
    this.debuggerResources = debuggerResources;
    this.debuggerManager = debuggerManager;
  }

  @Override
  protected Promise<List<Node>> getChildrenImpl() {
    return promiseProvider.resolve(emptyList());
  }

  @Override
  public String getName() {
    return watchExpression.getExpression();
  }

  @Override
  public boolean isLeaf() {
    return true;
  }

  @Override
  public void updatePresentation(NodePresentation presentation) {
    String content = watchExpression.getExpression() + "=" + watchExpression.getResult();
    presentation.setPresentableText(content);
    presentation.setPresentableIcon(debuggerResources.watchExpressionIcon());
  }

  //todo generate unique id and set up it like a key...
  @Override
  public String getKey() {
    return String.valueOf(hashCode());
  }

  @Override
  public WatchExpression getData() {
    return watchExpression;
  }

  public void calculateWatchExpression(
      WatchExpressionNode watchExpressionNode, long threadId, int frameIndex) {
    final String exprContent = watchExpressionNode.getData().getExpression();
    debuggerManager
        .getActiveDebugger()
        .evaluate(exprContent, threadId, frameIndex)
        .then(
            result -> {
              WatchExpression watchExpression = new WatchExpressionImpl(exprContent, result);
              watchExpressionNode.setData(watchExpression);
              view.updateWatchExpression(watchExpressionNode);
            })
        .catchError(
            error -> {
              WatchExpression watchExpression =
                  new WatchExpressionImpl(exprContent, error.getMessage());
              watchExpressionNode.setData(watchExpression);
              view.updateWatchExpression(watchExpressionNode);
            });
  }

  @Override
  public void setData(WatchExpression watchExpression) {
    this.watchExpression = watchExpression;
  }
}
