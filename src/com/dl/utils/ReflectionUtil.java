package com.dl.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 
* 文件名称：ReflectionUtil.java<br>
* 摘要：实体属性映射工具类<br>
* -------------------------------------------------------<br>
* 作者：胡毅<br>
* 完成日期：2015年7月23日<br>
 */
public class ReflectionUtil<T> {

	private List<String> fieldName =  new ArrayList<String>();

	
	public List<String> getFieldName() {
		return fieldName;
	}

	public void getFeilds(T model){
		// 获取实体类的所有属性，返回Field数组
		Field[] field = model.getClass().getDeclaredFields();
	    try {
	        for (int i = 0; i < field.length; i++) { // 遍历所有属性
	            String name = field[i].getName(); // 获取属性的名字
	            fieldName.add(name);
	            name = name.substring(0, 1).toUpperCase() + name.substring(1); // 将属性的首字符大写，方便构造get，set方法
	            String type = field[i].getGenericType().toString(); // 获取属性的类型
	            if (type.equals("class java.lang.String")) { // 如果type是类类型，则前面包含"class "，后面跟类名
	                Method m = model.getClass().getMethod("get" + name);
	                String value = (String) m.invoke(model); // 调用getter方法获取属性值
	                if (value == null) {
	                    m = model.getClass().getMethod("set"+name,String.class);
	                    m.invoke(model, "");
	                }
	            }
	            if (type.equals("class java.lang.Integer")) {
	                Method m = model.getClass().getMethod("get" + name);
	                Integer value = (Integer) m.invoke(model);
	                if (value == null) {
	                    m = model.getClass().getMethod("set"+name,Integer.class);
	                    m.invoke(model, 0);
	                }
	            }
	            if (type.equals("class java.lang.Boolean")) {
	                Method m = model.getClass().getMethod("get" + name);
	                Boolean value = (Boolean) m.invoke(model);
	                if (value == null) {
	                    m = model.getClass().getMethod("set"+name,Boolean.class);
	                    m.invoke(model, false);
	                }
	            }
	            if (type.equals("class java.util.Date")) {
	                Method m = model.getClass().getMethod("get" + name);
	                Date value = (Date) m.invoke(model);
	                if (value == null) {
	                    m = model.getClass().getMethod("set"+name,Date.class);
	                    m.invoke(model, new Date());
	                }
	            }// 如果有需要,可以仿照上面继续进行扩充,再增加对其它类型的判断
	        }
	    } catch (NoSuchMethodException e) {
	        e.printStackTrace();
	    } catch (SecurityException e) {
	        e.printStackTrace();
	    } catch (IllegalAccessException e) {
	        e.printStackTrace();
	    } catch (IllegalArgumentException e) {
	        e.printStackTrace();
	    } catch (InvocationTargetException e) {
	        e.printStackTrace();
	    }
	}
}
