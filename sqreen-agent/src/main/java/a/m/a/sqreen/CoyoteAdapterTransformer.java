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
            } catch (Exception ex) {
                System.out.println("### Ooops there seems to be a fuckup " + ex);
                ex.printStackTrace();
                throw new RuntimeException("MAJOR SCREW UP");
            }
        }

        return byteCode;
    }
}
