package com.katspow.datatron.client.view.scores;

import java.util.HashSet;
import java.util.Set;

import com.google.gwt.cell.client.Cell.Context;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.cell.client.SafeHtmlCell;
import com.google.gwt.cell.client.TextInputCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.ColumnSortEvent.ListHandler;
import com.google.gwt.user.cellview.client.DataGrid;
import com.google.gwt.user.cellview.client.DataGrid.Resources;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.SimplePager.TextLocation;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.ListDataProvider;
import com.katspow.datatron.client.utils.GridResources;
import com.katspow.datatron.shared.ScoreDto;

public class ScoresView extends Composite {

    private static ScoresViewUiBinder uiBinder = GWT.create(ScoresViewUiBinder.class);

    interface ScoresViewUiBinder extends UiBinder<Widget, ScoresView> {
    }

    @UiField
    HTML message;

    @UiField(provided = true)
    DataGrid<ScoreDto> scoreLst;

    @UiField(provided = true)
    SimplePager pager;

    @UiField
    DockLayoutPanel dock;

    private ListDataProvider<ScoreDto> dataProvider = new ListDataProvider<ScoreDto>();

    public ScoresView() {

        scoreLst = new DataGrid<ScoreDto>(10, (Resources) GWT.create(GridResources.class));
        scoreLst.setAutoHeaderRefreshDisabled(true);
        scoreLst.setEmptyTableWidget(new Label("No item"));

        // Attach a column sort handler to the ListDataProvider to sort the
        // list.
        ListHandler<ScoreDto> sortHandler = new ListHandler<ScoreDto>(dataProvider.getList());
        scoreLst.addColumnSortHandler(sortHandler);

        // Create a Pager to control the table.
        SimplePager.Resources pagerResources = GWT.create(SimplePager.Resources.class);
        pager = new SimplePager(TextLocation.CENTER, pagerResources, false, 0, true);
        pager.setDisplay(scoreLst);
        
        initTableColumns(sortHandler);
        
        dataProvider.addDataDisplay(scoreLst);

        initWidget(uiBinder.createAndBindUi(this));
        
        dock.setHeight("500px");
        dock.setWidth("100%");
        
        findAllScores();
    }

    private void initTableColumns(ListHandler<ScoreDto> sortHandler) {
        TextColumn<ScoreDto> numColumn = new TextColumn<ScoreDto>() {
            public String getValue(ScoreDto object) {
                return String.valueOf(object.getNumOrder());
            }
        };
        
        scoreLst.addColumn(numColumn, "No");
        
        Column<ScoreDto, String> nameColumn = new Column<ScoreDto, String>(new TextInputCell()) {
            @Override
            public String getValue(ScoreDto object) {
                String pwd = object.getName();
                return pwd == null ? "" : pwd;
            }
        };
        
        scoreLst.addColumn(nameColumn, "Name");
        
        nameColumn.setFieldUpdater(new FieldUpdater<ScoreDto, String>() {
            public void update(int index, ScoreDto object, String value) {
                object.setName(value);;
            }
        });
        
        Column<ScoreDto, String> scoreColumn = new Column<ScoreDto, String>(new TextInputCell()) {
            @Override
            public String getValue(ScoreDto object) {
                String pwd = object.getName();
                return pwd == null ? "" : pwd;
            }
        };
        
        scoreLst.addColumn(scoreColumn, "Score");
        
        scoreColumn.setFieldUpdater(new FieldUpdater<ScoreDto, String>() {
            public void update(int index, ScoreDto object, String value) {
                object.setScore(Integer.parseInt(value));
            }
        });
        
        final SafeHtmlCell progressCell = new SafeHtmlCell() {
            @Override
            public Set<String> getConsumedEvents() {
                HashSet<String> events = new HashSet<String>();
                events.add("click");
                return events;
            }
        };
        
        Column<ScoreDto, SafeHtml> saveCol = new Column<ScoreDto, SafeHtml>(progressCell) {
            public SafeHtml getValue(ScoreDto value) {
                SafeHtmlBuilder sb = new SafeHtmlBuilder();
                sb.appendHtmlConstant("<input type='image' src='images/icn_alert_success.png' title='Save' />");
                return sb.toSafeHtml();
            }

            @Override
            public void onBrowserEvent(Context context, Element elem, ScoreDto object, NativeEvent event) {
                if ("click".equals(event.getType())) {
                   update(object);
                }
            }

        };
        
        scoreLst.addColumn(saveCol, "Save");
        
        
    }

    protected void update(ScoreDto object) {
        // TODO Auto-generated method stub
        
    }

    private void findAllScores() {
        // TODO Auto-generated method stub
        
    }

}
