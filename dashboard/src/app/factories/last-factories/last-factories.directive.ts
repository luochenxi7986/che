/*
 * Copyright (c) 2015-2017 Red Hat, Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Red Hat, Inc. - initial API and implementation
 */
'use strict';

/**
 * @ngdoc directive
 * @name factories.directive:LastFactories
 * @description This class is handling the directive of the listing last opened factories
 * @author Oleksii Orel
 */
export class LastFactories {
  private restrict: string;
  private templateUrl: string;
  private replace: boolean;
  private controller: string;
  private controllerAs: string;
  private bindToController: boolean;

  /**
   * Default constructor that is using resource
   * @ngInject for Dependency injection
   */
  constructor() {
    this.restrict = 'E';
    this.templateUrl = 'app/factories/last-factories/last-factories.html';
    this.replace = false;
    this.controller = 'LastFactoriesController';
    this.controllerAs = 'lastFactoriesController';
    this.bindToController = true;
  }
}
