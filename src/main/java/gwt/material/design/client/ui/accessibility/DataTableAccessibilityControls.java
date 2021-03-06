/*
 * #%L
 * GwtMaterial
 * %%
 * Copyright (C) 2015 - 2020 GwtMaterialDesign
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
package gwt.material.design.client.ui.accessibility;

import com.google.gwt.event.dom.client.KeyUpEvent;
import gwt.material.design.client.accessibility.AccessibilityControl;
import gwt.material.design.client.data.AbstractDataView;
import gwt.material.design.client.data.component.CategoryComponent;
import gwt.material.design.client.data.component.RowComponent;
import gwt.material.design.client.data.infinite.InfiniteDataView;
import gwt.material.design.client.ui.MaterialToast;
import gwt.material.design.client.ui.pager.MaterialDataPager;
import gwt.material.design.client.ui.table.Table;
import gwt.material.design.client.ui.table.TableHeader;

/**
 * Controls the datatable's accessibility features including it's component focused states.
 */
public class DataTableAccessibilityControls extends AccessibilityControl {

    protected AbstractDataView<?> dataView;
    protected AccessibilityOption option = new AccessibilityOption();
    protected MaterialDataPager<?> dataPager;


    protected DataTableAccessibilityControls() {
    }

    public DataTableAccessibilityControls(AbstractDataView<?> dataView) {
        this.dataView = dataView;
    }

    public DataTableAccessibilityControls(AbstractDataView<?> dataView, AccessibilityOption option) {
        this.dataView = dataView;
        this.option = option;
    }

    public void registerRowControl(RowComponent<?> rowComponent) {
        registerWidget(rowComponent.getWidget(), option.getKeys().getRowTrigger());
    }

    public void registerCategoryControl(CategoryComponent categoryComponent) {
        registerWidget(categoryComponent.getWidget(), option.getKeys().getCategoryTrigger());
    }

    public void registerHeaderControl(TableHeader tableHeader) {
        registerWidget(tableHeader, option.getKeys().getHeaderTrigger());
    }

    //TODO: Work on how we incorporate the accessibility control to infinite data loading.
    public <T> void registerInfiniteDataLoadingControl(InfiniteDataView<T> infiniteDataView) {
        /*registerWidget(infiniteDataView.getTable(), option.getKeys().getInfiniteLoadNext(), event -> infiniteDataView.onVerticalScroll());*/
    }

    public void registerPageControl(MaterialDataPager<?> dataPager) {
        this.dataPager = dataPager;
        Table table = dataView.getTable();
        if (table != null) {
            table.setFocus(true);
            registerWidget(table, option.getKeys().getPageNext(), this::onPageNext);
            registerWidget(table, option.getKeys().getPagePrevious(), this::onPagePrevious);
        }
    }

    protected void onPageNext(KeyUpEvent event) {
        if (dataPager != null) {
            if (event.isShiftKeyDown()) {
                dataPager.lastPage();
            } else {
                dataPager.next();
            }
            dataView.getTable().setFocus(true);
        }
    }

    protected void onPagePrevious(KeyUpEvent event) {
        if (dataPager != null) {
            if (event.isShiftKeyDown()) {
                dataPager.firstPage();
            } else {
                dataPager.previous();
            }
            dataView.getTable().setFocus(true);
        }
    }
}
