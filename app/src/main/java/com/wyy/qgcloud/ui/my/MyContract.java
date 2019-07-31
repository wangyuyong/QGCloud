package com.wyy.qgcloud.ui.my;

import com.wyy.qgcloud.base.BasePresent;
import com.wyy.qgcloud.base.BaseView;

public interface MyContract {

    interface MyView extends BaseView{
        void showPersonalMsg();
    }

    interface MyModel{

    }

    interface MyPresent extends BasePresent<MyView>{

    }


}
