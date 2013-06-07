/*
 * $Id$
 * --------------------------------------------------------------------------------------
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */

package org.mule.common.security.oauth;

import java.io.Serializable;

public interface OAuthConnector extends Serializable
{

    public String getAccessTokenUrl();

    public String getConsumerKey();

    public String getConsumerSecret();
    
    public String getAccessToken();
    
    public String getScope();

    public void postAuth();
    
    public String getAuthorizationUrl();
    
    @Deprecated
    public String getAccessTokenId();

}
