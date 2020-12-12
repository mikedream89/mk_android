package com.joybar.compiler;

import com.google.auto.service.AutoService;
import com.joybar.annotation.RegisterRouter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import javax.tools.JavaFileObject;

@AutoService(Processor.class)
public class RouterProcessor extends AbstractProcessor {
    private Messager mMessager;
    private Filer mFiler;
    private Map<RouterModel, String> mStaticRouterMap = new HashMap<>();

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        mFiler = processingEnv.getFiler();
        mMessager = processingEnv.getMessager();
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        mStaticRouterMap.clear();
        for (TypeElement element : annotations) {
            if (element.getQualifiedName().toString().equals(RegisterRouter.class.getCanonicalName())) {
                processRouterMap1(element, roundEnv);
            }
        }
        return false;
    }

    /**
     * 声明注解处理器要处理的注解
     *
     * @return
     */
    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> set = new HashSet<>();
        set.add(RegisterRouter.class.getCanonicalName());
        return set;
    }

    /**
     * 声明注解处理器支持的java版本
     *
     * @return
     */
    @Override
    public SourceVersion getSupportedSourceVersion() {
        return processingEnv.getSourceVersion();
    }


    private void processRouterMap1(TypeElement element, RoundEnvironment roundEnv) {
        Set<? extends Element> routerElements = roundEnv.getElementsAnnotatedWith(RegisterRouter.class);
        for (Element e : routerElements) {
            if (!(e instanceof TypeElement)) {
                continue;
            }
            TypeElement typeElement = (TypeElement) e;
            String module = typeElement.getAnnotation(RegisterRouter.class).module();
            String path = typeElement.getAnnotation(RegisterRouter.class).path();
            String classFullName = typeElement.getQualifiedName().toString();
            mMessager.printMessage(Diagnostic.Kind.NOTE, "类名和包名：" + classFullName);
            mMessager.printMessage(Diagnostic.Kind.NOTE, "path(注解中的path):" + path);
            mMessager.printMessage(Diagnostic.Kind.NOTE, "module名称:" + module);

            if (mStaticRouterMap.get(new RouterModel(module, path)) == null) {
                mStaticRouterMap.put(new RouterModel(module, path), classFullName);
            }
        }
        writeComponentFile();
    }

    private void writeComponentFile() {
        for (Map.Entry<RouterModel, String> entry : mStaticRouterMap.entrySet()) {
            String module = entry.getKey().module;
            String path = entry.getKey().path;
            String className = entry.getValue();
            String createClassName = className.replace(".", "_") + Config.ROUTER_MANAGER_CLASS_NAME_SUFFIX;

            JavaFileObject javaFileObject = null;
            try {
                javaFileObject = mFiler.createSourceFile(createClassName);
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
            PrintWriter printWriter = null;
            try {
                printWriter = new PrintWriter(javaFileObject.openWriter());
                printWriter.println("package " + Config.ROUTER_MANAGER_PKN + ";");
                printWriter.println("import android.app.Activity;");
                printWriter.println("import android.app.Service;");
                printWriter.println("import android.content.BroadcastReceiver;");
                printWriter.println("import com.joybar.librouter.IRouter;");
                printWriter.println("public class " + createClassName + " implements IRouter {");
                printWriter.println("@Override");
                printWriter.println("public void " + Config.ROUTER_MANAGER_METHOD_NAME + "() {");
                printWriter.println("com.joybar.librouter.Router.registerRouter"
                        + "(\"" + module
                        + "\", "
                        + "\"" + path
                        + "\", "
                        + className + ".class"
                        + ");");

                printWriter.println("}");
                printWriter.println("}");
                printWriter.flush();
                printWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
                return;
            } finally {
                if (printWriter != null) {
                    printWriter.flush();
                    printWriter.close();
                }
            }

        }
    }

    private void writeComponentFileJavapoet(){

    }

    public static class RouterModel {
        String module;
        String path;

        public RouterModel(String module, String path) {
            this.module = module;
            this.path = path;
        }

        @Override
        public int hashCode() {
            return path.hashCode() + module.hashCode();
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof RouterModel) {
                RouterModel routerModel = (RouterModel) obj;
                return (path.equals(routerModel.path)
                        && module.equals(routerModel.module));
            }
            return super.equals(obj);
        }
    }
}
