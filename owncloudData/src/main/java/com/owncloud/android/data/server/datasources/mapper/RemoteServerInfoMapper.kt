/**
 * ownCloud Android client application
 *
 * @author Abel García de Prada
 * Copyright (C) 2020 ownCloud GmbH.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License version 2,
 * as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.owncloud.android.data.server.datasources.mapper

import com.owncloud.android.domain.mappers.RemoteMapper
import com.owncloud.android.domain.server.model.AuthenticationMethod
import com.owncloud.android.domain.server.model.ServerInfo
import com.owncloud.android.lib.resources.server.RemoteAuthenticationMethod
import com.owncloud.android.lib.resources.server.RemoteServerInfo

class RemoteServerInfoMapper : RemoteMapper<ServerInfo, RemoteServerInfo> {
    override fun toModel(remote: RemoteServerInfo?): ServerInfo? =
        remote?.run {
            ServerInfo(
                authenticationMethods = authenticationMethods.map { toAuthMethod(it) },
                isSecureConnection = isSecureConnection,
                baseUrl = baseUrl,
                ownCloudVersion = owncloudVersion.toString()
            )
        }

    // Not needed
    override fun toRemote(model: ServerInfo?): RemoteServerInfo? = null

    private fun toAuthMethod(remoteAuthenticationMethod: RemoteAuthenticationMethod): AuthenticationMethod =
        when (remoteAuthenticationMethod) {
            RemoteAuthenticationMethod.BASIC_HTTP_AUTH -> AuthenticationMethod.BASIC_HTTP_AUTH
            RemoteAuthenticationMethod.BEARER_TOKEN -> AuthenticationMethod.BEARER_TOKEN
            RemoteAuthenticationMethod.NONE -> AuthenticationMethod.NONE
        }
}
