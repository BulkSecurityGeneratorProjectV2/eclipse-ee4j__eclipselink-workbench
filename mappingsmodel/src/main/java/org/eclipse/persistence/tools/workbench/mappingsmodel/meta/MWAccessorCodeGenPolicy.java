/*
 * Copyright (c) 1998, 2018 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0,
 * or the Eclipse Distribution License v. 1.0 which is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * SPDX-License-Identifier: EPL-2.0 OR BSD-3-Clause
 */

// Contributors:
//     Oracle - initial API and implementation from Oracle TopLink
package org.eclipse.persistence.tools.workbench.mappingsmodel.meta;

public abstract class MWAccessorCodeGenPolicy
    extends MWMethodCodeGenPolicy
{
    private volatile MWClassAttribute accessedAttribute;


    MWAccessorCodeGenPolicy(MWMethod method, MWClassAttribute attribute, MWClassCodeGenPolicy classCodeGenPolicy)
    {
        super(method, classCodeGenPolicy);
        this.accessedAttribute = attribute;
    }

    MWClassAttribute getAccessedAttribute()
    {
        return this.accessedAttribute;
    }

    /**
     * Return "{@code this.<attribute name>}" in case of non value holders.
     * Return "{@code <value get method name>()}" in case of value holder, and the value get method exists.
     * Return null otherwise.
     */
    protected String attributeValueCode()
    {
        if (getAccessedAttribute().isValueHolder()) {
            if (getAccessedAttribute().getValueGetMethod() != null) {
                return getAccessedAttribute().getValueGetMethod().shortSignature();
            }
            return null;
        }
        return "this." + getAccessedAttribute().getName();
    }
}
