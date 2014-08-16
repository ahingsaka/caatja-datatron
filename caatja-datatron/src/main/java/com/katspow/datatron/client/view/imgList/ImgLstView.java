package com.katspow.datatron.client.view.imgList;

import java.util.Comparator;
import java.util.List;

import com.google.gwt.cell.client.SafeHtmlCell;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
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
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;
import com.katspow.datatron.client.Datatron;
import com.katspow.datatron.client.api.DatatronService;
import com.katspow.datatron.client.api.DatatronServiceAsync;
import com.katspow.datatron.client.utils.GridResources;
import com.katspow.datatron.shared.ImageDto;

public class ImgLstView extends Composite {

    private static ImgLstViewUiBinder uiBinder = GWT.create(ImgLstViewUiBinder.class);

    interface ImgLstViewUiBinder extends UiBinder<Widget, ImgLstView> {
    }

    private static final DatatronServiceAsync dataService = GWT.create(DatatronService.class);

    @UiField(provided = true)
    DataGrid<ImageDto> imgLst;

    @UiField(provided = true)
    SimplePager pager;

    @UiField
    Image preview;
    
    @UiField
    DockLayoutPanel dock;
    
    private ListDataProvider<ImageDto> dataProvider = new ListDataProvider<ImageDto>();

    public ImgLstView() {
        
        imgLst = new DataGrid<ImageDto>(10, (Resources) GWT.create(GridResources.class), ImageDto.KEY_PROVIDER);
        imgLst.setAutoHeaderRefreshDisabled(true);
        imgLst.setEmptyTableWidget(new Label("No item"));
        
     // Attach a column sort handler to the ListDataProvider to sort
        // the
        // list.
        ListHandler<ImageDto> sortHandler = new ListHandler<ImageDto>(dataProvider.getList());
        
        imgLst.addColumnSortHandler(sortHandler);

        // Create a Pager to control the table.
        SimplePager.Resources pagerResources = GWT.create(SimplePager.Resources.class);
        pager = new SimplePager(TextLocation.CENTER, pagerResources, false, 0, true);
        pager.setDisplay(imgLst);

        // Add a selection model so we can select cells.
        // final SelectionModel<ImageDto> selectionModel = new
        // MultiSelectionModel<ContactInfo>(
        // ContactDatabase.ContactInfo.KEY_PROVIDER);
        // dataGrid.setSelectionModel(selectionModel,
        // DefaultSelectionEventManager.<ContactInfo> createCheckboxManager());

        // Add a selection model so we can select cells.
        final SingleSelectionModel<ImageDto> selectionModel = new SingleSelectionModel<ImageDto>(ImageDto.KEY_PROVIDER);

        imgLst.setSelectionModel(selectionModel);
        selectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
            public void onSelectionChange(SelectionChangeEvent event) {
                ImageDto selectedObject = selectionModel.getSelectedObject();
                String base64Data = "data:image/png;base64," + selectedObject.getImageData();
                preview.setUrl(base64Data);
            }
        });

        initTableColumns(selectionModel, sortHandler);
        
        dataProvider.addDataDisplay(imgLst);

        initWidget(uiBinder.createAndBindUi(this));
        
        dock.setHeight("500px");
        dock.setWidth("100%");

        dataService.findAllResources(Datatron.getSelectedApplication().getId(), new AsyncCallback<List<ImageDto>>() {
            @Override
            public void onSuccess(List<ImageDto> result) {
                imgLst.setRowData(result);
                dataProvider.setList(result);
            }

            @Override
            public void onFailure(Throwable caught) {
                // TODO Auto-generated method stub
            }
        });

    }

    private void initTableColumns(SingleSelectionModel<ImageDto> selectionModel, ListHandler<ImageDto> sortHandler) {

        // First name.
        Column<ImageDto, String> firstNameColumn = new Column<ImageDto, String>(new TextCell()) {
            @Override
            public String getValue(ImageDto object) {
                return object.getName();
            }
        };
        
        firstNameColumn.setSortable(true);
        
        imgLst.addColumn(firstNameColumn, "Name");
        
        sortHandler.setComparator(firstNameColumn, new Comparator<ImageDto>() {
            @Override
            public int compare(ImageDto o1, ImageDto o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
        
        Column<ImageDto, String> dimCol = new Column<ImageDto, String>(new TextCell()) {
            @Override
            public String getValue(ImageDto object) {
                return object.getWidth() + " x " + object.getHeight();
            }
        };
        
        imgLst.addColumn(dimCol, "Dimensions");
        
        Column<ImageDto, SafeHtml> zoomCol = new Column<ImageDto, SafeHtml>(new SafeHtmlCell()) {
            @Override
            public SafeHtml getValue(ImageDto object) {
                SafeHtmlBuilder sb = new SafeHtmlBuilder();
                sb.appendHtmlConstant("<input type='image' src='images/icn_search.png' title='Search' />");
                return sb.toSafeHtml();
            }
        };
        
        imgLst.addColumn(zoomCol, "Zoom");
        
        Column<ImageDto, SafeHtml> deleteCol = new Column<ImageDto, SafeHtml>(new SafeHtmlCell()) {
            @Override
            public SafeHtml getValue(ImageDto object) {
                SafeHtmlBuilder sb = new SafeHtmlBuilder();
                sb.appendHtmlConstant("<input type='image' src='images/icn_trash.png' title='Trash' />");
                return sb.toSafeHtml();
            }
        };
        
        imgLst.addColumn(deleteCol, "Delete");
        
//        firstNameColumn.setFieldUpdater(new FieldUpdater<ImageDto, String>() {
//            @Override
//            public void update(int index, ImageDto object, String value) {
//                // Called when the user changes the value.
//                object.setName(name);
//                object.setFirstName(value);
//                ContactDatabase.get().refreshDisplays();
//            }
//        });
        
        imgLst.setColumnWidth(firstNameColumn, 50, Unit.PCT);
        imgLst.setColumnWidth(dimCol, 20, Unit.PCT);
        imgLst.setColumnWidth(zoomCol, 15, Unit.PCT);
        imgLst.setColumnWidth(deleteCol, 15, Unit.PCT);

    }

}
