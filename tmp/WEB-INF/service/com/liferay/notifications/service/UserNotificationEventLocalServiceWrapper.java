/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.notifications.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link UserNotificationEventLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see UserNotificationEventLocalService
 * @generated
 */
public class UserNotificationEventLocalServiceWrapper
	implements UserNotificationEventLocalService,
		ServiceWrapper<UserNotificationEventLocalService> {
	public UserNotificationEventLocalServiceWrapper(
		UserNotificationEventLocalService userNotificationEventLocalService) {
		_userNotificationEventLocalService = userNotificationEventLocalService;
	}

	/**
	* Adds the user notification event to the database. Also notifies the appropriate model listeners.
	*
	* @param userNotificationEvent the user notification event
	* @return the user notification event that was added
	* @throws SystemException if a system exception occurred
	*/

	public com.liferay.notifications.model.UserNotificationEvent addUserNotificationEvent(
		com.liferay.notifications.model.UserNotificationEvent userNotificationEvent)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _userNotificationEventLocalService.addUserNotificationEvent(userNotificationEvent);
	}

	/**
	* Creates a new user notification event with the primary key. Does not add the user notification event to the database.
	*
	* @param notificationEventId the primary key for the new user notification event
	* @return the new user notification event
	*/

	public com.liferay.notifications.model.UserNotificationEvent createUserNotificationEvent(
		long notificationEventId) {
		return _userNotificationEventLocalService.createUserNotificationEvent(notificationEventId);
	}

	/**
	* Deletes the user notification event with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param notificationEventId the primary key of the user notification event
	* @return the user notification event that was removed
	* @throws PortalException if a user notification event with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/

	public com.liferay.notifications.model.UserNotificationEvent deleteUserNotificationEvent(
		long notificationEventId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _userNotificationEventLocalService.deleteUserNotificationEvent(notificationEventId);
	}

	/**
	* Deletes the user notification event from the database. Also notifies the appropriate model listeners.
	*
	* @param userNotificationEvent the user notification event
	* @return the user notification event that was removed
	* @throws SystemException if a system exception occurred
	*/

	public com.liferay.notifications.model.UserNotificationEvent deleteUserNotificationEvent(
		com.liferay.notifications.model.UserNotificationEvent userNotificationEvent)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _userNotificationEventLocalService.deleteUserNotificationEvent(userNotificationEvent);
	}


	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _userNotificationEventLocalService.dynamicQuery();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	* @throws SystemException if a system exception occurred
	*/

	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _userNotificationEventLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.notifications.model.impl.UserNotificationEventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	* @throws SystemException if a system exception occurred
	*/

	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return _userNotificationEventLocalService.dynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.notifications.model.impl.UserNotificationEventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	* @throws SystemException if a system exception occurred
	*/

	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _userNotificationEventLocalService.dynamicQuery(dynamicQuery,
			start, end, orderByComparator);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/

	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _userNotificationEventLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/

	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _userNotificationEventLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}


	public com.liferay.notifications.model.UserNotificationEvent fetchUserNotificationEvent(
		long notificationEventId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _userNotificationEventLocalService.fetchUserNotificationEvent(notificationEventId);
	}

	/**
	* Returns the user notification event with the primary key.
	*
	* @param notificationEventId the primary key of the user notification event
	* @return the user notification event
	* @throws PortalException if a user notification event with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/

	public com.liferay.notifications.model.UserNotificationEvent getUserNotificationEvent(
		long notificationEventId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _userNotificationEventLocalService.getUserNotificationEvent(notificationEventId);
	}


	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _userNotificationEventLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the user notification events.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.notifications.model.impl.UserNotificationEventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of user notification events
	* @param end the upper bound of the range of user notification events (not inclusive)
	* @return the range of user notification events
	* @throws SystemException if a system exception occurred
	*/

	public java.util.List<com.liferay.notifications.model.UserNotificationEvent> getUserNotificationEvents(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _userNotificationEventLocalService.getUserNotificationEvents(start,
			end);
	}

	/**
	* Returns the number of user notification events.
	*
	* @return the number of user notification events
	* @throws SystemException if a system exception occurred
	*/

	public int getUserNotificationEventsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _userNotificationEventLocalService.getUserNotificationEventsCount();
	}

	/**
	* Updates the user notification event in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param userNotificationEvent the user notification event
	* @return the user notification event that was updated
	* @throws SystemException if a system exception occurred
	*/

	public com.liferay.notifications.model.UserNotificationEvent updateUserNotificationEvent(
		com.liferay.notifications.model.UserNotificationEvent userNotificationEvent)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _userNotificationEventLocalService.updateUserNotificationEvent(userNotificationEvent);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/

	public java.lang.String getBeanIdentifier() {
		return _userNotificationEventLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/

	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_userNotificationEventLocalService.setBeanIdentifier(beanIdentifier);
	}


	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _userNotificationEventLocalService.invokeMethod(name,
			parameterTypes, arguments);
	}


	public com.liferay.notifications.model.UserNotificationEvent addUserNotificationEvent(
		long userNotificationEventId, long userId, long timeStamp,
		boolean actionRequired, boolean delivered, boolean archived)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _userNotificationEventLocalService.addUserNotificationEvent(userNotificationEventId,
			userId, timeStamp, actionRequired, delivered, archived);
	}


	public java.util.List<com.liferay.notifications.model.UserNotificationEvent> getArchivedUserNotificationEvents(
		long userId, boolean actionRequired, boolean archived, int start,
		int end)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _userNotificationEventLocalService.getArchivedUserNotificationEvents(userId,
			actionRequired, archived, start, end);
	}


	public int getArchivedUserNotificationEventsCount(long userId,
		boolean actionRequired, boolean archived)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _userNotificationEventLocalService.getArchivedUserNotificationEventsCount(userId,
			actionRequired, archived);
	}


	public java.util.List<com.liferay.notifications.model.UserNotificationEvent> getDeliveredUserNotificationEvents(
		long userId, boolean delivered, boolean actionRequired, int start,
		int end)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _userNotificationEventLocalService.getDeliveredUserNotificationEvents(userId,
			delivered, actionRequired, start, end);
	}


	public int getDeliveredUserNotificationEventsCount(long userId,
		boolean delivered, boolean actionRequired)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _userNotificationEventLocalService.getDeliveredUserNotificationEventsCount(userId,
			delivered, actionRequired);
	}


	public com.liferay.notifications.model.UserNotificationEvent getNotificationEventByUserNotificationEventId(
		long userNotificationEventId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _userNotificationEventLocalService.getNotificationEventByUserNotificationEventId(userNotificationEventId);
	}


	public java.util.List<com.liferay.notifications.model.UserNotificationEvent> getUserNotificationEvents(
		long userId, boolean actionRequired, int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _userNotificationEventLocalService.getUserNotificationEvents(userId,
			actionRequired, start, end);
	}


	public int getUserNotificationEventsCount(long userId,
		boolean actionRequired)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _userNotificationEventLocalService.getUserNotificationEventsCount(userId,
			actionRequired);
	}


	public com.liferay.notifications.model.UserNotificationEvent updateUserNotificationEvent(
		long notificationEventId, long timeStamp, boolean actionRequired,
		boolean delivered, boolean archived)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _userNotificationEventLocalService.updateUserNotificationEvent(notificationEventId,
			timeStamp, actionRequired, delivered, archived);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	public UserNotificationEventLocalService getWrappedUserNotificationEventLocalService() {
		return _userNotificationEventLocalService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	public void setWrappedUserNotificationEventLocalService(
		UserNotificationEventLocalService userNotificationEventLocalService) {
		_userNotificationEventLocalService = userNotificationEventLocalService;
	}


	public UserNotificationEventLocalService getWrappedService() {
		return _userNotificationEventLocalService;
	}


	public void setWrappedService(
		UserNotificationEventLocalService userNotificationEventLocalService) {
		_userNotificationEventLocalService = userNotificationEventLocalService;
	}

	private UserNotificationEventLocalService _userNotificationEventLocalService;
}