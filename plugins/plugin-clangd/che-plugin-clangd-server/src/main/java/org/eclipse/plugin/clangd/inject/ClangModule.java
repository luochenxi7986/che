/*******************************************************************************
 * Copyright (c) 2012-2017 Codenvy, S.A.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Codenvy, S.A. - initial API and implementation
 *******************************************************************************/
package org.eclipse.plugin.clangd.inject;

import com.google.inject.AbstractModule;
import com.google.inject.multibindings.Multibinder;

import org.eclipse.che.api.languageserver.launcher.LanguageServerLauncher;
import org.eclipse.che.inject.DynaModule;
import org.eclipse.plugin.clangd.languageserver.ClangDLanguageServerLauncher;

/**
 * @author Alexander Andrienko
 */
@DynaModule
public class ClangModule extends AbstractModule {
    @Override
    protected void configure() {
        Multibinder.newSetBinder(binder(), LanguageServerLauncher.class).addBinding().to(ClangDLanguageServerLauncher.class);
    }
}