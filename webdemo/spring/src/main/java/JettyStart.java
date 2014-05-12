import org.mortbay.jetty.Connector;
import org.mortbay.jetty.Handler;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.handler.ContextHandlerCollection;
import org.mortbay.jetty.handler.DefaultHandler;
import org.mortbay.jetty.handler.HandlerCollection;
import org.mortbay.jetty.handler.RequestLogHandler;
import org.mortbay.jetty.nio.SelectChannelConnector;
import org.mortbay.jetty.webapp.WebAppContext;
import org.mortbay.thread.BoundedThreadPool;

public class JettyStart {

	public static final int JETTY_PORT = 8088;

	public static final int MONITOR_PORT = 8099;

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		// change the default file encoding to utf-8
		// need add the -Dfile.encoding=utf-8 to command line in deploy
		// environment
		System.setProperty("file.encoding", "utf-8");

		Server server = new Server();
		BoundedThreadPool threadPool = new BoundedThreadPool();
		threadPool.setMaxThreads(100);
		server.setThreadPool(threadPool);

		Connector connector = new SelectChannelConnector();
		connector.setPort(JETTY_PORT);
		server.setConnectors(new Connector[] { connector });

		WebAppContext context = new WebAppContext("./webapp/web", "/springmvc");
		//WebAppContext context = new WebAppContext();
		//context.setResourceBase("./war");
		//context.setContextPath("/daemon");
		HandlerCollection handlers = new HandlerCollection();
		ContextHandlerCollection contexts = new ContextHandlerCollection();
		RequestLogHandler requestLogHandler = new RequestLogHandler();
		handlers.setHandlers(new Handler[] { contexts, new DefaultHandler(),
				requestLogHandler });
		contexts.addHandler(context);

		server.setHandler(handlers);


		// NCSARequestLog requestLog = new ();
		// requestLogHandler.setRequestLog(requestLog);

		server.setStopAtShutdown(true);
		server.setSendServerVersion(true);

		server.start();
		server.join();
	}

}
