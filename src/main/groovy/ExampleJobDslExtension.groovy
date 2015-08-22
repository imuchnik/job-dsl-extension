/**
 * Created by muchniki on 8/18/15.
 */

import hudson.Extension
import javaposse.jobdsl.dsl.RequiresPlugin
import javaposse.jobdsl.dsl.helpers.step.StepContext;
import javaposse.jobdsl.dsl.helpers.wrapper.WrapperContext;
import javaposse.jobdsl.plugin.ContextExtensionPoint;
import javaposse.jobdsl.plugin.DslExtensionMethod;

@Extension(optional = true)
public class ExampleJobDslExtension extends ContextExtensionPoint {
    @DslExtensionMethod(context = WrapperContext.class)
    public Object SeleniumTunnelConfig( def browsers) {
       return new SauceConfigObject().buildDefaultSeleniumConfig(browsers)
    }


}
 public class SauceConfigObject{
     @RequiresPlugin(id = 'sauce-ondemand')
     Object buildDefaultSeleniumConfig(String[] seleniumBrowsersDrivers){
         def wrapperNodes
         def browsers =new NodeBuilder().'webDriverBrowsers'{
             seleniumBrowsersDrivers.each {browserName ->
                 'string'(browserName)
             }
         }

         wrapperNodes << new NodeBuilder().'plugins.sauce__ondemand.SauceOnDemandBuildWrapper'{
             useGeneratedTunnelIdentifier(false)
             sendUsageData(false)
             nativeAppPackage()
             useChromeForAndroid(false)
             sauceConnectPath()
             useOldSauceConnect(false)
			 enableSauceConnect(true)
             seleniumHost()
             seleniumPort()
             ${browsers}
             appiumBrowsers
             useLatestVersion(false)
			 launchSauceConnectOnSlave(false)
             httpsProtocol()
             options()
             verboseLogging(false)
         }

         wrapperNodes
     }
 }

//@RequiresPlugin(id = 'nodejs')
//void nodejs(String installation) {
//    wrapperNodes << new NodeBuilder().'jenkins.plugins.nodejs.tools.NpmPackagesBuildWrapper' {
//        nodeJSInstallationName(installation)
//    }
//}
