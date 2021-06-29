package com.tinytongtong.androidstudy.manager.dialoglevel.core;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * @Description: TODO
 * @Author wangjianzhou
 * @Date 2021/6/29 3:15 PM
 * @Version TODO
 */
public class PropertyChangeSupportTest {
    private boolean flag;

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public static void main(String[] args) {
        PropertyChangeSupportTest test = new PropertyChangeSupportTest();
        PropertyChangeSupport changes = new PropertyChangeSupport(test);
        changes.addPropertyChangeListener(new PropertyChangeListener() {

            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                System.out.println("change");
            }
        });
        boolean oldValue = test.isFlag();
        test.setFlag(!oldValue);
        changes.firePropertyChange("flag", oldValue, !oldValue);
    }
}
