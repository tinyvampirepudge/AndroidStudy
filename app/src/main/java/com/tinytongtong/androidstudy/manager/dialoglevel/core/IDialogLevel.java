package com.tinytongtong.androidstudy.manager.dialoglevel.core;

/**
 * @Description: 对话框层级的抽象接口
 * 用户对话框展示之前的优先级管理
 * @Author tinytongtong
 * @Date 2021/6/3 10:15 AM
 */
public interface IDialogLevel {
    /**
     * 当前对话框符合展示条件了。
     */
    void show();

    /**
     * {@link IDialogLevel}的实现类，必须持有{@link DialogLevelWrapper}的引用。
     * 这样才能调用Wrapper中的通知逻辑。
     *
     * @param wrapper
     */
    void attachWrapper(DialogLevelWrapper wrapper);

    /**
     * 允许没有展示机会的低优先级对话框释放资源
     */
    void release();
}
