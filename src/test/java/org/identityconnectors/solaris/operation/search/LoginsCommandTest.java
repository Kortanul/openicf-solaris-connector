/*
 * ====================
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright 2008-2009 Sun Microsystems, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Common Development
 * and Distribution License("CDDL") (the "License").  You may not use this file
 * except in compliance with the License.
 *
 * You can obtain a copy of the License at
 * http://opensource.org/licenses/cddl1.php
 * See the License for the specific language governing permissions and limitations
 * under the License.
 *
 * When distributing the Covered Code, include this CDDL Header Notice in each file
 * and include the License file at http://opensource.org/licenses/cddl1.php.
 * If applicable, add the following below this CDDL Header, with the fields
 * enclosed by brackets [] replaced by your own identifying information:
 * "Portions Copyrighted [year] [name of copyright owner]"
 * ====================
 */

package org.identityconnectors.solaris.operation.search;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import java.util.Set;

import org.identityconnectors.framework.common.objects.Attribute;
import org.identityconnectors.solaris.attr.NativeAttribute;
import org.identityconnectors.solaris.test.SolarisTestBase;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

public class LoginsCommandTest extends SolarisTestBase {

    @Test
    public void test() {
        SolarisEntry result = LoginsCommand.getAttributesFor("root", getConnection());
        AssertJUnit.assertTrue(result.getAttributeSet().size() >= 5);
        Set<Attribute> attrSet = result.getAttributeSet();
        for (Attribute attribute : attrSet) {
            if (attribute.getName().equals(NativeAttribute.NAME)) {
                assertNotNull(attribute.getValue());
                assertTrue(attribute.getValue().size() == 1);
                assertEquals("root", attribute.getValue().get(0));
                break;
            }
        }
    }

    @Override
    public boolean createGroup() {
        return false;
    }

    @Override
    public int getCreateUsersNumber() {
        return 0;
    }
}
