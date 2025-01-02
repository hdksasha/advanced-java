package org.example;

import com.google.auto.service.AutoService;
import org.example.annotations.*;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.tools.Diagnostic;
import java.util.Set;

@SupportedAnnotationTypes({
        "com.example.annotations.NotNull",
        "com.example.annotations.MaxValue",
        "com.example.annotations.MinValue",
        "com.example.annotations.StringLength"
})
@SupportedSourceVersion(SourceVersion.RELEASE_23)
@AutoService(Processor.class)
public class AnnotationProcessor extends AbstractProcessor {
    private Messager messager;

    // Initialize the annotation processor
    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        messager = processingEnv.getMessager();
    }

    // Process the annotated elements
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        for (Element element : roundEnv.getElementsAnnotatedWith(NotNull.class)) {
            validateNotNull(element);
        }
        for (Element element : roundEnv.getElementsAnnotatedWith(MaxValue.class)) {
            validateMaxValue(element);
        }
        for (Element element : roundEnv.getElementsAnnotatedWith(MinValue.class)) {
            validateMinValue(element);
        }
        for (Element element : roundEnv.getElementsAnnotatedWith(StringLength.class)) {
            validateStringLength(element);
        }
        return true;
    }

    // Validate that the field is not null
    private void validateNotNull(Element element) {
        if (element.getKind() != ElementKind.FIELD) {
            messager.printMessage(Diagnostic.Kind.ERROR,
                    "Element is not a field",
                    element
            );
        } else {
            VariableElement variable = (VariableElement) element;

            if (variable.asType().getKind().isPrimitive()) {
                messager.printMessage(Diagnostic.Kind.NOTE,
                        "@NotNull couldn't be applied for field " + element.getSimpleName(),
                        element
                );
            }
        }
    }

    // Validate that the field has the correct type for @MaxValue annotation
    private void validateMaxValue(Element element) {
        if (element.getKind() != ElementKind.FIELD) {
            messager.printMessage(Diagnostic.Kind.ERROR,
                    "Element is not a field",
                    element
            );
        } else {
            String fieldType = element.asType().toString();

            if (!fieldType.equals("java.lang.Integer")) {
                messager.printMessage(Diagnostic.Kind.ERROR,
                        "@MaxValue could be applied only for Integer fields",
                        element
                );
            }
        }
    }

    // Validate that the field has the correct type for @MinValue annotation
    private void validateMinValue(Element element) {
        if (element.getKind() != ElementKind.FIELD) {
            messager.printMessage(Diagnostic.Kind.ERROR,
                    "Element is not a field",
                    element
            );
        } else {
            String fieldType = element.asType().toString();

            if (!fieldType.equals("java.lang.Integer")) {
                messager.printMessage(Diagnostic.Kind.ERROR,
                        "@MinValue could be applied only for Integer fields",
                        element
                );
            }
        }
    }

    // Validate that the field has the correct type for @StringLength annotation
    private void validateStringLength(Element element) {
        if (element.getKind() != ElementKind.FIELD) {
            messager.printMessage(Diagnostic.Kind.ERROR,
                    "Element is not a field",
                    element
            );
        } else {
            String fieldType = element.asType().toString();

            if (!fieldType.equals("java.lang.String")) {
                messager.printMessage(Diagnostic.Kind.ERROR,
                        "@StringLength could be applied only for String fields",
                        element
                );
            }
        }
    }
}
