/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jclouds.compute.domain;

import static org.testng.Assert.assertNull;

import org.jclouds.compute.domain.Volume.Type;
import org.testng.annotations.Test;

/**
 * Unit tests for org.jclouds.compute.domain.VolumeBuilder
 */
@Test(testName = "VolumeBuilderTest", groups = "unit")
public class VolumeBuilderTest {

    /**
     * Ensures that an explicitly set null id results in no errors.
     */
    public void testNullDescription() {
        Volume vol = new VolumeBuilder().id(null)
                .type(Type.LOCAL)
                .size(10.0f)
                .device("/dev/sda1")
                .bootDevice(true)
                .durable(false)
                .build();
        assertNull(vol.getId());
    }
}
