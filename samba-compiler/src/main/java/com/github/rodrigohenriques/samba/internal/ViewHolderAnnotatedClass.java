package com.github.rodrigohenriques.samba.internal;

import com.github.rodrigohenriques.samba.ViewHolder;

import javax.lang.model.element.TypeElement;

public class ViewHolderAnnotatedClass extends AnnotatedClass {
    private int mLayoutId;

    public ViewHolderAnnotatedClass(TypeElement typeElement) throws UnsupportedOperationException, IllegalArgumentException {
        super(typeElement);

        ViewHolder annotation = mTypeElement.getAnnotation(ViewHolder.class);
        mLayoutId = annotation.value();

        if (mLayoutId == 0) {
            throw new IllegalArgumentException(
                    String.format("The value in @%s for class %s is null or empty! that's not allowed",
                            ViewHolder.class.getSimpleName(),
                            mTypeElement.getQualifiedName().toString()
                    )
            );
        }

        if (isNotPublic()) {
            throw new UnsupportedOperationException(
                    String.format("Classes annotated with @%s must be public.", ViewHolder.class.getSimpleName())
            );
        }

        if (isAbstract()) {
            throw new UnsupportedOperationException(
                    String.format("Classes annotated with @%s must not be abstract.", ViewHolder.class.getSimpleName())
            );
        }
    }

    public int getLayoutId() {
        return mLayoutId;
    }

    public String getSimpleName() {
        return mTypeElement.getSimpleName().toString();
    }
}
