package com.github.rodrigohenriques.samba.internal;

import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;

public class AnnotatedClass {
    TypeElement mTypeElement;

    public AnnotatedClass(TypeElement mTypeElement) {
        this.mTypeElement = mTypeElement;
    }

    public boolean isPublic() {
        return mTypeElement.getModifiers().contains(Modifier.PUBLIC);
    }

    public boolean isAbstract() {
        return mTypeElement.getModifiers().contains(Modifier.ABSTRACT);
    }

    public boolean isNotPublic() {
        return !isPublic();
    }
}
