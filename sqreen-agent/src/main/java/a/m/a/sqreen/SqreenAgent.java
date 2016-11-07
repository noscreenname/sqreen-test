package a.m.a.sqreen;

import java.lang.instrument.Instrumentation;

final class SqreenAgent {

    public static void premain(String agentArgs, Instrumentation inst) {
        inst.addTransformer(new CoyoteAdapterTransformer());
    }
}
