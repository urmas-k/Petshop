package eu.urmas.petshop.infrastructure.db;

import org.hsqldb.server.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * Configuration for running an HSQLDB TCP server so external tools (e.g. IntelliJ Database tool)
 * can connect via jdbc:hsqldb:hsql://localhost:9001/mydb.
 *
 * Enabled only when the 'hsql-server' Spring profile is active to avoid unintended server startup.
 */
@Configuration
@Profile("hsql-server")
public class HsqlServerConfig {
  private static final Logger log = LoggerFactory.getLogger(HsqlServerConfig.class);

  @Bean(initMethod = "start", destroyMethod = "stop")
  public Server hsqlServer() {
    Server server = new Server();
    server.setLogWriter(null); // suppress console logging from HSQLDB itself
    server.setSilent(true); // reduce verbosity
    server.setDatabaseName(0, "mydb"); // this name must match “mem:mydb”
    server.setDatabasePath(0, "mem:mydb"); // in-memory mode
    server.setPort(9001); // TCP port for client connections
    log.info("Starting HSQLDB server on port 9001 (in-memory database 'mydb')");
    return server;
  }
}
