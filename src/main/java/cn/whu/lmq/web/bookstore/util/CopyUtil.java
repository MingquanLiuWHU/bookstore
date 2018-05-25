package cn.whu.lmq.web.bookstore.util;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

/**
 * @author LMQ 提供通用的复制字段的方法
 */
public class CopyUtil {
    /**
     * 将一个类对象的非空字段（类本身声明的字段）复制到相同类对象 浅拷贝，只复制引用
     *
     * @param source           提供值的对象
     * @param target           接受值的对象 target或者和source同类型，或者是source的子类型
     * @param ignoreProperties 忽略复制的字段名称
     */
    public static <T> void copyNotNullFields(T source, T target, String... ignoreProperties) {
        checkInput(source, target);
        List<String> properties = Arrays.asList(ignoreProperties);
        Field[] fields = source.getClass().getDeclaredFields();
        try {
            for (Field field : fields) {
                if (properties.contains(field.getName())) {
                    continue;
                }
                field.setAccessible(true);

                Object value = field.get(source);
                if (value != null) {
                    field.set(target, value);
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }

    /**
     * 将一个类对象的非空字段（类本身声明的字段）复制到相同类对象 浅拷贝，只复制引用
     *
     * @param source     提供值的对象
     * @param target     接受值的对象 target或者和source同类型，或者是source的子类型
     * @param properties 需要复制的字段名称
     */
    public static <T> void copyFields(T source, T target, String... properties) {
        checkInput(source, target);
        List<String> propertiesList = Arrays.asList(properties);
        Field[] fields = source.getClass().getDeclaredFields();
        try {
            for (Field field : fields) {
                if (!propertiesList.contains(field.getName())) {
                    continue;
                }
                field.setAccessible(true);
                Object value = field.get(source);
                field.set(target, value);

            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private static <T> void checkInput(T source, T target) {
        if (source == null || target == null) {
            throw new NullPointerException("source和target不能为空");
        }
		if (!source.getClass().isInstance(target)) {
			throw new IllegalArgumentException("source和target必须为相同的类型");
		}

    }
}
