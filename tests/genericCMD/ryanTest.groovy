#!/usr/bin/env groovy


import static groovy.test.GroovyAssert.*

import org.junit.jupiter.api.Test

class GenericCmdTestCase {

    @Test
    void itCallsShWhenItIsUnix() {
        def script = this.class.classLoader.parseClass(new File("../../vars/genericCMD.groovy"))

        def didCheckUnix = false
        def whatItCalled = ""

        // need to do any mocking before we instantiate any instances of this (??) RPW 10-26-2018
        script.metaClass.isUnix = { didCheckUnix = true; return true }
        script.metaClass.sh     = { args -> whatItCalled = args; return true }

        def loadedJenkinsScript = script.newInstance()

        loadedJenkinsScript("ls")
        assert didCheckUnix

        assertEquals(whatItCalled, "ls")
    }
}
