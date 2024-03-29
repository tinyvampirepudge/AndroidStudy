package com.tinytongtong.androidstudy.manager.dialoglevel.core;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @Description: 对话框优先级的管理类。
 * 链接{@link IDialogLevel}与{@link DialogLevelWrapper}
 * @Author tinytongtong
 * @Date 2021/6/2 8:01 PM
 */
public class DialogLevelMng {
    /**
     * 是否已经有高优先级的对话框符合展示条件。
     */
    private boolean isFinished;

    private final Comparator<CustomPropertyChangeListener> listenerComparator = new Comparator<CustomPropertyChangeListener>() {
        @Override
        public int compare(CustomPropertyChangeListener o1, CustomPropertyChangeListener o2) {
            /**
             * 负数、0、正数
             * 小于、等于、大于
             */
            return o1.priority() - o2.priority();
        }
    };
    /**
     * 优先级队列
     */
    private final List<CustomPropertyChangeListener> listenerList = new ArrayList<>();

    public void add(DialogLevelWrapper level) {
        if (level == null || containsLevel(listenerList, level)) {
            return;
        }
        CustomPropertyChangeListener propertyChangeListener = new CustomPropertyChangeListener(level) {

            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                System.out.println("propertyChangeListener propertyChange: %s" + getLevelInfo(level));
                updateLevelStatus(getLevel());
            }
        };
        level.addPropertyChangeListener(propertyChangeListener);
        listenerList.add(propertyChangeListener);
        Collections.sort(listenerList, listenerComparator);
    }

    public boolean isFinished() {
        return isFinished;
    }

    private void setFinished(boolean finished) {
        isFinished = finished;
    }

    public void updateLevelStatus(DialogLevelWrapper level) {
        System.out.println("updateLevelStatus level: " + getLevelInfo(level));
        if (level == null) {
            return;
        }

        if (!containsLevel(listenerList, level)) {
            System.out.println("updateLevelStatus return level not added, " + getLevelInfo(level));
            return;
        }

        if (isFinished()) {
            System.out.println("updateLevelStatus return isFinished(), " + getLevelInfo(level));
            return;
        }

        checkTopLevel();
    }

    private boolean containsLevel(List<CustomPropertyChangeListener> listeners, DialogLevelWrapper level) {
        for (CustomPropertyChangeListener listener : listeners) {
            if (listener != null && listener.getLevel() == level) {
                return true;
            }
        }
        return false;
    }

    private void checkTopLevel() {
        System.out.println("checkTopLevel");
        if (isFinished()) {
            System.out.println("checkTopLevel return isFinished()");
            return;
        }
        /*
         * ——如果队列为空，就直接return，无需进行后续操作。
         * ——如果队列不为空，先判断队列中优先级最高的元素。
         *      ——如果最高优先级的元素没有ready，则不能退出流程，接着等待下一次更新。
         *      ——如果最高优先级的元素已经ready：
         *          ——如果最高优先级的元素的canShow为true，则展示最高优先级
         *          ——如果最高优先级的元素的canShow为false，表示最高优先级的没机会展示，则将无展示机会的对话框从
         *            队列中移除。接着走下一次判断，尝试查看下一个优先级最高的元素。
         */
        while (listenerList != null && !listenerList.isEmpty() && !isFinished()) {
            DialogLevelWrapper top = listenerList.get(0) != null ? listenerList.get(0).getLevel() : null;
            // 剔除空数据，避免空指针
            if (top == null) {
                System.out.println("checkTopLevel 剔除空数据，避免空指针, " + getLevelInfo(top));
                removeTop();
            } else {
                // 优先级最高的弹窗，数据已经准备好
                if (top.isReady()) {
                    // 满足展示条件
                    if (top.isCanShow()) {
                        System.out.println("checkTopLevel 优先级最高的弹窗，数据已经准备好，满足展示条件, " + getLevelInfo(top));
                        setFinished(true);
                        top.show();
                        removeTop();
                        notifyUnFinishedDialogs();
                    } else { // 不满足展示条件，任务出栈，准备看下一个优先级的任务
                        System.out.println("checkTopLevel 优先级最高的弹窗，数据已经准备好，不满足展示条件, " + getLevelInfo(top));
                        removeTop();
                    }
                } else {
                    // no-op 栈顶的未准备好，对低优先级的任务进行判断会毫无意义，所以直接结束即可
                    System.out.println(String.format("checkTopLevel 栈顶的未准备好，对低优先级的任务进行判断会毫无意义，所以直接结束即可, " + getLevelInfo(top)));
                    break;
                }
            }
        }
    }

    private void removeTop() {
        if (!listenerList.isEmpty()) {
            CustomPropertyChangeListener removed = listenerList.remove(0);
            if (removed != null && removed.getLevel() != null) {
                removed.getLevel().removePropertyChangeListener(removed);
            }
        }
    }

    /**
     * 通知未完成的任务
     */
    private void notifyUnFinishedDialogs() {
        if (listenerList != null && !listenerList.isEmpty() && isFinished()) {
            List<CustomPropertyChangeListener> listCopy = new ArrayList<>();
            listCopy.addAll(listenerList);
            while (listCopy != null && !listCopy.isEmpty() && isFinished()) {
                DialogLevelWrapper top = listCopy.get(0) != null ? listCopy.get(0).getLevel() : null;
                // 剔除空数据，避免空指针
                if (top != null) {
                    top.releaseRes();
                }
                if (!listCopy.isEmpty()) {
                    listCopy.remove(0);
                }
            }
        }
    }


    /**
     * 释放资源
     */
    public void release() {
        setFinished(false);
    }

    /**
     * 测试方法，可移除
     */
    public void log() {
        System.out.println("_________________log start_________________");
        for (CustomPropertyChangeListener item : listenerList) {
            System.out.println(item);
        }
        System.out.println("_________________log end_________________");
    }

    public static String getLevelInfo(DialogLevelWrapper wrapper) {
        if (wrapper != null) {
            return String.format("wrapper:%s, priority:%s, isReady:%s, isCanShow:%s", wrapper.getClass().getSimpleName(), wrapper.priority(),
                    wrapper.isReady(), wrapper.isCanShow());
        }
        return "";
    }

    private static abstract class CustomPropertyChangeListener implements PropertyChangeListener {
        private DialogLevelWrapper level;

        public CustomPropertyChangeListener(DialogLevelWrapper level) {
            this.level = level;
        }

        public DialogLevelWrapper getLevel() {
            return level;
        }

        public int priority() {
            if (level != null) {
                return level.priority();
            }
            return -1;
        }

        @Override
        public String toString() {
            return "CustomPropertyChangeListener{" +
                    "priority=" + priority() +
                    '}';
        }
    }
}

