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

package com.liferay.notifications.hook.upgrade.v1_1_0;

import com.liferay.compat.portal.kernel.util.ListUtil;
import com.liferay.notifications.util.NotificationsConstants;
import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.StringBundler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.List;

/**
 * @author Calvin Keum
 */
public class UpgradeUserNotificationEvent extends UpgradeProcess {

	protected void addUserNotificationEvent(
			long companyId, long userId, long userNotificationEventId,
			long timestamp, boolean actionRequired, boolean delivered,
			boolean archived)
		throws Exception {

		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = DataAccess.getUpgradeOptimizedConnection();

			StringBundler sb = new StringBundler(5);

			sb.append("insert into Notifications_UserNotificationEvent (");
			sb.append("notificationEventId, companyId, userId,");
			sb.append("userNotificationEventId, timestamp , delivered,");
			sb.append("actionRequired, archived) values (?, ?, ?, ?, ?, ?");
			sb.append(", ?, ?)");

			ps = con.prepareStatement(sb.toString());

			ps.setLong(1, increment());
			ps.setLong(2, companyId);
			ps.setLong(3, userId);
			ps.setLong(4, userNotificationEventId);
			ps.setLong(5, timestamp);
			ps.setBoolean(6, actionRequired);
			ps.setBoolean(7, delivered);
			ps.setBoolean(8, archived);

			ps.executeUpdate();
		}
		finally {
			DataAccess.cleanUp(con, ps);
		}
	}


	protected void doUpgrade() throws Exception {
		upgradeNotificationEvents();
	}

	protected void upgradeNotificationEvents() throws Exception {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = DataAccess.getUpgradeOptimizedConnection();

			ps = con.prepareStatement("select * from UserNotificationEvent");

			rs = ps.executeQuery();

			while (rs.next()) {
				long userNotificationEventId = rs.getLong(
					"userNotificationEventId");
				long companyId = rs.getLong("companyId");
				long userId = rs.getLong("userId");
				String type = rs.getString("type_");
				long timestamp = rs.getLong("timestamp");
				boolean delivered = rs.getBoolean("delivered");
				boolean archived = rs.getBoolean("archived");

				boolean actionRequired = false;

				if (_actionRequiredTypes.contains(type)) {
					actionRequired = true;
				}

				addUserNotificationEvent(
					companyId, userId, userNotificationEventId, timestamp,
					actionRequired, delivered, archived);
			}
		}
		finally {
			DataAccess.cleanUp(con, ps, rs);
		}
	}

	private List<String> _actionRequiredTypes = ListUtil.fromArray(
		NotificationsConstants.ACTIONABLE_TYPES);

}