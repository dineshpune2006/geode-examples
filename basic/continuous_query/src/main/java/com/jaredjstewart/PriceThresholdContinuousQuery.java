/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.jaredjstewart;

import com.gemstone.gemfire.cache.Operation;
import com.gemstone.gemfire.cache.query.CqEvent;
import com.gemstone.gemfire.cache.query.CqListener;

import java.math.BigDecimal;
import java.text.NumberFormat;

public class PriceThresholdContinuousQuery implements CqListener {
       @Override
    public void onEvent(CqEvent aCqEvent) {
        if (!aCqEvent.getQueryOperation().equals(Operation.DESTROY)){
            String tickerSymbol = (String) aCqEvent.getKey();
            BigDecimal newPrice = (BigDecimal) aCqEvent.getNewValue();
            System.out.println("==============");
            System.out.format ("[%s] just reached a price of %s\n", tickerSymbol, NumberFormat.getCurrencyInstance().format(newPrice));
        }
    }

    @Override
    public void onError(CqEvent aCqEvent) {

    }

    @Override
    public void close() {

    }
}