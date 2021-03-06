/*
 * Copyright 2017 Huawei Technologies Co., Ltd
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.servicecomb.provider.springmvc.reference;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.web.client.RequestCallback;

import io.servicecomb.provider.springmvc.reference.CseClientHttpRequest;
import io.servicecomb.provider.springmvc.reference.CseHttpEntity;
import io.servicecomb.provider.springmvc.reference.CseRequestCallback;
import mockit.Injectable;
import mockit.Mocked;

public class TestCseRequestCallback {
    @Test
    public void testNormal(@Mocked RequestCallback callback) throws IOException {
        CseClientHttpRequest request = new CseClientHttpRequest();
        CseRequestCallback cb = new CseRequestCallback(null, callback);
        cb.doWithRequest(request);

        Assert.assertEquals(null, request.getContext());
    }

    @Test
    public void testCseEntity(@Injectable CseHttpEntity<?> entity,
            @Mocked RequestCallback callback) throws IOException {
        CseClientHttpRequest request = new CseClientHttpRequest();

        entity.addContext("c1", "c2");
        CseRequestCallback cb = new CseRequestCallback(entity, callback);
        cb.doWithRequest(request);

        Assert.assertEquals(entity.getContext(), request.getContext());
    }
}
