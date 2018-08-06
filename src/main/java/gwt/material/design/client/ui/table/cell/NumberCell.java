/*
 * #%L
 * GwtMaterial
 * %%
 * Copyright (C) 2015 - 2017 GwtMaterialDesign
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
package gwt.material.design.client.ui.table.cell;

import com.google.gwt.cell.client.AbstractSafeHtmlCell;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.text.shared.SafeHtmlRenderer;
import com.google.gwt.text.shared.SimpleSafeHtmlRenderer;

/**
 * A {@link com.google.gwt.cell.client.Cell} used to render numbers.
 *
 * @author Ben Dol
 */
public class NumberCell<T extends Number> extends AbstractSafeHtmlCell<T> {

    /**
     * Constructs a TextCell that uses a {@link SimpleSafeHtmlRenderer} to render
     * its text.
     */
    public NumberCell() {
        super(new SafeHtmlRenderer<T>() {
            @Override
            public SafeHtml render(T object) {
                return (object == null) ? SafeHtmlUtils.EMPTY_SAFE_HTML : SafeHtmlUtils.fromString(object.toString());
            }

            @Override
            public void render(T object, SafeHtmlBuilder builder) {
                builder.append(SafeHtmlUtils.fromString(object.toString()));
            }
        });
    }

    /**
     * Constructs a TextCell that uses the provided {@link SafeHtmlRenderer} to
     * render its text.
     *
     * @param renderer a {@link SafeHtmlRenderer SafeHtmlRenderer<String>} instance
     */
    public NumberCell(SafeHtmlRenderer<T> renderer) {
        super(renderer);
    }

    @Override
    protected void render(Context context, SafeHtml data, SafeHtmlBuilder sb) {
        if (data != null) {
            sb.append(data);
        }
    }
}
