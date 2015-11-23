package com.github.rodrigohenriques.samba.internal;

import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;
import java.util.List;

import javax.lang.model.element.Modifier;

public class CodeGenerator {
    public static String SAMBA_PACKAGE = "com.github.rodrigohenriques.samba";

    List<ViewHolderAnnotatedClass> mAnnotatedClasses;

    public CodeGenerator(List<ViewHolderAnnotatedClass> annotatedClasses) {
        this.mAnnotatedClasses = annotatedClasses;
    }

    public void generate() throws IOException {
        for (ViewHolderAnnotatedClass viewHolderAnnotatedClass : mAnnotatedClasses) {
            generate(viewHolderAnnotatedClass);
        }
    }

    private void generate(ViewHolderAnnotatedClass viewHolderAnnotatedClass) {
        MethodSpec main = MethodSpec.methodBuilder("main")
                .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                .returns(void.class)
                .addParameter(String[].class, "args")
                .addStatement("$T.out.println($S)", System.class, "Hello, JavaPoet!")
                .build();

        String generatedClassName = generateClassName(viewHolderAnnotatedClass);

        TypeSpec typeSpec = TypeSpec.classBuilder(generatedClassName)
                .addModifiers(Modifier.PUBLIC, Modifier.FINAL)
                .addMethod(main)
                .build();

        JavaFile javaFile = JavaFile.builder(SAMBA_PACKAGE, typeSpec)
                .build();

        //javaFile.writeTo(System.out);
    }

    private String generateClassName(ViewHolderAnnotatedClass viewHolderAnnotatedClass) {
        return viewHolderAnnotatedClass.getSimpleName();
    }
}
