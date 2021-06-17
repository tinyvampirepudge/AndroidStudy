package com.tinytongtong.androidstudy.manager.dialoglevel.core;

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

    private Comparator<DialogLevelWrapper> comparator = new Comparator<DialogLevelWrapper>() {
        @Override
        public int compare(DialogLevelWrapper o1, DialogLevelWrapper o2) {
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
    private List<DialogLevelWrapper> list = new ArrayList<>();

    public void add(DialogLevelWrapper level) {
        if (level == null || list.contains(level)) {
            return;
        }
        list.add(level);
        Collections.sort(list, comparator);
    }

    public boolean isFinished() {
        return isFinished;
    }

    private void setFinished(boolean finished) {
        isFinished = finished;
    }

    public void updateLevelStatus(DialogLevelWrapper level) {
        System.out.printf("updateLevelStatus level: ", getLevelInfo(level));
        if (level == null) {
            return;
        }

        if (!list.contains(level)) {
            System.out.println("updateLevelStatus return level not added, " + getLevelInfo(level));
            return;
        }

        if (isFinished()) {
            System.out.println("updateLevelStatus return isFinished(), " + getLevelInfo(level));
            return;
        }

        checkTopLevel();
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
        while (list != null && !list.isEmpty() && !isFinished()) {
//            DialogLevelWrapper top = queue.peek();
            DialogLevelWrapper top = list.get(0);
            // 剔除空数据，避免空指针
            if (top == null) {
                System.out.println("checkTopLevel 剔除空数据，避免空指针, " + getLevelInfo(top));
                if (!list.isEmpty()) {
                    list.remove(0);
                }
            } else {
                // 优先级最高的弹窗，数据已经准备好
                if (top.isReady()) {
                    // 满足展示条件
                    if (top.isCanShow()) {
                        System.out.println("checkTopLevel 优先级最高的弹窗，数据已经准备好，满足展示条件, " + getLevelInfo(top));
                        setFinished(true);
                        top.show();
                        if (!list.isEmpty()) {
                            list.remove(0);
                        }
                        notifyUnFinishedDialogs();
                    } else { // 不满足展示条件，任务出栈，准备看下一个优先级的任务
                        System.out.println("checkTopLevel 优先级最高的弹窗，数据已经准备好，不满足展示条件, " + getLevelInfo(top));
                        if (!list.isEmpty()) {
                            list.remove(0);
                        }
                    }
                } else {
                    // no-op 栈顶的未准备好，对低优先级的任务进行判断会毫无意义，所以直接结束即可
                    System.out.println(String.format("checkTopLevel 栈顶的未准备好，对低优先级的任务进行判断会毫无意义，所以直接结束即可, " + getLevelInfo(top)));
                    break;
                }
            }
        }
    }

    /**
     * 通知未完成的任务
     */
    private void notifyUnFinishedDialogs() {
        if (list != null && !list.isEmpty() && isFinished()) {
            List<DialogLevelWrapper> listCopy = new ArrayList<>();
            listCopy.addAll(list);
            while (listCopy != null && !listCopy.isEmpty() && isFinished()) {
                DialogLevelWrapper top = listCopy.get(0);
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
        for (DialogLevelWrapper item : list) {
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
}

