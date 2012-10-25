package com.inet.jorthotests;

import com.inet.jortho.SpellChecker;
import java.net.MalformedURLException;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({EventTest.class, MemoryTest.class})
public class AllTests {

    private static boolean isInit;

    /**
     * register the dictionaries
     */
    static void init() throws MalformedURLException {
        if (!isInit) {
            isInit = true;
            final int threadCount = Thread.activeCount();
            SpellChecker.registerDictionaries(null, null);
            // wait until the dictionaries are loaded.
            for (int i = 0; i < 50; i++) {
                if (threadCount >= Thread.activeCount()) {
                    break;
                }
                try {
                    Thread.sleep(100);
                } catch (final InterruptedException e) {
                    break;
                }
            }
        }
    }
}
