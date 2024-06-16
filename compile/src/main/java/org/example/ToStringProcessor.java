package org.example;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import javax.tools.JavaFileObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;

@SupportedAnnotationTypes("org.example.ToString")
@SupportedSourceVersion(SourceVersion.RELEASE_21)
public class ToStringProcessor extends AbstractProcessor {

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        for (Element element : roundEnv.getElementsAnnotatedWith(ToString.class)) {
            if (element instanceof TypeElement) {
                TypeElement typeElement = (TypeElement) element;
                try {
                    generateToString(typeElement);
                } catch (IOException e) {
                    processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR, e.toString());
                }
            }
        }
        return true;
    }

    private void generateToString(TypeElement typeElement) throws IOException {
        String className = typeElement.getSimpleName() + "ToString";
        String packageName = processingEnv.getElementUtils().getPackageOf(typeElement).getQualifiedName().toString();
        String fullName = packageName + "." + className;

        JavaFileObject builderFile = processingEnv.getFiler().createSourceFile(fullName);

        try (PrintWriter out = new PrintWriter(builderFile.openWriter())) {
            out.println("package " + packageName + ";");
            out.println();
            out.println("public class " + className + " {");
            out.println("    public static String toString(" + typeElement.getSimpleName() + " instance) {");
            out.println("        return \"" + typeElement.getSimpleName() + "[make='\" + instance.getMake() + \"', model='\" + instance.getModel() + \"', year='\" + instance.getYear() + \"']\";");
            out.println("    }");
            out.println("}");
        }
    }
}
