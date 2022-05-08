package drupal.models;

import mgs.qa.base.page.MasterPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public interface ISectionDataModel {

    Logger LOG = LogManager.getLogger(ISectionDataModel.class);

    <P extends MasterPage> P setData(Class<P> expectedClass);

    <P extends MasterPage> P editData(Class<P> expectedClass);

    default void whichClassAndMethod() {
        Class<?> caller = StackWalker
                .getInstance(StackWalker.Option.RETAIN_CLASS_REFERENCE)
                .getCallerClass();

        LOG.debug("Executed className/methodName: {}", caller.getCanonicalName());
    }

}