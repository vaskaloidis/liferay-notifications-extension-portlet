AUI.add(
	'liferay-plugin-dockbar-notifications',
	function(A) {
		var DockbarNotifications = A.Component.create(
			{
				AUGMENTS: [Liferay.PortletBase],

				EXTENDS: A.Base,

				NAME: 'dockbarnotifications',

				prototype: {
					initializer: function(config) {
						var instance = this;

						instance._actionableNotificationsList = config.actionableNotificationsList;
						instance._baseActionURL = config.baseActionURL;
						instance._baseResourceURL = config.baseResourceURL;
						instance._nonActionableNotificationsList = config.nonActionableNotificationsList;
						instance._portletKey = config.portletKey;

						var userNotifications = A.one('.dockbar-user-notifications');

						userNotifications.on(
							'click',
							function(event) {
								var target = event.target;

								if (target.ancestor('.dockbar-user-notifications-container')) {
									return;
								}

								instance._setDelivered();

								var currentTarget = event.currentTarget;

								var container = currentTarget.one('.dockbar-user-notifications-container');

								container.toggleClass('open');

								var menuOpen = container.hasClass('open');

								if (menuOpen) {
									currentTarget.on(
										'clickoutside',
										function(event) {
											container.removeClass('open');
										}
									);

									instance._nonActionableNotificationsList.render();
									instance._actionableNotificationsList.render();
								}
							}
						);

						A.on(
							'domready',
							function() {
								Liferay.Poller.addListener(instance._portletKey, instance._onPollerUpdate, instance);
							}
						);

						Liferay.on('updateNotificationsCount', instance._getNotificationsCount, instance);
					},

					_getNotificationsCount: function() {
						var instance = this;

						var portletURL = new Liferay.PortletURL.createURL(instance._baseResourceURL);

						portletURL.setResourceId('getNotificationsCount');

						A.io.request(
							portletURL.toString(),
							{
								dataType: 'JSON',
								on: {
									success: function() {
										var response = this.get('responseData');

										if (response) {
											var newUserNotificationsCount = response.newUserNotificationsCount;
											var timestamp = response.timestamp;
											var unreadUserNotificationsCount = response.unreadUserNotificationsCount;

											instance._updateDockbarNotificationsCount(newUserNotificationsCount, timestamp, unreadUserNotificationsCount);
										}
									}
								}
							}
						);
					},

					_onPollerUpdate: function(response) {
						var instance = this;

						instance._updateDockbarNotificationsCount(response.newUserNotificationsCount, response.timestamp, response.unreadUserNotificationsCount);
					},

					_setDelivered: function() {
						var instance = this;

						var portletURL = new Liferay.PortletURL.createURL(instance._baseActionURL);

						portletURL.setParameter('javax.portlet.action', 'setDelivered');

						portletURL.setWindowState('normal');

						A.io.request(portletURL.toString());
					},

					_updateDockbarNotificationsCount: function(newUserNotificationsCount, timestamp, unreadUserNotificationsCount) {
						var instance = this;

						if (!instance._previousTimestamp || (instance._previousTimestamp < timestamp)) {
							instance._previousTimestamp = timestamp;

							var dockbarUserNotificationsCount = A.one('.dockbar-user-notifications .user-notifications-count');

							if (dockbarUserNotificationsCount) {
								dockbarUserNotificationsCount.toggleClass('alert', (newUserNotificationsCount > 0));

								dockbarUserNotificationsCount.setHTML(unreadUserNotificationsCount);
							}
						}
					}
				}
			}
		);

		Liferay.DockbarNotifications = DockbarNotifications;
	},
	'',
	{
		requires: ['aui-base', 'aui-io', 'liferay-poller', 'liferay-portlet-base', 'liferay-portlet-url']
	}

);

AUI.add(
	'liferay-plugin-notifications',
	function(A) {
		var Notifications = A.Component.create(
			{
				AUGMENTS: [Liferay.PortletBase],

				EXTENDS: A.Base,

				NAME: 'notifications',

				prototype: {
					initializer: function(config) {
						var instance = this;

						instance._actionableUserNotificationsStart = 0;
						instance._baseRenderURL = config.baseRenderURL;
						instance._nonActionableUserNotificationsStart = 0;
						instance._notificationsList = config.notificationsList;

						instance._notificationsList.render();

						var notificationsConfigurationNode = A.one('.notifications-portlet .user-notifications-container .notifications-configurations');
						var userNotificationsListNode = A.one('.notifications-portlet .user-notifications-container .user-notifications-list');
						var userNotificationsContainerNode = A.one('.notifications-portlet .user-notifications-container');

						var nonActionableUserNotificationsLink = A.one('.notifications-portlet .user-notifications-container .user-notifications-sidebar .nav .non-actionable');

						if (nonActionableUserNotificationsLink) {
							nonActionableUserNotificationsLink.on(
								'click',
								function() {
									var userNotificationsSidebar = A.one('.user-notifications-sidebar');

									if (userNotificationsSidebar) {
										userNotificationsSidebar.all('.nav a').removeClass('selected');
									}

									nonActionableUserNotificationsLink.addClass('selected');

									if (userNotificationsContainerNode) {
										userNotificationsContainerNode.addClass('non-actionable');
										userNotificationsContainerNode.removeClass('actionable');
									}

									notificationsConfigurationNode.hide();
									userNotificationsListNode.show();

									instance._notificationsList.setActionable(false);

									instance._notificationsList.setNotificationsCount('.non-actionable .count');

									instance._actionableUserNotificationsStart = instance._notificationsList.getStart();

									instance._notificationsList.setStart(instance._nonActionableUserNotificationsStart);

									instance._notificationsList.render();
								}
							);
						}

						var actionableUserNotificationsLink = A.one('.notifications-portlet .user-notifications-container .user-notifications-sidebar .nav .actionable');

						if (actionableUserNotificationsLink) {
							actionableUserNotificationsLink.on(
								'click',
								function() {
									var userNotificationsSidebar = A.one('.user-notifications-sidebar');

									if (userNotificationsSidebar) {
										userNotificationsSidebar.all('.nav a').removeClass('selected');
									}

									actionableUserNotificationsLink.addClass('selected');

									if (userNotificationsContainerNode) {
										userNotificationsContainerNode.addClass('actionable');
										userNotificationsContainerNode.removeClass('non-actionable');
									}

									notificationsConfigurationNode.hide();
									userNotificationsListNode.show();

									instance._notificationsList.setActionable(true);

									instance._notificationsList.setNotificationsCount('.actionable .count');

									instance._nonActionableUserNotificationsStart = instance._notificationsList.getStart();

									instance._notificationsList.setStart(instance._actionableUserNotificationsStart);

									instance._notificationsList.render();
								}
							);
						}

						var manageLink = A.one('.notifications-portlet .user-notifications-container .user-notifications-sidebar .nav .manage');

						if (manageLink) {
							manageLink.on(
								'click',
								function() {
									var userNotificationsSidebar = A.one('.user-notifications-sidebar');

									if (userNotificationsSidebar) {
										userNotificationsSidebar.all('.nav a').removeClass('selected');
									}

									manageLink.addClass('selected');

									if (notificationsConfigurationNode) {
										notificationsConfigurationNode.show();
										notificationsConfigurationNode.plug(A.LoadingMask).loadingmask.show();

										userNotificationsListNode.hide();

										var portletURL = new Liferay.PortletURL.createURL(instance._baseRenderURL);

										portletURL.setParameter('mvcPath', '/notifications/configuration.jsp');

										portletURL.setWindowState('exclusive');

										notificationsConfigurationNode.plug(A.Plugin.ParseContent);

										notificationsConfigurationNode.load(
											portletURL.toString(),
											function() {
												notificationsConfigurationNode.unplug(A.LoadingMask);
											}
										);
									}
								}
							);
						}
					}
				}
			}
		);

		Liferay.Notifications = Notifications;
	},
	'',
	{
		requires: ['aui-base', 'aui-io', 'liferay-portlet-base', 'liferay-portlet-url', 'node-load']
	}

);

AUI.add(
	'liferay-plugin-notifications-list',
	function(A) {
		var Lang = A.Lang;

		var NotificationsList = A.Component.create(
			{
				AUGMENTS: [Liferay.PortletBase],

				EXTENDS: A.Base,

				NAME: 'notificationslist',

				prototype: {
					initializer: function(config) {
						var instance = this;

						instance._actionable = config.actionable;
						instance._baseActionURL = config.baseActionURL;
						instance._baseRenderURL = config.baseRenderURL;
						instance._baseResourceURL = config.baseResourceURL;
						instance._delta = config.delta;
						instance._end = config.start + config.delta;
						instance._fullView = config.fullView;
						instance._namespace = config.namespace;
						instance._nextPageNode = config.nextPageNode;
						instance._markAllAsReadNode = config.markAllAsReadNode;
						instance._notificationsContainer = config.notificationsContainer;
						instance._notificationsCount = config.notificationsCount;
						instance._notificationsNode = config.notificationsNode;
						instance._paginationInfoNode = config.paginationInfoNode;
						instance._portletKey = config.portletKey;
						instance._previousPageNode = config.previousPageNode;
						instance._start = config.start;

						instance._bindUI();
					},

					getStart: function() {
						var instance = this;

						return instance._start;
					},

					render: function() {
						var instance = this;

						var notificationsContainer = A.one(instance._notificationsContainer);

						var notificationsNode = notificationsContainer.one(instance._notificationsNode);

						notificationsNode.plug(A.LoadingMask);

						notificationsNode.loadingmask.show();

						var portletURL = new Liferay.PortletURL.createURL(instance._baseResourceURL);

						portletURL.setParameter('actionable', instance._actionable);
						portletURL.setParameter('end', instance._end);
						portletURL.setParameter('fullView', instance._fullView);
						portletURL.setParameter('start', instance._start);

						portletURL.setResourceId('getUserNotificationEvents');

						A.io.request(
							portletURL.toString(),
							{
								dataType: 'JSON',
								on: {
									success: function() {
										var response = this.get('responseData');

										if (response) {
											var newTotalUuserNotificationEventsCount = response.newTotalUuserNotificationEventsCount;

											var notificationsCountNode = notificationsContainer.one(instance._notificationsCount);

											if (notificationsCountNode) {
												notificationsCountNode.setHTML(newTotalUuserNotificationEventsCount);
											}

											var entries = [];

											var entriesJSONArray = response.entries;

											if (entriesJSONArray) {
												for (var i = 0; i < entriesJSONArray.length; i++) {
													entries.push(entriesJSONArray[i]);
												}

												entries = entries.join('');
											}

											var markAllAsReadNode = notificationsContainer.one(instance._markAllAsReadNode);

											var markAllAsReadLink;

											if (markAllAsReadNode) {
												markAllAsReadLink = markAllAsReadNode.one('a');
											}

											var hasEntries = (entriesJSONArray.length > 0);

											if (!hasEntries) {
												var message = Liferay.Language.get('you-do-not-have-any-notifications');

												if (instance._actionable) {
													message = Liferay.Language.get('you-do-not-have-any-requests');
												}

												notificationsNode.setHTML('<div class=\"message\">' + message + '</div>');

												if (markAllAsReadLink) {
													markAllAsReadLink.hide();
												}
											}
											else {
												notificationsNode.setHTML(entries);

												var newUserNotificationEventsCount = response.newUserNotificationEventsCount;

												if (markAllAsReadLink) {
													markAllAsReadLink.toggle(!instance._actionable && newUserNotificationEventsCount > 0);
												}
											}

											var nextPageNode = notificationsContainer.all(instance._nextPageNode);
											var previousPageNode = notificationsContainer.all(instance._previousPageNode);

											var total = response.total;

											if (nextPageNode) {
												nextPageNode.toggle(total > instance._end);
											}

											if (previousPageNode) {
												previousPageNode.toggle(instance._start != 0);
											}

											var paginationInfoNode = notificationsContainer.all(instance._paginationInfoNode);

											var displayingCount = instance._end <= total ? instance._end : total;

											var paginationInfoText = Lang.sub(Liferay.Language.get('showing-x-x-of-x-results'), [(instance._start + 1), displayingCount, total]);

											if (paginationInfoNode) {
												if (hasEntries) {
													paginationInfoNode.setHTML(paginationInfoText);
												}

												paginationInfoNode.toggle(hasEntries);
											}

											instance._userNotificationEventIds = response.newUserNotificationEventIds;

											notificationsNode.loadingmask.hide();
										}
									}
								}
							}
						);

						Liferay.fire('updateNotificationsCount');
					},

					setActionable: function(actionable) {
						var instance = this;

						instance._actionable = actionable;
					},

					setNotificationsCount: function(notificationsCount) {
						var instance = this;

						instance._notificationsCount = notificationsCount;
					},

					setStart: function(start) {
						var instance = this;

						instance._start = start;

						instance._end = instance._start + instance._delta;
					},

					_bindMarkAllAsRead: function() {
						var instance = this;

						var notificationsContainer = A.one(instance._notificationsContainer);

						var markAllAsReadNode = notificationsContainer.one(instance._markAllAsReadNode);

						if (markAllAsReadNode) {
							markAllAsReadNode.on(
								'click',
								function(event) {
									event.preventDefault();

									var portletURL = new Liferay.PortletURL.createURL(instance._baseActionURL);

									portletURL.setParameter('javax.portlet.action', 'markAllAsRead');
									portletURL.setParameter('userNotificationEventIds', instance._userNotificationEventIds);

									portletURL.setWindowState('normal');

									A.io.request(
										portletURL.toString(),
										{
											after: {
												success: function() {
													var response = this.get('responseData');

													if (response.success) {
														instance.render();
													}
												}
											},
											dataType: 'JSON'
										}
									);
								}
							);
						}
					},

					_bindMarkAsRead: function() {
						var instance = this;

						var notificationsContainer = A.one(instance._notificationsContainer);

						var notificationsNode = notificationsContainer.one(instance._notificationsNode);

						if (notificationsNode) {
							notificationsNode.delegate(
								'click',
								function(event) {
									var currentTarget = event.currentTarget;

									var currentRow = currentTarget.ancestor('.user-notification');

									currentRow.plug(A.LoadingMask);

									currentRow.loadingmask.show();

									var userNotificationLink = currentRow.one('.user-notification-link');

									var markAsReadURL = userNotificationLink.attr('data-markAsReadURL');

									if (markAsReadURL) {
										A.io.request(
											markAsReadURL,
											{
												after: {
													success: function() {
														var responseData = this.get('responseData');

														if (responseData.success) {
															currentRow.loadingmask.hide();

															instance.render();
														}
													}
												},
												dataType: 'JSON'
											}
										);
									}
								},
								'.user-notification .mark-as-read'
							);
						}
					},

					_bindNextPageNotifications: function() {
						var instance = this;

						var notificationsContainer = A.one(instance._notificationsContainer);

						if (notificationsContainer) {
							notificationsContainer.delegate(
								'click',
								function() {
									instance._start += instance._delta;
									instance._end += instance._delta;

									instance.render();
								},
								instance._nextPageNode
							);
						}
					},

					_bindNotificationsAction: function() {
						var instance = this;

						var notificationsContainer = A.one(instance._notificationsContainer);

						var notificationsNode = notificationsContainer.one(instance._notificationsNode);

						if (notificationsNode) {
							notificationsNode.delegate(
								'click',
								function(event) {
									event.preventDefault();

									var currentTarget = event.currentTarget;

									var currentRow = currentTarget.ancestor('.user-notification');

									currentRow.plug(A.LoadingMask);

									currentRow.loadingmask.show();

									A.io.request(
										currentTarget.attr('href'),
										{
											after: {
												success: function() {
													var response = this.get('responseData');

													if (response.success) {
														var deleteNode = currentTarget.ancestor('.user-notification-delete');

														if (deleteNode) {
															A.io.request(
																deleteNode.getAttribute('data-deleteURL'),
																{
																	after: {
																		success: function() {
																			instance.render();
																		}
																	}
																}
															);
														}
													}
													else {
														currentRow.loadingmask.hide();
													}
												}
											},
											dataType: 'JSON'
										}
									);
								},
								'.user-notification .btn-action'
							);
						}
					},

					_bindPreviousPageNotifications: function() {
						var instance = this;

						var notificationsContainer = A.one(instance._notificationsContainer);

						if (notificationsContainer) {
							notificationsContainer.delegate(
								'click',
								function() {
									instance._start -= instance._delta;
									instance._end -= instance._delta;

									instance.render();
								},
								instance._previousPageNode
							);

						}
					},

					_bindUI: function() {
						var instance = this;

						instance._bindMarkAllAsRead();
						instance._bindMarkAsRead();
						instance._bindNotificationsAction();
						instance._bindNextPageNotifications();
						instance._bindPreviousPageNotifications();
						instance._bindViewNotification();
					},

					_bindViewNotification: function() {
						var instance = this;

						var notificationsContainer = A.one(instance._notificationsContainer);

						var notificationsNode = notificationsContainer.one(instance._notificationsNode);

						if (notificationsNode) {
							notificationsNode.delegate(
								'click',
								function(event) {
									var currentTarget = event.currentTarget;

									var target = event.target;

									if (target.hasClass('.mark-as-read') || target.ancestor('.mark-as-read') || (target._node.tagName == 'A')) {
										return;
									}

									var uri = currentTarget.attr('data-href');

									var markAsReadURL = currentTarget.attr('data-markAsReadURL');

									if (markAsReadURL) {
										A.io.request(
											markAsReadURL,
											{
												after: {
													success: function() {
														var responseData = this.get('responseData');

														if (responseData.success) {
															instance._redirect(uri);
														}
													}
												},
												dataType: 'JSON'
											}
										);
									}
									else {
										instance._redirect(uri);
									}
								},
								'.user-notification .user-notification-link'
							);
						}
					},

					_openWindow: function(uri) {
						return /p_p_state=(maximized|pop_up|exclusive)/.test(uri);
					},

					_redirect: function(uri) {
						var instance = this;

						if (uri) {
							if (instance._openWindow(uri)) {
								Liferay.Util.openWindow(
									{
										id: 'notificationsWindow',
										uri: uri
									}
								);
							}
							else {
								var topWindow = Liferay.Util.getTop();

								topWindow.location.href = uri;
							}
						}
					}
				}
			}
		);

		Liferay.NotificationsList = NotificationsList;
	},
	'',
	{
		requires: ['aui-base', 'aui-io', 'aui-loading-mask-deprecated', 'liferay-poller', 'liferay-portlet-base', 'liferay-portlet-url']
	}
);