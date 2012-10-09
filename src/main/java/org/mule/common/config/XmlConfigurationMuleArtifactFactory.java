/*
 * $Id$
 * --------------------------------------------------------------------------------------
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */

package org.mule.common.config;

import org.mule.common.MuleArtifactFactory;

import org.w3c.dom.Element;

public interface XmlConfigurationMuleArtifactFactory
    extends MuleArtifactFactory<Element, XmlConfigurationCallback>
{

}