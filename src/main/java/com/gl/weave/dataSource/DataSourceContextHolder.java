package com.gl.weave.dataSource;


/**
 * Created by pure on 2018-05-06.
 */
public class DataSourceContextHolder {
    
 
    private static final ThreadLocal<DataSourceKey> contextHolder = new ThreadLocal<>();
 
    /**
     * 清除当前数据源
     */
    public static void clear() {
    	contextHolder.remove();
    }

    /**
     * 获取当前使用的数据源
     *
     * @return 当前使用数据源的ID
     */
    public static DataSourceKey get() {
        return contextHolder.get();
    }

    /**
     * 设置当前使用的数据源
     *
     * @param value 需要设置的数据源ID
     */
    public static void set(DataSourceKey value) {
    	contextHolder.set(value);
    }

    /**
     * 设置从从库读取数据
     * 采用简单生成随机数的方式切换不同的从库
     */
    public static void setSlave() {
        if (Math.random() > 0) {
        	DataSourceContextHolder.set(DataSourceKey.DB_SLAVE2);
        } else {
        	DataSourceContextHolder.set(DataSourceKey.DB_SLAVE1);
        }
    }

}
