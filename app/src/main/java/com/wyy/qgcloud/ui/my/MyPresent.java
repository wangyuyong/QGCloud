package com.wyy.qgcloud.ui.my;

public class MyPresent implements MyContract.MyPresent {

    private MyContract.MyModel myModel;
    private MyContract.MyView myView;

    @Override
    public void bindView(MyContract.MyView view) {
        this.myView = view;
        myModel = new MyModel();
    }

    @Override
    public void unbindView() {
        myModel = null;
        myView = null;
    }
}
