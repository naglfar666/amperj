package com.amperj.registrar;

import com.amperj.core.AmperApplication;
import com.amperj.core.AmperContext;
import com.amperj.models.AmperRunnerModel;
import com.amperj.processors.RestControllerProcessor;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;

public class BeanRegistrar implements Registrar {

    private AmperRunnerModel amperRunnerModel;

    private Class<?>[] applicationClasses;

    public void register(AmperContext amperContext) {
        amperRunnerModel = AmperApplication.getAmperRunnerModel();
        // Получаем все классы приложения
        registerClasses();
        // Регистрируем контроллеры
        registerControllers();
    }

    /**
     * Регистрирует все классы внутри пользовательского приложения
     */
    private void registerClasses() {
        try {
            applicationClasses = getClasses(amperRunnerModel.getApplicationClass().getPackage().getName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("All registered classes " + Arrays.toString(applicationClasses));
    }

    /**
     * Сканируем все классы пользовательского приложения
     *
     * @param packageName The base package
     * @return The classes
     * @throws ClassNotFoundException
     * @throws IOException
     */
    private static Class<?>[] getClasses(String packageName)
            throws ClassNotFoundException, IOException, URISyntaxException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        String path = packageName.replace('.', '/');

        Enumeration<URL> resources = classLoader.getResources(path);

        ArrayList<File> dirs = new ArrayList<>();
        while (resources.hasMoreElements()) {
            URL resource = resources.nextElement();
            Paths.get(resource.toURI()).toFile();
            dirs.add(Paths.get(resource.toURI()).toFile());
        }
        ArrayList<Class<?>> classes = new ArrayList<>();
        for (File directory : dirs) {
            classes.addAll(findClasses(directory, packageName));
        }
        return classes.toArray(new Class[classes.size()]);
    }
    /**
     * Рекурсивно обрабатываем все классы
     *
     * @param directory   The base directory
     * @param packageName The package name for classes found inside the base directory
     * @return The classes
     * @throws ClassNotFoundException
     */
    private static List<Class<?>> findClasses(File directory, String packageName) throws ClassNotFoundException {
        List<Class<?>> classes = new ArrayList<>();
        if (!directory.exists()) {
            return classes;
        }
        File[] files = directory.listFiles();

        if (files == null) {
            throw new NullPointerException("Files are null");
        }

        for (File file : files) {
            if (file.isDirectory()) {
                assert !file.getName().contains(".");
                classes.addAll(findClasses(file, packageName + "." + file.getName()));
            } else if (file.getName().endsWith(".class")) {
                classes.add(Class.forName(packageName + '.' + file.getName().substring(0, file.getName().length() - 6)));
            }
        }
        return classes;
    }

    /**
     * Регистрирует контроллеры
     */
    private void registerControllers() {
        RestControllerProcessor restControllerProcessor = new RestControllerProcessor();
        restControllerProcessor.process(AmperApplication.getAmperContext(), applicationClasses);
    }

}
