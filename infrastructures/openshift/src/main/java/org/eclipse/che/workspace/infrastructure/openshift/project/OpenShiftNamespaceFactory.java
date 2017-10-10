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
package org.eclipse.che.workspace.infrastructure.openshift.project;

import com.google.inject.assistedinject.Assisted;

/**
 * Helps to create {@link OpenShiftNamespace} instances.
 *
 * @author Anton Korneta
 */
public interface OpenShiftNamespaceFactory {

  /** Creates Openshfit namespace by given name and workspace id. */
  OpenShiftNamespace createNamespace(
      @Assisted("namespace") String namespace, @Assisted("workspaceId") String workspaceId);

  /** Creates Openshfit namespace by given workspace id. */
  OpenShiftNamespace createNamespace(String workspaceId);
}
