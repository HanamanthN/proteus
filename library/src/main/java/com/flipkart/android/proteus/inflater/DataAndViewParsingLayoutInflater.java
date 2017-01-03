/*
 * Apache License
 * Version 2.0, January 2004
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * TERMS AND CONDITIONS FOR USE, REPRODUCTION, AND DISTRIBUTION
 *
 * Copyright (c) 2017 Flipkart Internet Pvt. Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use
 * this file except in compliance with the License. You may obtain a copy of the
 * License at http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed
 * under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR
 * CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */

package com.flipkart.android.proteus.inflater;

import android.support.annotation.NonNull;
import android.view.View;

import com.flipkart.android.proteus.Layout;
import com.flipkart.android.proteus.toolbox.IdGenerator;
import com.flipkart.android.proteus.view.ProteusView;

import java.util.Map;

/**
 * A layout builder which can parse @data and @view blocks before passing it on to
 * {@link SimpleLayoutInflater}
 */
public class DataAndViewParsingLayoutInflater extends DataParsingLayoutInflater {

    private Map<String, Layout> layouts;

    protected DataAndViewParsingLayoutInflater(Map<String, Layout> layouts, @NonNull IdGenerator idGenerator) {
        super(idGenerator);
        this.layouts = layouts;
    }

    @Override
    public Layout onIncludeLayout(String type, Layout include) {
        Layout layout = null;
        if (layouts != null) {
            layout = layouts.get(type);
        }
        return null != layout ? layout : super.onIncludeLayout(type, include);
    }

    private void onViewBuiltFromViewProvider(ProteusView view, String type, View parent, int childIndex) {
        if (listener != null) {
            listener.onViewBuiltFromViewProvider(view, parent, type, childIndex);
        }
    }

    public void setLayouts(Map<String, Layout> layouts) {
        this.layouts = layouts;
    }
}
