package com.springclass;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.reflect.ConstructorUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.*;

import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.net.URL;
import java.net.URLDecoder;
import java.util.*;

import static java.lang.System.out;

@RunWith(JUnit4.class)
public final class ValueObjectsGetSetMethodsTests {

    private static final Logger logger = LoggerFactory
            .getLogger(ValueObjectsGetSetMethodsTests.class);

    // TODO: Add all packages to be included in this test:
    // TODO, refactor to allow multiple packages.
    public static final Set<Class> CLASSES_SET = new HashSet();

    static {
        CLASSES_SET.addAll(getClasses("com.springclass.domain"));
    }


    private static final Map<String, Object> reflectionObjTypeMap = new HashMap<>();

    static {

        try {
            reflectionObjTypeMap.put("java.util.List", ArrayList.class.newInstance());
            reflectionObjTypeMap.put("java.util.Map", HashMap.class.newInstance());
            reflectionObjTypeMap.put("java.util.Set", HashSet.class.newInstance());
            reflectionObjTypeMap.put("boolean", Boolean.FALSE);
            reflectionObjTypeMap.put("java.lang.Boolean", Boolean.FALSE);
            reflectionObjTypeMap.put("short", new Short("0"));
            reflectionObjTypeMap.put("java.lang.Short", new Short("0"));
            reflectionObjTypeMap.put("int", new Integer(0));
            reflectionObjTypeMap.put("java.lang.Integer", new Integer(0));
            reflectionObjTypeMap.put("long", new Long(0));
            reflectionObjTypeMap.put("java.lang.Long", new Long(0));
            reflectionObjTypeMap.put("float", new Float(0));
            reflectionObjTypeMap.put("java.lang.Float", new Float(0));
            reflectionObjTypeMap.put("double", new Double(0));
            reflectionObjTypeMap.put("java.lang.Double", new Double(0));
            reflectionObjTypeMap.put("java.math.BigDecimal", new BigDecimal(0));
        } catch (Exception e) {
            logger.error("Exception while initializing the reflectionObjTypeMap.: {}", e.getMessage(), e);
        }
    }

    @Test
    public void testValueObjectMethods_invokeGetSetMethods() {
        try {
            logger.info("No. of classes found: {}", CLASSES_SET.size());
            for (Class clazz : CLASSES_SET) {

                logger.info("Class:  {}", clazz.getCanonicalName());

                final Method[] allMethods = clazz.getDeclaredMethods();

                for (Method method : allMethods) {
                    boolean invokeMethod = false;
                    Object paramForMethodInvocation = null;
                    final String methodName = method.getName();
                    //logger.info("%s%n", methodName);

                    if (!isValidGetOrSetMethod(clazz, method)) {
                        continue;
                    }

                    if ((methodName.startsWith("get") && method.getGenericParameterTypes().length == 0)
                            || (methodName.startsWith("is") && (method.getGenericReturnType() == boolean.class || method.getGenericReturnType() == Boolean.class))) {
                        //logger.info("found getter method %s()%n", methodName);
                        invokeMethod = true;
                    } else if (methodName.startsWith("set")
                            && method.getGenericParameterTypes().length == 1
                            && method.getGenericReturnType() == void.class) {
                        //logger.info("found setter method %s()%n", methodName);
                        invokeMethod = true;

                        Class[] paramsTypeList = method.getParameterTypes();
                        //logger.info("param type is: {}", paramsTypeList[0].getCanonicalName());

                        paramForMethodInvocation = getParamForMethodInvocation(paramsTypeList[0]);
                    }

                    try {
                        if (invokeMethod) {
                            method.setAccessible(true);
                            Object objOfMethodToBeInvoked = null;
                            if (clazz.isEnum()) {
                                Object[] enumConstants = clazz.getEnumConstants();
                                objOfMethodToBeInvoked = enumConstants[0];
                            } else {
                                objOfMethodToBeInvoked = getObjectByReflection(clazz);
                            }

                            Object o = (paramForMethodInvocation == null) ? method.invoke(objOfMethodToBeInvoked)
                                    : method.invoke(objOfMethodToBeInvoked, paramForMethodInvocation);
                            out.format("successfully invoked %s()%n", methodName);
                        }
                    } catch (Exception excp) {
                        logger.error(">>>>>>>>Exception while processing: {}", excp.getMessage());
                        logger.error("Class : {}", clazz.getCanonicalName());
                        logger.error("Method: {}", methodName);
//                        logger.error("", excp);
//                        assert true;

//                        assertTrue(true);// throw new RuntimeException();
                    }

                    //Some useful debug statements
                    //logger.error("{}", m.toGenericString());
                    //logger.error("  Modifiers:  {}", Modifier.toString(m.getModifiers()));
                    //logger.error("  [ synthetic=%-5b var_args=%-5b bridge=%-5b ]%n", m.isSynthetic(), m.isVarArgs(), m.isBridge());
                }

            }
        } catch (Exception excp) {
            logger.error("Exception: {}", excp.getMessage(), excp);
        }
    }

    private static Object getObjectByReflection(final Class<Object> clazz) {
        Object result = null;

        try {
            final Constructor[] allConstructors = clazz.getDeclaredConstructors();
            Class[] paramTypes = null;

            int ctorParamCount = -1;
            Constructor selectedCtor = null;
            for (Constructor constructor : allConstructors) {
                paramTypes = constructor.getParameterTypes();
                if (ctorParamCount == -1 || paramTypes.length < ctorParamCount) {
                    selectedCtor = constructor;
                    ctorParamCount = paramTypes.length;
                    if (paramTypes.length == 0) break; //Found default ctor
                }
            }

            if (ctorParamCount == 0) {
                //Default ctor exists so newInstance can be used
                result = clazz.newInstance();
            } else {
                final List<Object> paramObjects = new ArrayList<>();
                //Loop thru the constructor argument list and construct each one of them.
                for (Class paramTypeClass : paramTypes) {
                    Object obj = getParamForMethodInvocation(paramTypeClass);
                    if (obj != null) {
                        //This argument exists in the basic java list types
                        paramObjects.add(obj);
                    } else {
                        //recursive call here to construct the argument object
                        paramObjects.add(getObjectByReflection(paramTypeClass));
                    }
                }

                result = ConstructorUtils.invokeConstructor(clazz, paramObjects.toArray());
            }

        } catch (Exception excp) {
            //Do nothing here
        }

        return result;
    }

    private static Object getParamForMethodInvocation(final Class<Object> paramType) {
        Object result = null;
        try {
            if (paramType.isEnum()) {
                result = paramType.getEnumConstants()[0];
            } else if (reflectionObjTypeMap.containsKey(paramType.getCanonicalName())) {
                result = reflectionObjTypeMap.get(paramType.getCanonicalName());
            } else {
                result = paramType.newInstance();
            }
        } catch (Exception e) {
            logger.error("Exception while constructing param object for param type: {}", paramType.getCanonicalName(), e);
        }
        return result;
    }

    private static boolean isValidGetOrSetMethod(final Class<Object> clazz, final Method method) {
        boolean result = false;

        String fieldName = method.getName();
        if (fieldName.startsWith("get")) {
            fieldName = StringUtils.uncapitalize(fieldName.replaceFirst("get", ""));
        } else if (fieldName.startsWith("is")) {
            fieldName = StringUtils.uncapitalize(fieldName.replaceFirst("is", ""));
        } else if (fieldName.startsWith("set")) {
            //Skip testing setter methods for an enum as this will update the enum by reference which will cause the
            //tests that come after this JUnit test invoking the getter methods of the enums to FAIL !!!!!!!
            //If necessary write JUnit tests for setters in the corresponding JUnit class with appropriate resets.
            //IMPORTANT: DO NOT REMOVE THIS CHECK !!!!!!!
            if (!clazz.isEnum()) {
                fieldName = StringUtils.uncapitalize(fieldName.replace("set", ""));
            }
        }

        if (StringUtils.isNotBlank(fieldName)) {
            try {
                result = (clazz.getDeclaredField(fieldName) != null);
            } catch (NoSuchFieldException e) {
                //Do nothing and let it go
            }
        }

        return result;
    }

    /**
     * Scans all classes accessible from the context class loader which belong to the given package and subpackages.
     *
     * @param packageName The base package
     * @return The classes
     */
    @SuppressWarnings("unchecked")
    private static List<Class> getClasses(final String packageName) {
        final List<Class> classes = new ArrayList<>();

        try {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            assert classLoader != null;
            String path = packageName.replace('.', '/');
            Enumeration<URL> resources = classLoader.getResources(path);
            List<File> dirs = new ArrayList<>();
            while (resources.hasMoreElements()) {
                URL resource = resources.nextElement();
                String fileName = resource.getFile();
                String fileNameDecoded = URLDecoder.decode(fileName, "UTF-8");
                dirs.add(new File(fileNameDecoded));
            }
            for (File directory : dirs) {
                classes.addAll(findClasses(directory, packageName));
            }
        } catch(ClassNotFoundException | IOException e){
            logger.error("Exception: {}", e.getMessage(), e);
        }
        return classes;
    }

    /**
     * Recursive method used to find all classes in a given directory and subdirs.
     *
     * @param directory   The base directory
     * @param packageName The package name for classes found inside the base directory
     * @return The classes
     * @throws ClassNotFoundException
     */
    @SuppressWarnings("unchecked")
    private static List<Class> findClasses(final File directory, final String packageName)
            throws ClassNotFoundException {
        final List<Class> classes = new ArrayList<>();
        if (!directory.exists()) {
            return classes;
        }
        File[] files = directory.listFiles();
        for (File file : files) {
            String fileName = file.getName();
            if (file.isDirectory()) {
                assert !fileName.contains(".");
                classes.addAll(findClasses(file, packageName + "." + fileName));
            } else if (fileName.endsWith(".class")
                    && !fileName.contains("$")
                    && !fileName.contains("AsyncResultsContainer")
                    && !fileName.endsWith("Test.class")) {
                Class _class;
                try {
                    _class = Class.forName(packageName + '.' + fileName.substring(0, fileName.length() - 6));
                } catch (ExceptionInInitializerError e) {
                    // happen, for example, in classes, which depend on
                    // Spring to inject some beans, and which fail,
                    // if dependency is not fulfilled
                    _class = Class.forName(packageName + '.' + fileName.substring(0, fileName.length() - 6),
                            false, Thread.currentThread().getContextClassLoader());
                }
                classes.add(_class);
            }
        }
        return classes;
    }

} // The End...
