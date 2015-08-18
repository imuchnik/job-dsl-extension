/**
 * Created by muchniki on 8/18/15.
 */

import hudson.Extension;
import javaposse.jobdsl.dsl.helpers.step.StepContext;
import javaposse.jobdsl.plugin.ContextExtensionPoint;
import javaposse.jobdsl.plugin.DslExtensionMethod;

@Extension(optional = true)
public class ExampleJobDslExtension extends ContextExtensionPoint {
    @DslExtensionMethod(context = StepContext.class)
    public Object example(String optionA, int optionB) {
       // do something
    }


}
