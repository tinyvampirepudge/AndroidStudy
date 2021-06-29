package com.tinytongtong.androidstudy.manager.dialoglevel.core;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * @Description: IInfoBusDialogLevel的代理类，将单个对话框和优先级管理器连接起来
 * 链接{@link IDialogLevel}与{@link DialogLevelMng}
 * @Author tinytongtong
 * @Date 2021/6/2 8:40 PM
 */
public abstract class DialogLevelWrapper {

    private IDialogLevel mLevel;

    /**
     * 当前对话框，数据是否已经准备好。数据准备好是判断是否能展示的先决条件。
     * 比如展示条件依赖于接口的话，需要等待接口数据返回。
     */
    private boolean isReady;

    /**
     * 当前对话框，是否满足展示的条件。
     * 只有真正可以展示的时候，这个方法才能置为true。
     */
    private boolean canShow;

    private PropertyChangeSupport changes = new PropertyChangeSupport(this);

    public DialogLevelWrapper(IDialogLevel iDialogLevel) {
        this.mLevel = iDialogLevel;
        attachToLevel(iDialogLevel);
    }

    /**
     * 优先级，大于0的整数。数字越大，优先级越低。
     * 1 > 2 > 3 > 4 ...
     *
     * @return
     */
    public abstract int priority();

    public void attachToLevel(IDialogLevel mLevel) {
        this.mLevel = mLevel;
        if (mLevel != null) {
            mLevel.attachWrapper(this);
        }
    }

    public IDialogLevel getLevel() {
        return mLevel;
    }

    public boolean isReady() {
        return isReady;
    }

    public void setReady(boolean ready) {
        isReady = ready;
    }

    public boolean isCanShow() {
        return canShow;
    }

    public void setCanShow(boolean canShow) {
        this.canShow = canShow;
    }

    public void notifyReadyStatus(boolean canShow) {
        // 状态是否有改变
        boolean readyChanged = !isReady();
        boolean oldReadyValue = isReady();
        setReady(true);
        boolean oldShowValue = this.canShow;
        setCanShow(canShow);
        // 通知更新
        changes.firePropertyChange("isReady", oldReadyValue, isReady());
        // 避免重复更新
        if (!readyChanged) {
            changes.firePropertyChange("canShow", oldShowValue, isCanShow());
        }
    }

    public void show() {
        if (mLevel != null) {
            mLevel.show();
        }
    }

    /**
     * 本次展示过程中没有机会展示，这里可以释放资源。
     */
    public void releaseRes() {
        if (mLevel != null) {
            mLevel.release();
        }
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changes.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changes.removePropertyChangeListener(listener);
    }

    @Override
    public String toString() {
        return "DialogLevelWrapper{" +
                "priority=" + priority() +
                ", isReady=" + isReady +
                ", canShow=" + canShow +
                '}';
    }
}
