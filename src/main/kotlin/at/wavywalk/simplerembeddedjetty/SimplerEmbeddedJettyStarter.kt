package at.wavywalk.simplerembeddedjetty

import at.wavywalk.simpler.applicationservices.SimplerApplicationBootstrapper
import at.wavywalk.simpler.servlets.FrontServlet
import org.eclipse.jetty.server.Server
import org.eclipse.jetty.server.handler.HandlerList
import org.eclipse.jetty.server.session.SessionHandler
import org.eclipse.jetty.servlet.ServletContextHandler
import org.eclipse.jetty.servlet.ServletHolder
import org.eclipse.jetty.servlet.DefaultServlet



class SimplerEmbeddedJettyStarter {

    fun startServer(port: Int = 8080, contextListener: SimplerApplicationBootstrapper): Unit {
        val server = Server(port)

        val defaultServlet = DefaultServlet()
        val defaultServletHolder = ServletHolder("default", defaultServlet)
        defaultServletHolder.setInitParameter("resourceBase", "./src/main/webapp")
        defaultServletHolder.setInitParameter("dirAllowed", "false")

        val servletContextHandler = ServletContextHandler().also {
            it.contextPath = "/"
            it.addEventListener(contextListener)
            it.addServlet(FrontServlet::class.java, "/*")
            it.addServlet(defaultServletHolder, "/-")
            it.sessionHandler = SessionHandler()

        }

        val handlers = HandlerList().also {
            it.addHandler(servletContextHandler)
        }

        server.handler = handlers
        server.start()
        server.join()
    }


}