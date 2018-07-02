package com.dev.salatiel.homelibrary.ActivityBase;

import com.dev.salatiel.interfaces.IBaseFormActivity;
import com.dev.salatiel.interfaces.IEntityModel;

public abstract class BaseFormActivity<T extends IEntityModel> extends BaseActivity implements IBaseFormActivity<T> {
    protected long idModel = 0;
}
