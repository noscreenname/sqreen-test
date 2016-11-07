package a.m.a.sqreen;

import javassist.*;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

final class CoyoteAdapterTransformer implements ClassFileTransformer {

    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
        byte[] byteCode = classfileBuffer;

        if (className.equals("org/apache/catalina/connector/CoyoteAdapter")) {
            try {
                ClassPool pool = ClassPool.getDefault();
                pool.insertClassPath(new LoaderClassPath(loader));
                CtClass cc = pool.get("org.apache.catalina.connector.CoyoteAdapter");
                CtMethod m = cc.getDeclaredMethod("service");
                m.insertBefore("$2.addHeader(\"X-Instrumented-By\", \"sqreen\");");
                byteCode = cc.toBytecode();
                cc.detach();
            } catch (Exception e) {
                throw new RuntimeException("Failed to instrument tomcat", e);
            }
        }

        return byteCode;
    }
}
