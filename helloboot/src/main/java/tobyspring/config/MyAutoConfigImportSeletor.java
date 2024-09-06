package tobyspring.config;

import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.boot.context.annotation.ImportCandidates;
import org.springframework.context.annotation.DeferredImportSelector;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.StreamSupport;

public class MyAutoConfigImportSeletor implements DeferredImportSelector {
    private final ClassLoader classLoader;

    public MyAutoConfigImportSeletor(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        List<String> autoConfigs = new ArrayList<>();

//        for(String candidates : ImportCandidates.load(MyAutoConfiguration.class, classLoader)) {
//        ImportCandidates.load(MyAutoConfiguration.class, classLoader).forEach(candidate ->
//                autoConfigs.add(candidate)
//                );
        ImportCandidates.load(MyAutoConfiguration.class, classLoader).forEach(autoConfigs::add);

        return autoConfigs.toArray(new String[0]);
//        return autoConfigs.stream().toArray(String[]::new);
//        return Arrays.copyOf(autoConfigs.toArray(), autoConfigs.size(), String[].class);
    }
}
