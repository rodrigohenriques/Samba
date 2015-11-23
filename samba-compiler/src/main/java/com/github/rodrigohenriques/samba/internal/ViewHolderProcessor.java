package com.github.rodrigohenriques.samba.internal;

import com.github.rodrigohenriques.samba.ViewHolder;
import com.google.auto.service.AutoService;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
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
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import javax.tools.Diagnostic;

@AutoService(Processor.class)
public class ViewHolderProcessor extends AbstractProcessor {
    private static final String ANNOTATION = "@" + ViewHolder.class.getSimpleName();

    private Types mTypeUtils;
    private Elements mElementUtils;
    private Messager mMessager;
    private Filer mFiler;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        mTypeUtils = processingEnv.getTypeUtils();
        mElementUtils = processingEnv.getElementUtils();
        mFiler = processingEnv.getFiler();
        mMessager = processingEnv.getMessager();
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> annotations = new LinkedHashSet<>();
        annotations.add(ViewHolder.class.getCanonicalName());
        return annotations;
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        List<ViewHolderAnnotatedClass> classes = new ArrayList<>();

        for (Element element : roundEnv.getElementsAnnotatedWith(ViewHolder.class)) {
            if (element.getKind().isClass()) {
                TypeElement typeElement = (TypeElement) element;

                ViewHolderAnnotatedClass annotatedClass;

                try {
                    annotatedClass = new ViewHolderAnnotatedClass(typeElement);
                } catch (Exception e) {
                    error(typeElement, e.getMessage());
                    return true;
                }

                classes.add(annotatedClass);
            } else {
                error(element, "Annotation %s must be used only in classes", ANNOTATION);
                return true;
            }
        }

        CodeGenerator codeGenerator = new CodeGenerator(classes);
        codeGenerator.generate();
        return false;
    }

    private void error(Element e, String msg) {
        mMessager.printMessage(Diagnostic.Kind.ERROR, msg, e);
    }

    private void error(Element e, String msg, Object... args) {
        mMessager.printMessage(Diagnostic.Kind.ERROR, String.format(msg, args), e);
    }
}
