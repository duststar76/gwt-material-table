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
package gwt.material.design.client.data;

import gwt.material.design.client.base.constants.TableCssName;
import gwt.material.design.client.ui.MaterialIcon;
import gwt.material.design.client.ui.table.TableHeader;
import gwt.material.design.client.ui.table.cell.Column;

/**
 * @author Ben Dol
 */
public class SortContext<T> {
    private TableHeader tableHeader;
    protected Column<T, ?> sortColumn;
    protected SortDir sortDir = SortDir.ASC;
    protected boolean sorted;

    protected SortContext(SortContext<T> sortContext) {
        if(sortContext != null) {
            tableHeader = sortContext.tableHeader;
            sortColumn = sortContext.sortColumn;
            sortDir = sortContext.sortDir;
            sorted = sortContext.sorted;
        }
    }

    protected SortContext(Column<T, ?> sortColumn, TableHeader tableHeader) {
        this.sortColumn = sortColumn;
        if(!this.sortColumn.defaultSortAscending()) {
            sortDir = SortDir.DESC;
        }
        setTableHeader(tableHeader);
    }

    public Column<T, ?> getSortColumn() {
        return sortColumn;
    }

    public void setSortDir(SortDir sortDir) {
        this.sortDir = sortDir;
    }

    public SortDir getSortDir() {
        return sortDir;
    }

    protected void setSortColumn(Column<T, ?> sortColumn) {
        reset();
        this.sortColumn = sortColumn;

        if(sortColumn != null && !sortColumn.defaultSortAscending()) {
            sortDir = SortDir.DESC;
        } else {
            sortDir = SortDir.ASC;
        }
    }

    protected void setTableHeader(TableHeader tableHeader) {
        if(tableHeader.getSortIcon() == null) {
            tableHeader.setSortIcon(new MaterialIcon());
        }
        this.tableHeader = tableHeader;
    }

    public TableHeader getTableHeader() {
        return tableHeader;
    }

    protected void reverse() {
        sortDir = sortDir.reverse();
    }

    protected void reset() {
        tableHeader.removeSortIcon();
        tableHeader.removeStyleName(TableCssName.SELECTED);
        sortDir = SortDir.ASC;
        sortColumn = null;
    }

    public boolean isSorted() {
        return sorted;
    }

    public void setSorted(boolean sorted) {
        this.sorted = sorted;
    }
}
