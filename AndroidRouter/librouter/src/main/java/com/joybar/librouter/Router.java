package com.joybar.librouter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Router {

    public static Map<Rule, Rule> ruleMap = new HashMap<>();

    public static void registerRouter(String module, String path, Class clazz) {
        Rule rule = new Rule(module, path, clazz);
        ruleMap.put(rule, rule);
    }


    public static void registerRouters(RouterTable routeTable) {
        List<Rule> rules = routeTable.buildRuleList();
        if (null != rules && rules.size() != 0) {
            for (Rule rule : rules) {
                ruleMap.put(rule, rule);
            }
        }
    }


    public static IRouterManagerService create() {
        RouterService routerService = new RouterService();
        return routerService;
    }


    public interface RouterTable {
        List<Rule> buildRuleList();
    }

//    //注册所有的Activity
//    public static void initRegister(Context context) {
//        String ROUTER_MANAGER_PKN = "com.cn.routermanager.helper";
//        String ROUTER_MANAGER_METHOD_NAME = "registerRouter";
//        List<String> classNameList = getClassName(ROUTER_MANAGER_PKN, context);
//        for (String className : classNameList) {
//            try {
//                className = ROUTER_MANAGER_PKN + "." + className;
//                System.out.println("classFullName=" + className);
//                Class clazz = Class.forName(className);
//                Method method = clazz.getDeclaredMethod(ROUTER_MANAGER_METHOD_NAME);
//                System.out.println("method=" + method);
//                method.invoke(null);
//            } catch (ClassNotFoundException e) {
//                e.printStackTrace();
//            } catch (NoSuchMethodException e) {
//                e.printStackTrace();
//            } catch (InvocationTargetException e) {
//                e.printStackTrace();
//            } catch (IllegalAccessException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    /**
//     * 获取指定包名下 的所有类
//     *
//     * @param packageName
//     * @return
//     */
//    public static List<String> getClassName(String packageName, Context context) {
//        List<String> classNameList = new ArrayList<String>();
//        try {
//            DexFile df = new DexFile(context.getPackageCodePath());//通过DexFile查找当前的APK中可执行文件
//            Enumeration<String> enumeration = df.entries();//获取df中的元素  这里包含了所有可执行的类名 该类名包含了包名+类名的方式
//            while (enumeration.hasMoreElements()) {//遍历
//                String className = (String) enumeration.nextElement();
//
//                if (className.contains(packageName)) {//在当前所有可执行的类里面查找包含有该包名的所有类
//                    classNameList.add(className);
//                }
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return classNameList;
//    }

}
